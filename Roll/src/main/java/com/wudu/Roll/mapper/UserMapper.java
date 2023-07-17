package com.wudu.Roll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wudu.Roll.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户表对应的持久层接口
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
