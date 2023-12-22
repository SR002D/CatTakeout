package com.nwafu.cattakeout.pojo;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String phone;
    private Integer sex;
    private String idNumber;
    private String avatar;      // 头像
    private Integer status;
}
