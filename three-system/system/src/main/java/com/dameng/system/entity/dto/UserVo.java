package com.dameng.system.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: user对象返回信息</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022/7/21 10:33
 **/

@Data
@Accessors(chain = true)
@EqualsAndHashCode
@ToString
public class UserVo implements Serializable {

    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty("最后登录ip")
    private Date lastLoginIp;

    @ApiModelProperty("最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
    
}
