package com.nwafu.cattakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwafu.cattakeout.entity.Category;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);

}
