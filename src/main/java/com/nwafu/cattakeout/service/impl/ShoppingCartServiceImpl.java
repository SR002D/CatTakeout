package com.nwafu.cattakeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.entity.ShoppingCart;
import com.nwafu.cattakeout.mapper.ShoppingCartMapper;
import com.nwafu.cattakeout.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
