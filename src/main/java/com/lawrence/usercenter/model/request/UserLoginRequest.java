package com.lawrence.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = -6200869174692385536L;

    private String userAccount;

    private String userPassword;

}
