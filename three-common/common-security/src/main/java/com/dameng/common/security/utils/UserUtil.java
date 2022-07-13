package com.dameng.common.security.utils;

import com.dameng.common.security.entity.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>Description: 当前登陆对象获取工具</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/12/20 10:43
 **/

public class UserUtil {
    
    public static SecurityUser getUser(){
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
