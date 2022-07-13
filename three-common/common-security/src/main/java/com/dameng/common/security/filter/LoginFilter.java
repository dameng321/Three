package com.dameng.common.security.filter;

import cn.hutool.json.JSONUtil;
import com.dameng.common.core.consts.TokenConst;
import com.dameng.common.core.result.R;
import com.dameng.common.core.result.ResponseUtil;
import com.dameng.common.core.result.ResultEnum;
import com.dameng.common.core.utils.JwtUtils;
import com.dameng.common.core.utils.RedisUtil;
import com.dameng.common.security.entity.SecurityUser;
import com.dameng.common.security.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 认证过滤器</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/5/30 13:34
 **/

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisUtil redisUtil;
    private AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager, RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/login","POST"));
    }

    /**
     * Description： 获取表单提交的用户名密码
     * @param request 1
     * @param response 2
     * @version 0.1.0
     * @return org.springframework.security.core.Authentication
     * @author dameng
     * @date 2021/5/30 13:37
     * @since 0.1.0
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.warn("认证过滤器：attemptAuthentication 获取表单提交的用户名密码");

        try {
            System.out.println(JSONUtil.parseObj(request.getInputStream()));
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            System.out.println(user);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Description： 认证成功调用的方法
     * @param request 1
     * @param response 2
     * @param chain 3
     * @param authResult 4
     * @version 0.1.0
     * @return void
     * @author dameng
     * @date 2021/5/30 13:40
     * @since 0.1.0
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println(request.getRequestURI());
        log.warn("认证过滤器：successfulAuthentication 认证成功的调用");
        //认证成功，得到认证成功之后用户信息
        SecurityUser user = (SecurityUser)authResult.getPrincipal();
        //根据用户名生成token
        Map<String, Object> map = new HashMap<>(8);
        map.put(TokenConst.USER_NAME_KEY,user.getUsername());
        map.put(TokenConst.USER_ID_KEY,user.getCurrentUserInfo().getId());

        String token = JwtUtils.createToken(map);
        //把用户名称和用户权限列表放到redis
        //redisUtil.set(user.getUsername(),user.getPermissionValueList());
        //返回token
        ResponseUtil.out(response, R.ok().data(TokenConst.TOKEN_NAME,token));
//        super.successfulAuthentication(request, response, chain, authResult);
    }

    /**
     * Description： 认证失败的方法
     * @param request 1
     * @param response 2
     * @param failed 3
     * @version 0.1.0
     * @return void
     * @author dameng
     * @date 2021/5/30 13:40
     * @since 0.1.0
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.warn("认证过滤器：successfulAuthentication 认证失败的调用");
        //super.unsuccessfulAuthentication(request, response, failed);
        ResponseUtil.out(response, R.error(ResultEnum.USERNAME_OR_PASSWORD_ERROR));
    }
}
