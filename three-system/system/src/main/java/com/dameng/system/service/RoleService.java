package com.dameng.system.service;

import com.dameng.common.core.result.R;
import com.dameng.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dameng.system.entity.form.RoleForm;

import java.util.List;
import java.util.Map;

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

    /**
     * Description: userid获取角色名
     * @param userIds 1 
     * @return java.util.Map<java.lang.Long,java.lang.String>
     * @author dameng
     * @date 2022-7-21 下午 02:25
     * @since 0.1.0
     */
    Map<Long, String> getUserRoleMap(List<Long> userIds);

    /**
     * Description: 分页获取角色列表
     * @param current 1
 * @param size 2
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 03:43
     * @since 0.1.0
     */
    R pageList(Integer current, Integer size);

    /**
     * Description: 添加角色
     * @param roleForm 1
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 03:46
     * @since 0.1.0
     */
    R saveRole(RoleForm roleForm);

    /**
     * Description: 修改角色
     * @param roleForm 1 
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 03:52
     * @since 0.1.0
     */
    R updateRole(RoleForm roleForm);

    /**
     * Description: 删除角色
     * @param ids 1
     * @return com.dameng.common.core.result.R
     * @author dameng
     * @date 2022-7-21 下午 03:54
     * @since 0.1.0
     */
    R deleteRole(List<Long> ids);
}
