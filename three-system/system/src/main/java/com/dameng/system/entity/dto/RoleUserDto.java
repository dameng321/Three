package com.dameng.system.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Description: 角色名和用户id映射类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022-7-21 14:24
 **/

@Data
@ApiModel(value = "角色名和用户id映射类")
public class RoleUserDto {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "角色名")
    private String roleName;
}
