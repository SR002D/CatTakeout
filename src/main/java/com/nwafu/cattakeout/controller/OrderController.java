package com.nwafu.cattakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.pojo.Order;
import com.nwafu.cattakeout.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result page(int page, int pageSize,String number, LocalDateTime beginTime, LocalDateTime endTime){
        Page<Order> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(beginTime!=null&&endTime!=null,Order::getOrderTime,beginTime,endTime);
        queryWrapper.like(number!=null,Order::getNumber,number);
        orderService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 用户下单
     */
    @PostMapping("/submit")
    public Result submit(@RequestBody Order order){
        log.info("order:{}",order);
        orderService.submit(order);
        return Result.success();
    }
}
