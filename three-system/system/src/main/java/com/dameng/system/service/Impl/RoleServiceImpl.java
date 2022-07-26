package com.dameng.system.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dameng.common.core.result.R;
import com.dameng.common.core.result.ResultEnum;
import com.dameng.system.entity.Role;
import com.dameng.system.entity.RolePermission;
import com.dameng.system.entity.UserRole;
import com.dameng.system.entity.dto.RoleUserDto;
import com.dameng.system.entity.form.RoleForm;
import com.dameng.system.mapper.RoleMapper;
import com.dameng.system.service.RolePermissionService;
import com.dameng.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dameng.system.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Role getRoleByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public Map<Long, String> getUserRoleMap(List<Long> userIds) {
        List<RoleUserDto> roleUsers = baseMapper.selectRoleNameByUserIds(userIds);
        return roleUsers.stream().collect(Collectors.toMap(RoleUserDto::getUserId,RoleUserDto::getRoleName));
    }

    @Override
    public R pageList(Integer current, Integer size) {
        Page<Role> rolePage = new Page<>(current, size);
        Page<Role> page = baseMapper.selectPage(rolePage, new QueryWrapper<>());
        return R.ok().data("total", page.getTotal()).data("list", page.getRecords());
    }

    @Override
    public R saveRole(RoleForm roleForm) {
        Role role = baseMapper.selectOne(new QueryWrapper<>(new Role()).eq("name", roleForm.getRoleName()));
        if (role != null) {
            return R.error(ResultEnum.ROLE_NAME_EXIST);
        }
        role = new Role();
        BeanUtils.copyProperties(roleForm, role);
        baseMapper.insert(role);
        savePermission(roleForm, role);
        return R.ok(ResultEnum.ADD_SUCCESS);
    }

    @Override
    public R updateRole(RoleForm roleForm) {
        Role role = new Role();
        BeanUtils.copyProperties(roleForm, role);
        baseMapper.update(role, new QueryWrapper<>(new Role()).eq("id", roleForm.getId()));
        rolePermissionService.remove(new QueryWrapper<>(new RolePermission()).eq("role_id", roleForm.getId()));
        savePermission(roleForm, role);
        return R.ok(ResultEnum.UPDATE_SUCCESS);
    }

    @Override
    public R deleteRole(List<Long> ids) {
        long count = userRoleService.count(new QueryWrapper<>(new UserRole()).in("role_id", ids));
        if (count > 0) {
            return R.error(ResultEnum.ROLE_USER_EXIST);
        }
        baseMapper.deleteBatchIds(ids);
        rolePermissionService.remove(new QueryWrapper<>(new RolePermission()).in("role_id", ids));
        return R.ok(ResultEnum.DELETE_SUCCESS);
    }

    private void savePermission(RoleForm roleForm, Role role) {
        List<RolePermission> permissionList = new ArrayList<>();
        permissionList.add(new RolePermission().setPermissionId(1L).setRoleId(role.getId()));
        for (Long permission : roleForm.getPermissions()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permission);
        }
        rolePermissionService.saveBatch(permissionList);
    }
}
