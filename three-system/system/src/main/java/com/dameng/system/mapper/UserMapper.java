package com.dameng.system.mapper;

import com.dameng.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author dameng
 * @since 2022-07-13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
