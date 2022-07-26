package com.dameng.common.core.result;

import lombok.Getter;

/**
 * <p>Description: 返回枚举</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022/2/18 13:51
 **/

@Getter
public enum ResultEnum {

    //==============================系统级别信息==============================

    ERROR(-1, "服务端错误"),

    SUCCESS(0, "成功"),

    TIME_OUT(1001, "超时"),

    REQ_METHOD_NOT_SUPPORT(1002,"请求方式不支持"),


    //==============================业务级别通用信息==============================

    NEED_LOGIN(1100, "用户未登录, 请先登录"),

    PARAM_ERROR(1101, "参数错误"),

    ARGUMENT_NULL(1102, "参数为空"),

    ARGUMENT_TYPE_MISMATCH(1103, "参数类型不匹配"),

    ADD_ERROR(1103, "添加失败"),

    ADD_SUCCESS(1104, "添加成功"),

    UPDATE_ERROR(1106, "更新失败"),

    UPDATE_SUCCESS(1107, "更新成功"),

    DELETE_ERROR(1108, "删除失败"),

    DELETE_SUCCESS(1109, "删除成功"),

    GET_ERROR(1107, "查找失败"),


    //==============================用户相关信息==============================

    USERNAME_OR_PASSWORD_ERROR(1200, "用户名或密码错误"),

    PASSWORD_ERROR(1201,"密码错误"),

    USERNAME_EXIST(1202, "用户名已存在"),

    EMAIL_EXIST(1203, "邮箱已存在"),

    PHONE_EXIST(1204, "手机号已存在"),

    USERNAME_OR_PHONE_OR_EMAIL_EXIST(1205, "用户名或手机号或邮箱已存在"),

    USER_NOT_CHECK_PASSWORD(1206,"密码输入不一致"),

    //==============================角色相关信息==============================

    ROLE_NAME_EXIST(1210, "角色名已存在"),

    ROLE_NOT_EXIST(1211, "角色不存在"),

    ROLE_USER_EXIST(1212, "角色已绑定用户"),

    ROLE_USER_NOT_EXIST(1213, "角色未绑定用户"),

    ROLE_PERMISSION_EXIST(1214, "角色已绑定权限"),

    ROLE_PERMISSION_NOT_EXIST(1215, "角色未绑定权限"),

    //==============================权限相关信息==============================

    PERMISSION_NAME_EXIST(1220, "权限名已存在"),

    PERMISSION_NOT_EXIST(1221, "权限不存在"),

    PERMISSION_USER_EXIST(1222, "权限已绑定用户"),

    PERMISSION_USER_NOT_EXIST(1223, "权限未绑定用户"),

    PERMISSION_ROLE_EXIST(1224, "权限已绑定角色"),

    PERMISSION_ROLE_NOT_EXIST(1225, "权限未绑定角色"),

    //==============================菜单相关信息==============================

    MENU_NAME_EXIST(1230, "菜单名已存在"),

    MENU_NOT_EXIST(1231, "菜单不存在"),

    MENU_PARENT_NOT_EXIST(1232, "父级菜单不存在"),



    ;

    private final Integer code;

    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过状态码获取枚举对象
     * @param code 状态码
     * @return 枚举对象
     */
    public static ResultEnum getByCode(int code){
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if(code == resultEnum.getCode()){
                return resultEnum;
            }
        }
        return null;
    }

}
