package com.nwafu.cattakeout.dto;


import com.nwafu.cattakeout.entity.Setmeal;
import com.nwafu.cattakeout.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
