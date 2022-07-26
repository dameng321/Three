package com.dameng.system.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Description: 菜单前端传输对象</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022/7/21 10:54
 **/

@Data
@ApiModel(value="菜单前端传输对象", description="菜单")
public class MenuForm {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "所属上级")
    private Long pid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮,3其他)")
    private Integer type;

    @ApiModelProperty(value = "权限值")
    private String permissionValue;

    @ApiModelProperty(value = "访问路径")
    private String path;

    @ApiModelProperty("重定向路径")
    private String redirect;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "状态(0:禁止,1:正常)")
    private Integer status;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

}
