package com.dameng.system.service.Impl;

import com.dameng.system.entity.Permission;
import com.dameng.system.mapper.PermissionMapper;
import com.dameng.system.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<String> getPerValuesByRoleIds(List<Long> roleIds) {
        return baseMapper.selectValuesInRoleIds(roleIds);
    }

    @Override
    public List<Permission> getListByRoleId(Long roleId) {
        return baseMapper.selectListByRoleId(roleId);
    }
}
