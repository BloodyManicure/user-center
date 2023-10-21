package com.lawrence.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lawrence.usercenter.model.domain.User;
import com.lawrence.usercenter.service.UserService;
import com.lawrence.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author mjc
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-21 21:32:35
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




