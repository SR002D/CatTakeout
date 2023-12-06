package com.nwafu.cattakeout.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Dish {
    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;   //使用BigDecimal计算钱数
    private String code;        //代码
    private String image;       //图片
    private String description;
    private Integer status;     //状态：0停售，1起售
    private Integer sort;       //顺序
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    private Integer isDeleted;  //是否删除
}
