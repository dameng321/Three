package com.dameng.common.security.handler;

import com.dameng.common.core.result.R;
import com.dameng.common.core.result.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: 不允许访问处理类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/5/28 14:50
 **/

@Slf4j
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.debug("不允许访问");
        ResponseUtil.out(response, R.error().message("不允许访问！"));
    }

}
