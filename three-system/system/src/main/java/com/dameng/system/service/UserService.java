package com.dameng.system.service;

import com.dameng.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
public interface UserService extends IService<User> {

    /**
     * Description: 通过用户名或者手机号加密码查询用户
     * @param username 1
 * @param phone 2
 * @param password 3
     * @return com.dameng.system.entity.User
     * @author dameng
     * @date 2022-7-13 下午 05:28
     * @since 0.1.0
     */
    User getByUsernameOrPhone(String username, String phone, String password);


}
