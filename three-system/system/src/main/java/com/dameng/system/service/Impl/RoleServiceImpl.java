package com.dameng.system.service.Impl;

import com.dameng.system.entity.Role;
import com.dameng.system.mapper.RoleMapper;
import com.dameng.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Role getRoleByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }
}
