package com.dameng.system.service;

import com.dameng.common.core.result.R;
import com.dameng.system.entity.form.LoginForm;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: 登录相关服务类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022-7-13 16:40
 **/

public interface LoginService {

    /**
     * Description: 登录
     * @param loginForm 1
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-13 下午 05:08
     * @since 0.1.0
     */
    R login(LoginForm loginForm);

    /**
     * Description: 获取当前登录用户信息
     * @param  1 
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-19 上午 11:34
     * @since 0.1.0
     */
    R info();

    /**
     * Description: 获取路由
     * @param  1 
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-19 上午 11:41
     * @since 0.1.0
     */
    R getRoute();
}
