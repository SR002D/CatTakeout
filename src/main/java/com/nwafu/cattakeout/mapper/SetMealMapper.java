package com.nwafu.cattakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nwafu.cattakeout.dto.SetMealDTO;
import com.nwafu.cattakeout.pojo.SetMeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetMealMapper extends BaseMapper<SetMeal> {
    IPage<SetMealDTO> selectAllSetMealDTO(IPage<SetMealDTO> page,String name);
}
