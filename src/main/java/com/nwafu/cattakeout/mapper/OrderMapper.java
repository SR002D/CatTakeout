package com.nwafu.cattakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwafu.cattakeout.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
