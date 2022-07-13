package com.dameng.system.controller;

import com.dameng.common.core.consts.TokenConst;
import com.dameng.common.core.result.R;
import com.dameng.common.core.utils.JwtUtils;
import com.dameng.common.core.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    private RedisUtil redisUtil;

    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(){
        Map<String, Object> map = new HashMap<>(8);
        map.put(TokenConst.USER_NAME_KEY,"test");
        map.put(TokenConst.USER_ID_KEY,"10086");
        String token = JwtUtils.createToken(map);
        return R.ok().data(TokenConst.TOKEN_NAME,token);
    }

}
