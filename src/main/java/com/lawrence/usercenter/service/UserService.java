package com.lawrence.usercenter.service;

import com.lawrence.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 用户登录
     * @param userAccount 账号
     * @param userPassword 密码
     * @return 用户脱敏信息
     */
    User doLogin(String userAccount, String userPassword, HttpServletRequest request);

    List<User> searchUsers(String username);

    boolean deleteUser(long id);

    /**
     * 用户脱敏
     * @param originUser 原始用户对象
     * @return 脱敏后的用户对象
     */
    User getSafetyUser(User originUser);
}
