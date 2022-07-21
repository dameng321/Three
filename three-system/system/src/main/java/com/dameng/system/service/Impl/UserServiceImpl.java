package com.dameng.system.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dameng.common.core.utils.MD5;
import com.dameng.system.entity.User;
import com.dameng.system.mapper.UserMapper;
import com.dameng.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User getByUsernameOrPhone(String username, String phone, String password) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username",username).or().eq("phone",phone).eq("password", MD5.encrypt(password)));
    }
}
