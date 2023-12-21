package com.nwafu.cattakeout.dto;

import com.nwafu.cattakeout.pojo.SetMeal;
import com.nwafu.cattakeout.pojo.SetMealDish;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SetMealDTO extends  SetMeal {
    private List<SetMealDish> setmealDishes= new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
