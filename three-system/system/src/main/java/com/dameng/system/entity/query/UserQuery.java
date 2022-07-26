package com.dameng.system.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *  User多条件查询对象
 * </p>
 *
 * @author 大梦
 * @since 2022-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User多条件查询对象", description="")
public class UserQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "排序")
    private Integer order;

}
