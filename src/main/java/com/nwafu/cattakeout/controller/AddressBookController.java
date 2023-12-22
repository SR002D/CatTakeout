package com.nwafu.cattakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.common.UserHolder;
import com.nwafu.cattakeout.pojo.AddressBook;
import com.nwafu.cattakeout.service.IAddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private IAddressBookService addressBookService;

    /**
     * 获取默认地址
     */
    @GetMapping("/default")
    public Result getDefault(){
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getIsDefault,1);
        AddressBook addressBook = addressBookService.getOne(queryWrapper);
        return Result.success(addressBook);
    }

    /**
     * 新增
     */
    @PostMapping
    public Result save(@RequestBody AddressBook addressBook) {
        log.info("addressBook save:{}",addressBook);
        addressBook.setUserId(UserHolder.get());
        addressBookService.save(addressBook);
        return Result.success();
    }

    /**
     * 设置默认地址
     */
    @Transactional
    @PutMapping("default")
    public Result setDefault(@RequestBody AddressBook addressBook) {
        // 将现在这个地址设置为默认
        addressBook.setIsDefault(1);
        // 将所有地址设置为一般
        LambdaUpdateWrapper<AddressBook> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(AddressBook::getIsDefault,0);
        updateWrapper.eq(AddressBook::getUserId,UserHolder.get());
        addressBookService.update(updateWrapper);
        // 设置默认地址
        addressBookService.updateById(addressBook);
        return  Result.success();
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        return Result.success(addressBook);
    }

    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public Result list(AddressBook addressBook) {
        log.info("list addressBook:{}",addressBook);
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, UserHolder.get());
        List<AddressBook> lists = addressBookService.list(queryWrapper);
        return Result.success(lists);
    }
}
