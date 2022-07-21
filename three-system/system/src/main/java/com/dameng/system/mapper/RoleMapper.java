package com.dameng.system.mapper;

import com.dameng.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * Description: 通过userid获取
     * @param userId 1
     * @return com.dameng.system.entity.Role
     * @author dameng
     * @date 2022-7-13 下午 05:59
     * @since 0.1.0
     */
    Role selectByUserId(Long userId);
}
