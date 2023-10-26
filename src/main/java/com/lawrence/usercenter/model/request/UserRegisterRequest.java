package com.lawrence.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3343728790162764101L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
