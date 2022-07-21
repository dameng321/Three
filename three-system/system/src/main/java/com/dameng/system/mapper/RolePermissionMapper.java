package com.dameng.system.mapper;

import com.dameng.system.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}
