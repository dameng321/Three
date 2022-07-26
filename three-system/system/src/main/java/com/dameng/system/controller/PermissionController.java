package com.dameng.system.controller;

import com.dameng.common.core.result.R;
import com.dameng.system.entity.form.MenuForm;
import com.dameng.system.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@RestController
@Api(tags = "菜单权限接口")
@RequestMapping("/system/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("获取全部菜单")
    @GetMapping("/getMenus")
    public R getMenu() {
        return permissionService.getMenus();
    }

    @ApiOperation("添加菜单")
    @PostMapping("/")
    public R save(@RequestBody MenuForm menuForm) {
        return permissionService.saveMenu(menuForm);
    }

    @ApiOperation("添加菜单")
    @PutMapping("/")
    public R updateMenu(@RequestBody MenuForm menuForm) {
        return permissionService.updateMenu(menuForm);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public R deleteMenu(@PathVariable("id") Long id) {
        return permissionService.deleteMenu(id);
    }

    @ApiOperation("获取角色的菜单")
    @GetMapping("/getRoleMenus/{roleId}")
    public R getRoleMenus(@PathVariable Long roleId) {
        return permissionService.getMenuByRoleId(roleId);
    }

}
