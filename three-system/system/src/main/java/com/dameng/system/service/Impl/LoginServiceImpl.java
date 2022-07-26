package com.dameng.system.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dameng.common.core.consts.RedisKeyConst;
import com.dameng.common.core.consts.TokenConst;
import com.dameng.common.core.result.R;
import com.dameng.common.core.utils.JwtUtils;
import com.dameng.common.core.utils.RedisUtil;
import com.dameng.common.core.utils.StringUtils;
import com.dameng.common.core.utils.ip.IpUtils;
import com.dameng.common.security.entity.SecurityUser;
import com.dameng.common.security.utils.UserUtil;
import com.dameng.system.entity.Permission;
import com.dameng.system.entity.Role;
import com.dameng.system.entity.User;
import com.dameng.system.entity.dto.Meta;
import com.dameng.system.entity.dto.Routers;
import com.dameng.system.entity.form.LoginForm;
import com.dameng.system.service.LoginService;
import com.dameng.system.service.PermissionService;
import com.dameng.system.service.RoleService;
import com.dameng.system.service.UserService;
import com.dameng.system.utils.RouterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Description: 登录相关服务实现类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022-7-13 16:42
 **/

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public R login(LoginForm loginForm) {
        User user = null;
        if (loginForm.getIsCode()==0){
            user = userService.getByUsernameOrPhone(loginForm.getUsername(),loginForm.getPhone(),loginForm.getPassword());
        }else {
            //todo.判断短信验证码是否正确
            user = userService.getOne(new QueryWrapper<User>().eq("phone",loginForm.getPhone()));
        }
        if (StringUtils.isNull(user)){
            return R.error().message("用户不存在");
        }
        Role role = roleService.getRoleByUserId(user.getId());

        List<Long> roleIds = new ArrayList<>();
        roleIds.add(role.getId());
        List<String> permissions = permissionService.getPerValuesByRoleIds(roleIds);

        com.dameng.common.security.entity.User sUser = new com.dameng.common.security.entity.User();
        com.dameng.common.security.entity.Role sRole = new com.dameng.common.security.entity.Role();
        BeanUtils.copyProperties(user,sUser);
        BeanUtils.copyProperties(role,sRole);

        redisUtil.hset(RedisKeyConst.ADMIN_USER,user.getUsername(),sUser);
        redisUtil.hset(RedisKeyConst.ADMIN_ROLE,user.getUsername(),sRole);
        redisUtil.hset(RedisKeyConst.ADMIN_PERMISSION,user.getUsername(),permissions);

        Map<String, Object> map = new HashMap<>(8);
        map.put(TokenConst.USER_NAME_KEY,user.getUsername());
        map.put(TokenConst.USER_ID_KEY,user.getId());
        String token = JwtUtils.createToken(map);
        return R.ok().data(TokenConst.TOKEN_NAME,token);
    }

    @Override
    public R info() {
        SecurityUser user = UserUtil.getUser();
        com.dameng.common.security.entity.User userInfo = user.getCurrentUserInfo();
        userInfo.setPassword("");
        return R.ok().data("user",userInfo).data("role",user.getRole()).data("permission",user.getPermissionValueList());
    }

    @Override
    public R getRoute() {
        SecurityUser user = UserUtil.getUser();
        List<Permission> permissions = permissionService.getListByRoleId(user.getRole().getId());
        permissions = permissions.stream().filter(p -> p.getType()==1).collect(Collectors.toList());
        List<Routers> routers = RouterUtils.buildRouters(permissions);
        return R.ok().data("route",routers);
    }

}
