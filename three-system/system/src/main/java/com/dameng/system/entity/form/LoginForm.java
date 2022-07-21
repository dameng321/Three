package com.dameng.system.entity.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Description: 登录提交类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022-7-13 16:45
 **/

@Setter
@Getter
public class LoginForm implements Serializable {

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;

    @ApiModelProperty("是否是验证码登录")
    private Integer isCode;

}
