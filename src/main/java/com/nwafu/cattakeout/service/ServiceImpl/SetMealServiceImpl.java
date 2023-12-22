package com.nwafu.cattakeout.service.ServiceImpl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.dto.SetMealDTO;
import com.nwafu.cattakeout.mapper.SetMealMapper;
import com.nwafu.cattakeout.pojo.SetMeal;
import com.nwafu.cattakeout.pojo.SetMealDish;
import com.nwafu.cattakeout.service.ISetMealDishService;
import com.nwafu.cattakeout.service.ISetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, SetMeal> implements ISetMealService {
    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private ISetMealDishService setMealDishService;

    @Override
    public void selectAllSetMealDTO(IPage<SetMealDTO> page,String name){
        name = "%"+name+"%";
        setMealMapper.selectAllSetMealDTO(page,name);
    }

    @Override
    public SetMealDTO getWithDishById(Long id){
        //查询套餐基本信息，从set_meal表查询
        SetMeal setMeal = this.getById(id);
        log.info("setMeal:{}",setMeal);
        SetMealDTO setMealDTO = new SetMealDTO();
        BeanUtils.copyProperties(setMeal, setMealDTO);

        //查询当前套餐对应菜品，从set_meal_dish表查询
        LambdaQueryWrapper<SetMealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetMealDish::getSetMealId, id);
        queryWrapper.eq(SetMealDish::getIsDeleted, 0);
        List<SetMealDish> setMealDishes = setMealDishService.list(queryWrapper);
        setMealDTO.setSetMealDishes(setMealDishes);
        log.info("setMealDTO:{},{}",setMealDTO.getId(),setMealDTO.getName());
        return setMealDTO;
    }


    @Override
    @Transactional
    public void saveWithDish(SetMealDTO setMealDTO){
        //保存套餐基本信息到set_meal表
        this.save(setMealDTO);
        Long setMealId = setMealDTO.getId();//套餐id
        //套餐菜品
        List<SetMealDish> setMealDTODishes = setMealDTO.getSetMealDishes();
        setMealDTODishes = setMealDTODishes.stream().peek((item) -> item.setSetMealId(setMealId)).collect(Collectors.toList());
        log.info("setMealDishes:{}",setMealDTODishes);
        //保存菜品数据到套餐菜品表set_meal_dish
        setMealDishService.saveBatch(setMealDTODishes);
    }

    @Override
    @Transactional
    public void updateWithDish(SetMealDTO setMealDTO){
        // 更新套餐基本信息
        this.updateById(setMealDTO);
        // 查询原套餐菜品信息
        LambdaQueryWrapper<SetMealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetMealDish::getSetMealId,setMealDTO.getId());
        List<SetMealDish> setMealDishes = setMealDishService.list(queryWrapper);
        // 将原数据库套餐菜品信息全部删除
        setMealDishes = setMealDishes.stream().peek((item) -> item.setIsDeleted(1)).collect(Collectors.toList());
        setMealDishService.updateBatchById(setMealDishes);
        // 保存最新的套餐菜品
        List<SetMealDish> setMealDishes2 = setMealDTO.getSetMealDishes();
        // 更新setMealId
        setMealDishes2 = setMealDishes2.stream().peek((item) -> item.setSetMealId(setMealDTO.getId())).collect(Collectors.toList());
        log.info("setMeadDishes:{}",setMealDishes2);
        setMealDishService.saveBatch(setMealDishes2);
    }

    @Override
    @Transactional
    public void deleteWithDish(Long[] ids){
        for(Long i : ids){
            // 删除套餐
            LambdaUpdateWrapper<SetMeal> setMealUpdateWrapper = new LambdaUpdateWrapper<>();
            setMealUpdateWrapper.set(SetMeal::getIsDeleted,1);
            setMealUpdateWrapper.eq(SetMeal::getId,i);
            this.update(setMealUpdateWrapper);
            // 级联删除套餐菜品
            LambdaUpdateWrapper<SetMealDish> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(SetMealDish::getIsDeleted,1);
            updateWrapper.eq(SetMealDish::getSetMealId,i);
            setMealDishService.update(updateWrapper);
        }
    }
}
