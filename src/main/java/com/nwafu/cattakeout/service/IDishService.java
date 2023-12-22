package com.nwafu.cattakeout.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nwafu.cattakeout.dto.DishDTO;
import com.nwafu.cattakeout.pojo.Dish;

public interface IDishService extends IService<Dish> {
    /**
     * 获取全部带口味的菜品
     * @param page 页面
     * @param name 模糊查询的名字
     */
    void selectAllDishDTO(IPage<DishDTO> page, String name);

    /**
     * 查询带口味的菜品
     * @param id 菜品id
     * @return 带口味的菜品
     */
    DishDTO getWithFlavorById(Long id);

    /**
     * 保存带口味的菜品
     * @param dishDTO 带口味的菜品
     */
    void saveWithFlavor(DishDTO dishDTO);

}
