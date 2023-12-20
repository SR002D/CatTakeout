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
        log.info("菜品删除：{}",ids);
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

}
