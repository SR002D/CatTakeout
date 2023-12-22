package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.mapper.AddressBookMapper;
import com.nwafu.cattakeout.pojo.AddressBook;
import com.nwafu.cattakeout.service.IAddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

}
