package com.dameng.system.service;

import com.dameng.common.core.result.R;
import com.dameng.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dameng.system.entity.form.UserForm;
import com.dameng.system.entity.query.UserQuery;

import java.util.List;

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

    /**
     * Description: 多条件分页获取用户列表
     * @param current 1
 * @param size 2
 * @param userQuery 3 
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 02:12
     * @since 0.1.0
     */
    R pageList(Integer current, Integer size, UserQuery userQuery);

    /**
     * Description: 添加用户
     * @param userForm 1 
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 02:46
     * @since 0.1.0
     */
    R saveUser(UserForm userForm);

    /**
     * Description: 修改用户
     * @param userForm 1
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 02:54
     * @since 0.1.0
     */
    R updateUser(UserForm userForm);

    /**
     * Description: 删除用户
     * @param ids 1
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 02:57
     * @since 0.1.0
     */
    R deleteUser(List<Long> ids);

}
