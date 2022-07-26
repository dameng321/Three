package com.dameng.system.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Description: 角色表单</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022/7/21 15:12
 **/

@Data
@EqualsAndHashCode
@ApiModel(value="Role前端传输对象", description="")
public class RoleForm implements Serializable {

    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "权限")
    private List<Long> permissions;
}
