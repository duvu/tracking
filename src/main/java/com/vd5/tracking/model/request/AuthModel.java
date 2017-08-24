package com.vd5.tracking.model.request;

import lombok.Data;

/**
 * @author beou on 8/22/17 15:15
 * @version 1.0
 */
@Data
public class AuthModel {
    private String userName;
    private String password;

    @Override
    public String toString() {
        return "LoginModel{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
