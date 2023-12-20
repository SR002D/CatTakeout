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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorServiceImpl dishFlavorService;

    @Override
    public IPage<DishDTO> selectAllDishDTO(IPage<DishDTO> page,String name){
        name = "%" + name +"%";
        page = dishMapper.selectAllDishDTO(page,name);
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

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO){
        //保存菜品的基本信息到菜品表dish
        this.save(dishDTO);
        Long dishId = dishDTO.getId();//菜品id
        //菜品口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }
}
