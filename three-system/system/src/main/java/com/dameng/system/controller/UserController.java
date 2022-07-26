package com.dameng.system.controller;

import com.dameng.common.core.result.R;
import com.dameng.system.entity.form.UserForm;
import com.dameng.system.entity.query.UserQuery;
import com.dameng.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户列表")
    @PostMapping("/list/{current}/{size}")
    public R list(@PathVariable("current") Integer current, @PathVariable("size") Integer size, @RequestBody UserQuery userQuery){
        return userService.pageList(current,size,userQuery);
    }

    @ApiOperation("添加用户")
    @PostMapping("/")
    public R save(@Validated @RequestBody UserForm userForm){
        return userService.saveUser(userForm);
    }

    @ApiOperation("修改用户")
    @PutMapping("/")
    public R update(@RequestBody UserForm userForm){
        if (userForm.getId() == null){
            return R.error().message("id不能为空");
        }
        return userService.updateUser(userForm);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Long> ids){
        return userService.deleteUser(ids);
    }
}
