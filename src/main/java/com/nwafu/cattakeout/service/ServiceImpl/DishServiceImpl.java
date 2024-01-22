package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.dto.DishDTO;
import com.nwafu.cattakeout.mapper.DishMapper;
import com.nwafu.cattakeout.pojo.Dish;
import com.nwafu.cattakeout.pojo.DishFlavor;
import com.nwafu.cattakeout.service.IDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorServiceImpl dishFlavorService;

    @Override
    public void selectAllDishDTO(IPage<DishDTO> page, String name){
        name = "%" + name +"%";
        dishMapper.selectAllDishDTO(page,name);
    }
    @Override
    public DishDTO getWithFlavorById(Long id) {
        //查询菜品基本信息，从dish表查询
        Dish dish = this.getById(id);
        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dish, dishDTO);
        log.info("dishDTO:{}",dishDTO);
        //查询当前菜品对应的口味信息，从dish_flavor表查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDTO.setFlavors(flavors);
        return dishDTO;
    }

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO){
        //保存菜品的基本信息到菜品表dish
        this.save(dishDTO);
        Long dishId = dishDTO.getId();//菜品id
        //菜品口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().peek((item) -> item.setDishId(dishId)).collect(Collectors.toList());
        //保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public void updateWithFlavor(DishDTO dishDTO){
        this.updateById(dishDTO);
        Long dishId = dishDTO.getId();//菜品id
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishId);
        // 先删除
        dishFlavorService.remove(queryWrapper);
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().peek((item) -> item.setDishId(dishId)).collect(Collectors.toList());
        // 后重新保存
        dishFlavorService.saveBatch(flavors);
    }
}
