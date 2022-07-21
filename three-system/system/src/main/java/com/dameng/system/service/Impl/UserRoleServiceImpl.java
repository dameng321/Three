package com.dameng.system.service.Impl;

import com.dameng.system.entity.UserRole;
import com.dameng.system.mapper.UserRoleMapper;
import com.dameng.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
