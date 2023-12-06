package com.nwafu.cattakeout.common;

import org.springframework.stereotype.Component;

/**
 * 全局线程储存，用于存储当前登录的用户
 */
@Component
public class UserHolder {
    private static final ThreadLocal<Long> tl = new ThreadLocal<>();

    public static Long get(){
        return tl.get();
    }

    public static void set(Long id){
        tl.set(id);
    }
}
