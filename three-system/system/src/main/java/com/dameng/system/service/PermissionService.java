package com.dameng.system.service;

import com.dameng.common.core.result.R;
import com.dameng.system.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dameng.system.entity.form.MenuForm;

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

    /**
     * Description: 获取全部菜单
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 04:02
     * @since 0.1.0
     */
    R getMenus();

    /**
     * Description: 添加菜单
     * @param menuForm 1 
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 04:12
     * @since 0.1.0
     */
    R saveMenu(MenuForm menuForm);

    /**
     * Description: 修改菜单
     * @param menuForm 1 
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 04:14
     * @since 0.1.0
     */
    R updateMenu(MenuForm menuForm);

    /**
     * Description: 删除菜单
     * @param id 1 
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 04:20
     * @since 0.1.0
     */
    R deleteMenu(Long id);

    /**
     * Description: 获取角色的菜单
     * @param roleId 1
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 04:21
     * @since 0.1.0
     */
    R getMenuByRoleId(Long roleId);
}
