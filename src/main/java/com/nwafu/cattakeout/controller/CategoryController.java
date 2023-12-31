package com.nwafu.cattakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.pojo.Category;
import com.nwafu.cattakeout.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result page(int page,int pageSize){
        Page<Category> pageInfo = new Page<>(page,pageSize);
        log.info("分页查询：{},{}",page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 添加分类
     */
    @PostMapping()
    public Result add(@RequestBody Category category){
        log.info("添加分类：{}",category);
        categoryService.save(category);
        return Result.success();
    }

    /**
     * 更新分类
     */
    @PutMapping()
    public Result update(@RequestBody Category category){
        log.info("修改分类：{}",category);
        categoryService.updateById(category);
        return Result.success();
    }

    /**
     * 删除分类
     */
    @DeleteMapping()
    public Result delete(Long ids){
        log.info("删除分类：{}",ids);
        categoryService.removeById(ids);
        return Result.success("删除成功！");
    }

    /**
     * 根据条件查询分类数据
     */
    @GetMapping("/list")
    public Result list(Category category){
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);
        return Result.success(list);
    }

}
