package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.dto.DishDTO;
import com.nwafu.cattakeout.mapper.DishMapper;
import com.nwafu.cattakeout.pojo.Dish;
import com.nwafu.cattakeout.pojo.DishFlavor;
import com.nwafu.cattakeout.service.IDishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorServiceImpl dishFlavorService;

    @Override
    public IPage<DishDTO> selectAllDishDTO(IPage<DishDTO> page){
        page = dishMapper.selectAllDishDTO(page);
        return page;
    }
    @Override
    public DishDTO getWithFlavorById(Long id) {
        //查询菜品基本信息，从dish表查询
        Dish dish = this.getById(id);

        DishDTO dishDto = new DishDTO();
        BeanUtils.copyProperties(dish, dishDto);

        //查询当前菜品对应的口味信息，从dish_flavor表查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }
}
