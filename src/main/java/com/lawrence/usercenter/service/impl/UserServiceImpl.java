package com.lawrence.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lawrence.usercenter.model.domain.User;
import com.lawrence.usercenter.service.UserService;
import com.lawrence.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
* @author mjc
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-21 21:32:35
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }
        // 2. 账户不能包含特殊字符
        String validPattern = "^[a-zA-Z0-9]+$";
        if (!userAccount.matches(validPattern)) {
            return -1;
        }
        // 3. 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 4. 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 1) {
            return -1;
        }
        // 加密
        final String SALT = "yupi";
        String encryptPassword = DigestUtils.md5DigestAsHex(("123456" + SALT).getBytes());
        // 数据库操作
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        int res = userMapper.insert(user);
        if(res == 0) {
            return -1;
        }
        return user.getId();
    }
}




