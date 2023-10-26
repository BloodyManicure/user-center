package com.lawrence.usercenter.controller;

import com.lawrence.usercenter.model.domain.User;
import com.lawrence.usercenter.model.request.UserLoginRequest;
import com.lawrence.usercenter.model.request.UserRegisterRequest;
import com.lawrence.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.lawrence.usercenter.constant.UserConstant.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest == null) {
            return null;
        }
        String account = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAnyBlank(account, password, checkPassword)) {
            return null;
        }
        return userService.userRegister(account, password, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if(userLoginRequest == null) {
            return null;
        }
        String account = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(account, password)) {
            return null;
        }
        return userService.doLogin(account, password, request);
    }

    @GetMapping("/search")
    public List<User> searchUsers(String username, HttpServletRequest request) {
        if(isAdmin(request)) return new ArrayList<>();
        return userService.searchUsers(username);
    }

    @PostMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable long id, HttpServletRequest request) {
        if(isAdmin(request)) return false;
        return userService.deleteUser(id);
    }

    private boolean isAdmin(HttpServletRequest request) {
        // 鉴权：仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == DEFAULT_ROLE;
    }
}
