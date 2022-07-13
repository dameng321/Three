package com.dameng.common.security.handler;

import com.dameng.common.core.result.R;
import com.dameng.common.core.result.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: 未授权处理类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/5/28 14:50
 **/

@Slf4j
public class UnAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.warn("未授权处理");
        ResponseUtil.out(response, R.error().message("抱歉,您没有权限！"));
    }
}
