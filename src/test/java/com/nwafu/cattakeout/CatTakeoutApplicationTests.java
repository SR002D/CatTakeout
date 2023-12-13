package com.nwafu.cattakeout;

import com.nwafu.cattakeout.mapper.DishMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CatTakeoutApplicationTests {
    @Autowired
    private DishMapper dishMapper;

    @Test
    void contextLoads() {

    }

}
