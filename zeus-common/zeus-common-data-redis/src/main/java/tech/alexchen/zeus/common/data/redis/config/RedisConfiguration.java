package tech.alexchen.zeus.common.data.redis.config;

import com.alibaba.fastjson2.support.spring.data.redis.GenericFastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 自动配置
 *
 * @author alexchen
 */
@EnableCaching
@AutoConfiguration
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfiguration {

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 使用 Jackson 序列化
//        template.setKeySerializer(RedisSerializer.string());
//        template.setValueSerializer(RedisSerializer.json());
//        template.setHashKeySerializer(RedisSerializer.string());
//        template.setHashValueSerializer(RedisSerializer.json());

        // 使用 fastjson2 序列化
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        return template;
    }

}
