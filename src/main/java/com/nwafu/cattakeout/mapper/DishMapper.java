package com.nwafu.cattakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nwafu.cattakeout.dto.DishDTO;
import com.nwafu.cattakeout.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    IPage<DishDTO> selectAllDishDTO(IPage<DishDTO> page,String name);

//    @Select("select test1.id, data. id, data. year, data. month     from test1,data    where test1.id = data.test_id")
//    List<Test> demo22();
}
