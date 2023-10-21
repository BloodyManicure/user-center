package com.lawrence.usercenter.service;

import com.lawrence.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author mjc
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-21 21:32:35
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 账号
     * @param userPassword 密码
     * @param checkPassword 二次密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
