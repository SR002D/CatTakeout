package com.nwafu.cattakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwafu.cattakeout.pojo.Order;

public interface IOrderService extends IService<Order> {
    /**
     * 提交订单
     * @param order 订单详情
     */
    void submit(Order order);
}
