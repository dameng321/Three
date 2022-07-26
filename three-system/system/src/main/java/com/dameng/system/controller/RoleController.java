package com.dameng.system.controller;

import com.dameng.common.core.result.R;
import com.dameng.system.entity.form.RoleForm;
import com.dameng.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@RestController
@Api(tags = "角色管理接口")
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取角色列表")
    @GetMapping("/list/{current}/{size}")
    public R list(@PathVariable("current") Integer current, @PathVariable("size") Integer size){
        return roleService.pageList(current,size);
    }

    @ApiOperation("新增角色")
    @PostMapping("/")
    public R save(@RequestBody RoleForm roleForm){
        return roleService.saveRole(roleForm);
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public R update(@RequestBody RoleForm roleForm){
        return roleService.updateRole(roleForm);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable("ids") List<Long> ids){
        return roleService.deleteRole(ids);
    }

}
