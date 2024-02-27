package com.nwafu.cattakeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.entity.OrderDetail;
import com.nwafu.cattakeout.mapper.OrderDetailMapper;
import com.nwafu.cattakeout.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}