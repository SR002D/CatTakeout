package com.nwafu.cattakeout.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Orders {
    private Long id;
    private String number;      // 订单号
    private Integer status;     // 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
    private Long userId;
    private Long addressBookId;
    private LocalDateTime orderTime;    // 下单时间
    private LocalDateTime checkoutTime; // 结账时间
    private Integer payMethod;          // 支付方式：1、微信 2、支付宝
    private BigDecimal amount;
    private String remark;
    private String phone;
    private String address;
    private String userName;
    private String consignee;       // 收货人
}
