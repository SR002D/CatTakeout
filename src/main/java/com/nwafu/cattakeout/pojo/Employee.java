package com.nwafu.cattakeout.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Employee {
    private Long id;
    private String username;
    private String name;
    private Integer sex;        //0女，1男
    private String phone;
    private String password;
    private String idNumber;    //身份证号
    private Integer status;      //0为账号禁用
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
