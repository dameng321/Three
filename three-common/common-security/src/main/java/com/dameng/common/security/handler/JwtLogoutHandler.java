package com.dameng.common.security.handler;

import com.dameng.common.core.consts.TokenConst;
import com.dameng.common.core.result.R;
import com.dameng.common.core.result.ResponseUtil;
import com.dameng.common.core.utils.JwtUtils;
import com.dameng.common.core.utils.RedisUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: 退出登录处理器</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/5/28 14:26
 **/

@Component
public class JwtLogoutHandler implements LogoutHandler {

    private RedisUtil redisUtil;

    public JwtLogoutHandler( RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        //从header获取token
        String token = request.getHeader(TokenConst.TOKEN_NAME);
        if (token != null){
            String username = JwtUtils.getUserName(token);
            redisUtil.del(username);
            //删除token
            //jwtUtils.removeToken(token);
        }
        ResponseUtil.out(response, R.ok());
    }
}
