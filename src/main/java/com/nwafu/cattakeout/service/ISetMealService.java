package com.nwafu.cattakeout.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nwafu.cattakeout.dto.SetMealDTO;
import com.nwafu.cattakeout.pojo.SetMeal;

public interface ISetMealService extends IService<SetMeal> {
    void selectAllSetMealDTO(IPage<SetMealDTO> page,String name);

    SetMealDTO getWithDishById(Long id);

    void saveWithDish(SetMealDTO setMealDTO);

    void updateWithDish(SetMealDTO setMealDTO);

    void deleteWithDish(Long[] ids);
}
