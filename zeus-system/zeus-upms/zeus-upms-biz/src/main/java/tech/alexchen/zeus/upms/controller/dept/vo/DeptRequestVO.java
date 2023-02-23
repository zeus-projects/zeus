package tech.alexchen.zeus.upms.controller.dept.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 部门 - RequestVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeptRequestVO extends DeptBaseVO {

}