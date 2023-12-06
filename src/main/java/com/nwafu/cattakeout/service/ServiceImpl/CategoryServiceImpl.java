package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.mapper.CategoryMapper;
import com.nwafu.cattakeout.pojo.Category;
import com.nwafu.cattakeout.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
