package com.dameng.system.mapper;

import com.dameng.system.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * Description: 查询角色权限值
     * @param roleIds 1 
     * @return java.util.List<java.lang.String>
     * @author dameng
     * @date 2022-7-14 上午 09:48
     * @since 0.1.0
     */
    List<String> selectValuesInRoleIds(List<Long> roleIds);

    /**
     * Description: 通过角色id查询权限列表
     * @param roleId 1 
     * @return java.util.List<com.dameng.system.entity.Permission>
     * @author dameng
     * @date 2022-7-19 下午 01:08
     * @since 0.1.0
     */
    List<Permission> selectListByRoleId(Long roleId);
}
