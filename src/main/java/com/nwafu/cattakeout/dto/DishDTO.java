package com.nwafu.cattakeout.dto;


import com.nwafu.cattakeout.entity.Dish;
import com.nwafu.cattakeout.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    //菜品对应的口味数据
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
