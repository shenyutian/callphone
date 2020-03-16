package com.syt.cellphone.pojo;

/**
 * @author shenyutian
 * @data 2020/3/16 2:26 PM
 * 功能 注册返回值
 */
public class Registered {


    /**
     * uid: 676,
     * userName : "syt"
     * token : "eyJ0eXAiOiJKV1QiLCJh"
     * msg : 3
     * errorMap : {"nameError":"用户名117已经被使用了","emailError":"邮箱358288839@qq.com已经被使用了","passError":"密码长度不合法"}
     */
    private int uid;
    private String userName;
    private String token;
    private int msg;
    private ErrorMapBean errorMap;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public ErrorMapBean getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(ErrorMapBean errorMap) {
        this.errorMap = errorMap;
    }

    public static class ErrorMapBean {
        /**
         * nameError : 用户名117已经被使用了
         * emailError : 邮箱358288839@qq.com已经被使用了
         * passError : 密码长度不合法
         * errorVerNum : 验证码不正确
         */

        private String nameError;
        private String emailError;
        private String passError;
        private String errorVerNum;

        public String getNameError() {
            return nameError;
        }

        public void setNameError(String nameError) {
            this.nameError = nameError;
        }

        public String getEmailError() {
            return emailError;
        }

        public void setEmailError(String emailError) {
            this.emailError = emailError;
        }

        public String getPassError() {
            return passError;
        }

        public void setPassError(String passError) {
            this.passError = passError;
        }

        public String getErrorVerNum() {
            return errorVerNum;
        }

        public void setErrorVerNum(String errorVerNum) {
            this.errorVerNum = errorVerNum;
        }
    }
}
