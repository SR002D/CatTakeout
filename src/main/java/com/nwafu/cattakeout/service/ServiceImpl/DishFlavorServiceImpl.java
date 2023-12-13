package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.mapper.DishFlavorMapper;
import com.nwafu.cattakeout.pojo.DishFlavor;
import com.nwafu.cattakeout.service.IDishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements IDishFlavorService {
}
