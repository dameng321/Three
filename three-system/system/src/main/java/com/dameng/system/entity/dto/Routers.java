package com.dameng.system.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>Description: 路由实体类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/3/25 13:36
 **/

@Data
public class Routers {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "上级编号")
    private Long pid;

    @ApiModelProperty(value = "访问路径")
    private String path;

    @ApiModelProperty(value = "重定向路径")
    private String redirect;

    @ApiModelProperty(value = "权限值")
    private String permissionValue;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
    private Integer type;

    @ApiModelProperty(value = "资源")
    private Meta meta;

    @ApiModelProperty(value = "是否隐藏路由")
    private Boolean hidden;

    @ApiModelProperty(value = "子路由")
    private List<Routers> children;

}
