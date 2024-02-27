package com.nwafu.cattakeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.nwafu.cattakeout.entity.User;
import com.nwafu.cattakeout.mapper.UserMapper;
import com.nwafu.cattakeout.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
