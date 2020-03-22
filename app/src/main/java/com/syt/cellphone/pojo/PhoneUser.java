package com.syt.cellphone.pojo;

/**
 * @author ：syt
 * @date ：Created in 2019-10-06 19:20
 * @description：用户类，用于保存登录
 */
public class PhoneUser {

    /**
     * userPhonenumber      手机号
     * userPortrait         头像
     * userEmail            邮箱
     * message              状态码 用来接收登录信息的
     * verificationNumber   验证码，用来传给服务端的
     */
    private Integer userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private String userPhonenumber;

    private String userPortrait;

    private String token;

    private String message;

    private String verificationNumber;

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

    public String getUserPass() {
        return userPassword;
    }

    public void setUserPass(String userPass) {
        this.userPassword = userPass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public String getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(String verificationNumber) {
        this.verificationNumber = verificationNumber;
    }
}