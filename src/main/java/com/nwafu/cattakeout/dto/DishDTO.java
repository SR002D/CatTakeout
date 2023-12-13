package com.nwafu.cattakeout.dto;

import com.nwafu.cattakeout.pojo.Dish;
import com.nwafu.cattakeout.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
