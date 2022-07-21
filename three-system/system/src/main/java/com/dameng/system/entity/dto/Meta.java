package com.dameng.system.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/3/25 13:39
 **/

@Data
public class Meta {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图标")
    private String icon;
}
