package com.shanzhu.purchase.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RoleMenuRelationDto {

    @NotEmpty
    @ApiModelProperty(value = "角色id", required = true)
    private Long roleId;

    @NotEmpty
    @ApiModelProperty(value = "菜单id", required = true)
    private List<Long> menuIds;

}
