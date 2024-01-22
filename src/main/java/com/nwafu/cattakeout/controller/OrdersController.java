package com.nwafu.cattakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.pojo.Orders;
import com.nwafu.cattakeout.service.IOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService orderService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result page(int page, int pageSize,String number, LocalDateTime beginTime, LocalDateTime endTime){
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(beginTime!=null&&endTime!=null, Orders::getOrderTime,beginTime,endTime);
        queryWrapper.like(number!=null, Orders::getNumber,number);
        orderService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 用户下单
     */
    @PostMapping("/submit")
    public Result submit(@RequestBody Orders orders){
        log.info("order:{}", orders);
        orderService.submit(orders);
        return Result.success();
    }
}
