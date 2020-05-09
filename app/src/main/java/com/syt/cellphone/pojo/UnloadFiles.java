package com.syt.cellphone.pojo;

import java.util.List;

/**
 * @author shenyutian
 * @data 2020/5/8 6:45 PM
 * 功能 上传多个文件的承接
 */
public class UnloadFiles {

    /**
     * code : 0
     * data : ["http://47.115.43.738001/cw.png"]
     * error : []
     */

    private int code;
    private List<String> data;
    private List<String> error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}
