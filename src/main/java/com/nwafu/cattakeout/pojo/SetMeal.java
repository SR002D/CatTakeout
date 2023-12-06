package com.nwafu.cattakeout.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SetMeal {
    private Long id;
    private Long categoryId;
    private String name;
    private BigDecimal price;       //使用BigDecimal计算钱数
    private Integer status;         //状态：0停售，1起售
    private String code;            //代码
    private String description;     //描述
    private String image;           //图片
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    private Integer isDeleted;      //是否三处
}
