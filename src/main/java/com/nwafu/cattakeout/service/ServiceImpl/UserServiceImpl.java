package com.nwafu.cattakeout.service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwafu.cattakeout.mapper.UserMapper;
import com.nwafu.cattakeout.pojo.User;
import com.nwafu.cattakeout.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
