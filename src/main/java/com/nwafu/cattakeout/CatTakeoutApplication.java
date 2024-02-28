package com.nwafu.cattakeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableCaching  // 开启spring cache
public class CatTakeoutApplication {

    public static void main(String[] args) {
        log.info("程序启动成功...");
        SpringApplication.run(CatTakeoutApplication.class, args);
    }
}
