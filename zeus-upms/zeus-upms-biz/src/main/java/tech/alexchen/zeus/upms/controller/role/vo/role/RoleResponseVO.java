package tech.alexchen.zeus.upms.controller.role.vo.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoleResponseVO extends RoleBaseVO {

    @ApiModelProperty("角色 id")
    private Long id;
}