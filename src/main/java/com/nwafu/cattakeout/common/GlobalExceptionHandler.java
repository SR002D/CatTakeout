package com.nwafu.cattakeout.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * springboot的全局异常处理器
 */
@Slf4j
@RestController
public class GlobalExceptionHandler{
    /**
     * 处理全局异常
     */
    @ExceptionHandler(IOException.class)
    public Result exceptionHandler(IOException ex) {
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }
        return Result.error("未知错误");
    }
}
