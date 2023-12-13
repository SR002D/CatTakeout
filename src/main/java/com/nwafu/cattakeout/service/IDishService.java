package com.nwafu.cattakeout.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nwafu.cattakeout.dto.DishDTO;
import com.nwafu.cattakeout.pojo.Dish;

public interface IDishService extends IService<Dish> {
    IPage<DishDTO> selectAllDishDTO(IPage<DishDTO> page);

    DishDTO getWithFlavorById(Long id);

}
