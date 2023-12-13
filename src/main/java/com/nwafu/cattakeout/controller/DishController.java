package com.nwafu.cattakeout.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.dto.DishDTO;
import com.nwafu.cattakeout.pojo.Dish;
import com.nwafu.cattakeout.service.ICategoryService;
import com.nwafu.cattakeout.service.IDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    IDishService dishService;
    @Autowired
    ICategoryService categoryService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result page(int page, int pageSize){
        Page<DishDTO> pageInfo = new Page<>(page,pageSize);

        log.info("分页查询菜品：{}，{}",page,pageSize);
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.orderByAsc(Dish::getSort);
//        dishService.page(pageInfo,queryWrapper);
        dishService.selectAllDishDTO(pageInfo);
        log.info("pageInfo:{}",pageInfo);
        return Result.success(pageInfo);
    }

    /**
     * 更新菜品状态（起售、停售）
     */
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status,Long ids){
        log.info("菜品状态更新：{},{}",ids,status);
        Dish dish = new Dish();
        dish.setId(ids);
        dish.setStatus(status);
        dishService.updateById(dish);
        return Result.success("更新状态成功");
    }

    /**
     *  删除菜品
     */
    @DeleteMapping()
    public Result delete(Long ids){
        log.info("菜品删除：{}",ids);
        Dish dish = new Dish();
        dish.setId(ids);
        dish.setIsDeleted(1);
        dishService.updateById(dish);
        return Result.success();
    }

    /**
     * 更新菜品
     */
    @PutMapping()
    public Result update(@RequestBody Dish dish){
        //TODO updateWithFlavor
//        log.info("更新菜品：{}",dish);
//        dishService.updateById(dish);
        return Result.success();
    }

    /**
     * 查询菜品信息
     */
    @GetMapping("/{id}")
    public Result query(@PathVariable Long id){
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Dish::getId,id);
//        Dish dish = dishService.getOne(queryWrapper);
//        log.info("查询菜品：{}",dish);
//        return Result.success(dish);

        DishDTO dishDto = dishService.getWithFlavorById(id);

        return Result.success(dishDto);
    }



}
