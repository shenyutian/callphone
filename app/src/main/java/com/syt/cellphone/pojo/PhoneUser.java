package com.syt.cellphone.pojo;

/**
 * @author ：syt
 * @date ：Created in 2019-10-06 19:20
 * @description：用户类，用于保存登录
 */
public class PhoneUser {
    private Integer userId;

    private String userName;

    private String token;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}