package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.common.UserHolder;
import com.nwafu.cattakeout.mapper.OrderMapper;
import com.nwafu.cattakeout.pojo.AddressBook;
import com.nwafu.cattakeout.pojo.Order;
import com.nwafu.cattakeout.pojo.ShoppingCart;
import com.nwafu.cattakeout.pojo.User;
import com.nwafu.cattakeout.service.IAddressBookService;
import com.nwafu.cattakeout.service.IOrderService;
import com.nwafu.cattakeout.service.IShoppingCartService;
import com.nwafu.cattakeout.service.IUserService;
import com.nwafu.cattakeout.util.ValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order>  implements IOrderService {
    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IAddressBookService addressBookService;
    @Autowired
    private IUserService userService;

    public void submit(Order order){
        // 获取购物车信息
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, UserHolder.get());
        ShoppingCart shoppingCart = shoppingCartService.getOne(queryWrapper);
        // 更新order
        order.setNumber(ValidateCodeUtil.generateValidateCode4String(16));
        order.setStatus(1);
        order.setUserId(UserHolder.get());
        order.setOrderTime(LocalDateTime.now());
        order.setAmount(shoppingCart.getAmount());
        // 从address_book中更新
        Long addressBookId = order.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        order.setPhone(addressBook.getPhone());
        order.setAddress(addressBook.getAddress4String());
        order.setConsignee(addressBook.getConsignee());
        User user= userService.getById(UserHolder.get());
        order.setUserName(user.getName());
        // 保存order
        this.save(order);
        // TODO:更新order_detail
        // 把shoppingCart搬到order_detail

        // TODO:清空购物车
    }
}
