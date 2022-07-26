package com.dameng.system.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dameng.common.core.result.R;
import com.dameng.common.core.result.ResultEnum;
import com.dameng.system.entity.Permission;
import com.dameng.system.entity.dto.Routers;
import com.dameng.system.entity.form.MenuForm;
import com.dameng.system.mapper.PermissionMapper;
import com.dameng.system.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dameng.system.utils.RouterUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<String> getPerValuesByRoleIds(List<Long> roleIds) {
        return baseMapper.selectValuesInRoleIds(roleIds);
    }

    @Override
    public List<Permission> getListByRoleId(Long roleId) {
        return baseMapper.selectListByRoleId(roleId);
    }

    @Override
    public R getMenus() {
        List<Permission> permissions = baseMapper.selectList(new QueryWrapper<Permission>().orderByDesc("sort"));
        List<Routers> routers = RouterUtils.buildRouters(permissions);
        return R.ok().data("menu",routers);
    }

    @Override
    public R saveMenu(MenuForm menuForm) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(menuForm, permission);
        baseMapper.insert(permission);
        return R.ok(ResultEnum.ADD_SUCCESS);
    }

    @Override
    public R updateMenu(MenuForm menuForm) {
        if (menuForm.getId()==null){
            return R.error(ResultEnum.UPDATE_ERROR);
        }
        Permission permission = new Permission();
        BeanUtils.copyProperties(menuForm, permission);
        baseMapper.updateById(permission);
        return R.ok(ResultEnum.UPDATE_SUCCESS);
    }

    @Override
    public R deleteMenu(Long id) {
//        baseMapper.deleteById(id);
//        return R.ok(ResultEnum.DELETE_SUCCESS);
        return null;
    }

    @Override
    public R getMenuByRoleId(Long roleId) {
        List<Permission> permissions = baseMapper.selectListByRoleId(roleId);
        List<Routers> routers = RouterUtils.buildRouters(permissions);
        return R.ok().data("menu",routers);
    }
}
