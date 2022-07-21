package com.dameng.system.service;

import com.dameng.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
public interface RoleService extends IService<Role> {


    /**
     * Description: 通过userId获取角色
     * @param userId 1
     * @return com.dameng.system.entity.Role
     * @author dameng
     * @date 2022-7-13 下午 05:58
     * @since 0.1.0
     */
    Role getRoleByUserId(Long userId);
}
