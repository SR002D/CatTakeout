package com.nwafu.cattakeout.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * MP的自动填充策略
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("createUser", UserHolder.get());
        metaObject.setValue("updateUser", UserHolder.get());
    }

    /**
     * 更新操作
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser", UserHolder.get());
    }
}
