package com.dameng.common.security.filter;

import com.dameng.common.core.consts.RedisKeyConst;
import com.dameng.common.core.consts.TokenConst;
import com.dameng.common.core.utils.JwtUtils;
import com.dameng.common.core.utils.RedisUtil;
import com.dameng.common.security.entity.Role;
import com.dameng.common.security.entity.SecurityUser;
import com.dameng.common.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>Description: 授权过滤器</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/5/30 13:42
 **/

@Slf4j
public class AuthorizeFilter extends BasicAuthenticationFilter {

    private RedisUtil redisUtil;

    public AuthorizeFilter(AuthenticationManager authenticationManager, RedisUtil redisUtil) {
        super(authenticationManager);
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //log.warn("授权过滤器: doFilterInternal开始");

        //获取当前认证成功的用户
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        //判断是否有权限信息若有放入权限上下文中
//        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
        //log.warn("授权过滤器: doFilterInternal结束");
        chain.doFilter(request, response);
    }

    /**
     * Description： 获取当前认证成功的用户
     *
     * @param request 1
     * @return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     * @version 0.1.0
     * @author dameng
     * @date 2021/5/30 14:10
     * @since 0.1.0
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader(TokenConst.TOKEN_NAME);

        if(token != null) {
            //从token获取用户名
            String username = JwtUtils.getUserName(token);

            User user = (User) redisUtil.hget(RedisKeyConst.ADMIN_USER, username);
            Role role = (Role) redisUtil.hget(RedisKeyConst.ADMIN_ROLE, username);
            List<String> permissionValueList = (List<String>)redisUtil.hget(RedisKeyConst.ADMIN_PERMISSION, username);

            SecurityUser securityUser = new SecurityUser();
            securityUser.setCurrentUserInfo(user);
            securityUser.setRole(role);
            securityUser.setPermissionValueList(permissionValueList);

            Collection<GrantedAuthority> authority = new ArrayList<>();
            for(String permissionValue : permissionValueList) {
                SimpleGrantedAuthority auth = new SimpleGrantedAuthority(permissionValue);
                authority.add(auth);
            }
            return new UsernamePasswordAuthenticationToken(securityUser,token,authority);
        }
        return null;
    }


}
