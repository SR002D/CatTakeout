package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.common.UserHolder;
import com.nwafu.cattakeout.mapper.OrdersMapper;
import com.nwafu.cattakeout.pojo.*;
import com.nwafu.cattakeout.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>  implements IOrdersService {
    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IAddressBookService addressBookService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderDetailService orderDetailService;

    public void submit(Orders orders){
        // 获取购物车信息
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, UserHolder.get());
        ShoppingCart shoppingCart = shoppingCartService.getOne(queryWrapper);
        // 更新order
        orders.setNumber(UUID.randomUUID().toString());
        orders.setStatus(1);
        orders.setUserId(UserHolder.get());
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setAmount(shoppingCart.getAmount());
        // 从address_book中更新
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getAddress4String());
        orders.setConsignee(addressBook.getConsignee());
        User user= userService.getById(UserHolder.get());
        orders.setUserName(user.getName());
        // 保存order
        this.save(orders);
        // 更新order_detail
        OrderDetail orderDetail = new OrderDetail();
        // 把shoppingCart搬到order_detail
        BeanUtils.copyProperties(shoppingCart,orderDetail);
        orderDetail.setOrderId(orders.getId());
        orderDetail.setId(null);
        orderDetailService.save(orderDetail);

        // 清空购物车
        shoppingCartService.removeById(shoppingCart);
    }
}
