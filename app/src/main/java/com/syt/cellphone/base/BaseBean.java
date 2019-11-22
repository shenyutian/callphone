package com.syt.cellphone.base;

import java.io.Serializable;

/**
 * author：syt
 * Date: 2019-11-22
 * 作用:
 */
public class BaseBean<T> implements Serializable {

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * data : {"name":"admin","password":"123456"}
     * ret : {"code":"200","msg":"登录成功"}
     */

    private T data;
    private RetBean ret;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    private String api;
    private String v;

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }


    public static class RetBean {
        /**
         * code : 200
         * msg : 登录成功
         */

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


}
