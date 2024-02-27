package com.nwafu.cattakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.nwafu.cattakeout.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
}
