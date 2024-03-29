package tech.alexchen.zeus.common.log.aop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import tech.alexchen.zeus.common.log.annotation.SysLog;
import tech.alexchen.zeus.common.log.bean.SysLogInfo;
import tech.alexchen.zeus.common.log.enums.LogTypeEnum;
import tech.alexchen.zeus.common.log.event.SysLogEvent;

/**
 * 使用 AOP 收集日志，并通过 Spring Event 推送事件，异步处理
 *
 * @author alexchen
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class SysLogAspect {

    private final ApplicationEventPublisher publisher;

    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        // 获取切点信息
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        log.debug("class: {}, method: {}", className, methodName);

        Object result;
        SysLogInfo logInfo = new SysLogInfo();
        logInfo.setTitle(sysLog.value());
        // 执行切点代码
        Long startTime = System.currentTimeMillis();
        try {
            result = point.proceed();
        } catch (Throwable e) {
            logInfo.setLogType(LogTypeEnum.ERROR.getTypeId());
            logInfo.setException(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Long endTime = System.currentTimeMillis();
            logInfo.setTime(endTime - startTime);
            // 发布事件，由 SysLogListener 异步处理
            publisher.publishEvent(new SysLogEvent(logInfo));
        }
        return result;
    }
}
