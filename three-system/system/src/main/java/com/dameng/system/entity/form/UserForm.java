package com.dameng.system.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>Description: 视图层传输的user对象</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022/7/21 13:55
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")

public class UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "确认密码")
    private String checkPassword;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

}
