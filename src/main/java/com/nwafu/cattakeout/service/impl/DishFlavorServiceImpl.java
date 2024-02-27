package com.nwafu.cattakeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.entity.DishFlavor;
import com.nwafu.cattakeout.mapper.DishFlavorMapper;
import com.nwafu.cattakeout.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
