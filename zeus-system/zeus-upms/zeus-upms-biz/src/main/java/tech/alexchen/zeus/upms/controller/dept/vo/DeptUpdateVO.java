package tech.alexchen.zeus.upms.controller.dept.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 部门 - UpdateVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeptUpdateVO extends DeptBaseVO {

    @ApiModelProperty(value = "部门 ID", required = true, example = "1024")
    @NotNull(message = "id 不能为空")
    private Long id;

}
