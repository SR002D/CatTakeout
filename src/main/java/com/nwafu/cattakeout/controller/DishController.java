package com.nwafu.cattakeout.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.dto.DishDTO;
import com.nwafu.cattakeout.pojo.Category;
import com.nwafu.cattakeout.pojo.Dish;
import com.nwafu.cattakeout.pojo.DishFlavor;
import com.nwafu.cattakeout.service.ICategoryService;
import com.nwafu.cattakeout.service.IDishFlavorService;
import com.nwafu.cattakeout.service.IDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private IDishService dishService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IDishFlavorService dishFlavorService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result page(int page, int pageSize, String name){
        Page<DishDTO> pageInfo = new Page<>(page,pageSize);
        log.info("分页查询菜品：{}，{}",page,pageSize);
        dishService.selectAllDishDTO(pageInfo,name);
        log.info("pageInfo:{}",pageInfo);
        return Result.success(pageInfo);
    }
    /*
     * 根据名字搜索
     */

    /**
     * 更新菜品状态（起售、停售）
     */
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status,Long[] ids){
        log.info("菜品状态更新：{},{}",ids,status);
        Dish dish = new Dish();
        for (Long id : ids) {
            dish.setId(id);
            dish.setStatus(status);
            dishService.updateById(dish);
        }
        return Result.success("更新状态成功");
    }

    /**
     *  删除菜品
     */
    @DeleteMapping()
    public Result delete(Long[] ids){
        log.info("菜品删除：{}", (Object) ids);
        Dish dish = new Dish();
        for(Long i: ids){
            dish.setId(i);
            dish.setIsDeleted(1);
            dishService.updateById(dish);
        }
        return Result.success("菜品删除完成");
    }

    /**
     * 更新菜品
     */
    @PutMapping()
    public Result update(@RequestBody Dish dish){
        log.info("更新菜品：{}",dish);
        dishService.updateById(dish);
        return Result.success();
    }

    /**
     * 新增菜品
     */
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO){
        log.info(dishDTO.toString());
        dishService.saveWithFlavor(dishDTO);
        return Result.success("新增菜品成功");
    }

    /**
     * 查询菜品信息
     */
    @GetMapping("/{id}")
    public Result query(@PathVariable Long id){
        DishDTO dishDTO = dishService.getWithFlavorById(id);
        return Result.success(dishDTO);
    }

    @GetMapping("/list")
    public Result list(Dish dish){
        //构造查询条件
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null ,Dish::getCategoryId,dish.getCategoryId());
        //添加条件，查询状态为1（起售状态）的菜品
        queryWrapper.eq(Dish::getStatus,1);

        //添加排序条件
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);

        List<DishDTO> dishDtoList = list.stream().map((item) -> {

            DishDTO dishDTO = new DishDTO();

            BeanUtils.copyProperties(item,dishDTO);

            Long categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryService.getById(categoryId);

            if(category != null){
                String categoryName = category.getName();
                dishDTO.setCategoryName(categoryName);
            }

            //当前菜品的id
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId,dishId);
            //SQL:select * from dish_flavor where dish_id = ?
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
            dishDTO.setFlavors(dishFlavorList);
            return dishDTO;
        }).collect(Collectors.toList());

        return Result.success(dishDtoList);
    }

}
