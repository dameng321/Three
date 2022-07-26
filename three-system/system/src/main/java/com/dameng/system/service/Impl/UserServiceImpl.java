package com.dameng.system.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dameng.common.core.result.R;
import com.dameng.common.core.result.ResultEnum;
import com.dameng.common.core.utils.MD5;
import com.dameng.system.entity.Role;
import com.dameng.system.entity.User;
import com.dameng.system.entity.UserRole;
import com.dameng.system.entity.dto.UserVo;
import com.dameng.system.entity.form.UserForm;
import com.dameng.system.entity.query.UserQuery;
import com.dameng.system.mapper.UserMapper;
import com.dameng.system.service.RoleService;
import com.dameng.system.service.UserRoleService;
import com.dameng.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * 用户表 服务实现类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User getByUsernameOrPhone(String username, String phone, String password) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username",username).or().eq("phone",phone).eq("password", MD5.encrypt(password)));
    }

    @Override
    public R pageList(Integer current, Integer size, UserQuery userQuery) {
        long l = System.currentTimeMillis();
        Page<User> userPage = new Page<>(current,size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(userQuery.getUsername() != null && !"".equals(userQuery.getUsername())){
            queryWrapper.like("username",userQuery.getUsername());
        }
        if(userQuery.getPhone() != null && !"".equals(userQuery.getPhone())){
            queryWrapper.like("phone",userQuery.getPhone());
        }
        if (userQuery.getEmail() != null && !"".equals(userQuery.getEmail())){
            queryWrapper.like("email",userQuery.getEmail());
        }
        if(userQuery.getNickName() != null && !"".equals(userQuery.getNickName())){
            queryWrapper.like("nick_name",userQuery.getNickName());
        }
        if (userQuery.getOrder()==null) {
            userQuery.setOrder(0);
        }

        switch (userQuery.getOrder()){
            case 1:
                queryWrapper.orderByAsc("create_time");
                break;
            case 3:
                queryWrapper.orderByDesc("update_time");
                break;
            case 4:
                queryWrapper.orderByAsc("update_time");
                break;
            case 2:
            default:
                queryWrapper.orderByDesc("create_time");
        }

        Page<User> page = baseMapper.selectPage(userPage, queryWrapper);

        List<User> records = page.getRecords();

        Map<Long, String> userRoleMap = roleService.getUserRoleMap(records.stream().map(User::getId).collect(Collectors.toList()));
        List<UserVo> userVos = new ArrayList<>();
        for (User user : records) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            userVo.setRoleName(userRoleMap.get(user.getId()));
            userVos.add(userVo);
        }

        return R.ok().data("total",page.getTotal()).data("list",userVos);
    }

    @Override
    public R saveUser(UserForm userForm) {
        if (countRoleByRoleId(userForm.getRoleId()) == 0){
            return R.error(ResultEnum.ROLE_NOT_EXIST);
        }
        if (!userForm.getPassword().equals(userForm.getCheckPassword())){
            return R.error(ResultEnum.USER_NOT_CHECK_PASSWORD);
        }
        User user = new User();
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userForm,user);
        User checkUser = baseMapper.selectOne(new QueryWrapper<User>().eq("username",
                user.getUsername()).or().eq("phone", user.getPhone()).or().eq("email", user.getEmail()));
        if(checkUser != null){
            return R.error(ResultEnum.USERNAME_OR_PHONE_OR_EMAIL_EXIST);
        }
        baseMapper.insert(user);
        userRole.setUserId(user.getId());
        userRole.setRoleId(userForm.getRoleId());
        userRoleService.save(userRole);
        return R.ok(ResultEnum.ADD_SUCCESS);
    }

    @Override
    public R updateUser(UserForm userForm) {
        if (countRoleByRoleId(userForm.getRoleId()) == 0){
            return R.error(ResultEnum.ROLE_NOT_EXIST);
        }
        User user = new User();
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userForm,user);
        if (user.getPassword() != null && !("".equals(user.getPassword()))){
            user.setPassword(MD5.encrypt(user.getPassword()));
        }
        baseMapper.update(user,new QueryWrapper<User>().eq("id",userForm.getId()));
        userRole.setUserId(user.getId());
        userRole.setRoleId(userForm.getRoleId());
        userRoleService.update(userRole,new QueryWrapper<UserRole>().eq("user_id",userForm.getId()));
        return R.ok(ResultEnum.UPDATE_SUCCESS);
    }

    @Override
    public R deleteUser(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
        userRoleService.remove(new QueryWrapper<UserRole>().in("user_id",ids));
        return R.ok(ResultEnum.DELETE_SUCCESS);
    }

    private long countRoleByRoleId(Long roleId) {
        return roleService.count(new QueryWrapper<Role>().eq("id", roleId));
    }

}
