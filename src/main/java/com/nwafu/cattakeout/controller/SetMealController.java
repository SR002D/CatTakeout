package com.nwafu.cattakeout.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.dto.SetMealDTO;
import com.nwafu.cattakeout.pojo.SetMeal;
import com.nwafu.cattakeout.service.ISetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetMealController {
    @Autowired
    ISetMealService setMealService;
    /*
     * 分页查询
     */
    @GetMapping("/page")
    public Result page(int page, int pageSize, String name){
        Page<SetMealDTO> pageInfo = new Page<>(page,pageSize);
        setMealService.selectAllSetMealDTO(pageInfo, name);
        return Result.success(pageInfo);
    }

    /**
     * 更新套餐状态（起售、停售）
     */
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, Long[] ids){
        log.info("套餐状态更新：{},{}",ids,status);
        for (Long id : ids) {
            LambdaUpdateWrapper<SetMeal> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(SetMeal::getStatus,status);
            updateWrapper.eq(SetMeal::getId,id);
            setMealService.update(updateWrapper);
        }
        return Result.success("更新状态成功");
    }

    /**
     *  删除套餐
     */
    @DeleteMapping()
    public Result delete(Long[] ids){
        log.info("套餐删除：{}", (Object) ids);
        setMealService.deleteWithDish(ids);
        return Result.success("套餐删除完成");
    }

    /**
     * 查询菜品信息
     */
    @GetMapping("/{id}")
    public Result query(@PathVariable Long id){
        SetMealDTO setMealDTO = setMealService.getWithDishById(id);
        return Result.success(setMealDTO);
    }

    /*
     * 保存套餐
     */
    @PostMapping
    public Result save(@RequestBody SetMealDTO setMealDTO){
        log.info("套餐信息：{}",setMealDTO);
        setMealService.saveWithDish(setMealDTO);
        return Result.success();
    }
    /*
     * 更新套餐
     */
    @PutMapping
    public Result update(@RequestBody SetMealDTO setMealDTO){
        setMealService.updateWithDish(setMealDTO);
        return Result.success();
    }
}
