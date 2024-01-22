package com.nwafu.cattakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwafu.cattakeout.pojo.Orders;

public interface IOrdersService extends IService<Orders> {
    /**
     * 提交订单
     * @param orders 订单详情
     */
    void submit(Orders orders);
}
