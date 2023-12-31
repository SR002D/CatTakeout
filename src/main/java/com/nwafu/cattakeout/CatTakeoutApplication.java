package com.nwafu.cattakeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class CatTakeoutApplication {

    public static void main(String[] args) {
        log.info("程序启动成功...");
        SpringApplication.run(CatTakeoutApplication.class, args);
    }
}
