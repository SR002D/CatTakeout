package com.nwafu.cattakeout.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nwafu.cattakeout.dto.SetMealDTO;
import com.nwafu.cattakeout.pojo.SetMeal;

public interface ISetMealService extends IService<SetMeal> {

    /**
     * 获取全部带有套餐菜品的套餐
     * @param page 组合的页面
     * @param name 模糊查询名字
     */
    void selectAllSetMealDTO(IPage<SetMealDTO> page,String name);

    /**
     * 查询带有套餐菜品的套餐
     * @param id 套餐id
     * @return   带有套餐菜品的套餐
     */
    SetMealDTO getWithDishById(Long id);

    /**
     * 保存新增的套餐
     * @param setMealDTO 带有套餐菜品的套餐
     */
    void saveWithDish(SetMealDTO setMealDTO);

    /**
     * 更新带有套餐菜品的套餐
     * @param setMealDTO 带有套餐菜品的套餐
     */
    void updateWithDish(SetMealDTO setMealDTO);

    /**
     * 级联删除套餐以及套餐菜品
     * @param ids 待删除的套餐id号，+
     */
    void deleteWithDish(Long[] ids);
}
