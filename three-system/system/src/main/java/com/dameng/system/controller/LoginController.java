package com.dameng.system.controller;

import com.dameng.common.core.consts.TokenConst;
import com.dameng.common.core.result.R;
import com.dameng.common.core.utils.JwtUtils;
import com.dameng.common.core.utils.RedisUtil;
import com.dameng.system.entity.form.LoginForm;
import com.dameng.system.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 登录相关处理器</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022-7-12 17:43
 **/

@RestController
@Api(tags = "登录相关请求")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm loginForm){
        return loginService.login(loginForm);
    }

    @GetMapping("/info")
    @ApiOperation("获取当前登录用户信息")
    public R info(){
        return loginService.info();
    }

    @GetMapping("/router")
    @ApiOperation("获取当前登录用户的路由")
    public R router(){
        return loginService.getRoute();
    }

}
