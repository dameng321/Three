package com.dameng.system.utils;

import com.dameng.system.entity.Permission;
import com.dameng.system.entity.dto.Meta;
import com.dameng.system.entity.dto.Routers;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 路由转换工具类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022-7-21 16:03
 **/

public class RouterUtils {

    public static List<Routers> buildRouters(List<Permission> permissionList) {
        List<Routers> resultRouters = new ArrayList<>();
        for (Permission permission : permissionList) {
            if (permission.getPid()==1L) {
                Routers routers = buildRouter(permission);
                resultRouters.add(buildChildren(permissionList, routers));
            }
        }
        return resultRouters;
    }


    private static Routers buildRouter(Permission permission) {
        Routers router = new Routers();
        router.setId(permission.getId());
        router.setPid(permission.getPid());
        router.setPath(permission.getPath());
        router.setRedirect(permission.getRedirect());
        router.setName(permission.getName());
        router.setComponent(permission.getComponent());
        router.setPermissionValue(permission.getPermissionValue());
        router.setSort(permission.getSort());
        router.setType(permission.getType());
        Meta meta = new Meta();
        meta.setTitle(permission.getName());
        if (permission.getIcon() != null) {
            meta.setIcon(permission.getIcon());
        } else {
            meta.setIcon("");
        }
        router.setMeta(meta);
        router.setHidden(permission.getStatus() == 1);
        if (router.getChildren() == null) {
            router.setChildren(new ArrayList<>());
        }
        return router;
    }

    private static Routers buildChildren(List<Permission> menus, Routers routers) {
        for (Permission permission : menus) {
            if (routers.getId().equals(permission.getPid())) {
                Routers router = buildRouter(permission);
                routers.getChildren().add(buildChildren(menus, router));
            }
        }
        return routers;
    }
}
