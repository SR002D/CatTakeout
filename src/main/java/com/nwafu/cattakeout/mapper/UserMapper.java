package com.nwafu.cattakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwafu.cattakeout.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
