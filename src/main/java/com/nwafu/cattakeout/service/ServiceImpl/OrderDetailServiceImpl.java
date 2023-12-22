package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.mapper.OrderDetailMapper;
import com.nwafu.cattakeout.pojo.OrderDetail;
import com.nwafu.cattakeout.service.IOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
}
