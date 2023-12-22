package com.nwafu.cattakeout.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ShoppingCart {
    private Long id;
    private String name;
    private String image;
    private Long userId;
    private Long dishId;
    private Long setMealId;
    private String dishFlavor;
    private Integer number;
    private BigDecimal amount;
    private LocalDateTime createTime;
}
