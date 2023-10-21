package com.lawrence.usercenter.service;

import com.lawrence.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务测试
 * @author mch
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("mch");
        user.setUserAccount("admin");
        user.setGender(0);
        user.setAvatarUrl("");
        user.setUserPassword("123456");
        user.setPhone("18961112371");
        user.setEmail("lawrence02230711@gmail.com");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);
    }

    @Test
    void testDigest() throws NoSuchAlgorithmException {
        String newPassword = DigestUtils.md5DigestAsHex(("123456" + "mypassword").getBytes());
        System.out.println(newPassword);
    }

    @Test
    void userRegister() {
        String userAccount = "mjcheng";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        long res = userService.userRegister(userAccount, userPassword, checkPassword);
        System.out.println(res);
    }
}