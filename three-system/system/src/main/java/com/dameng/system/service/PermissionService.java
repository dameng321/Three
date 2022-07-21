package com.dameng.system.service;

import com.dameng.system.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
public interface PermissionService extends IService<Permission> {

    /**
     * Description: 通过角色id获取对应权限值
     * @param roleIds 1
     * @return java.util.List<java.lang.String>
     * @author dameng
     * @date 2022-7-14 上午 09:35
     * @since 0.1.0
     */
    List<String> getPerValuesByRoleIds(List<Long> roleIds);

    /**
     * Description: 通过角色id获取列表
     * @param roleId 1
     * @return java.util.List<com.dameng.system.entity.Permission>
     * @author dameng
     * @date 2022-7-19 上午 11:49
     * @since 0.1.0
     */
    List<Permission> getListByRoleId(Long roleId);
}
