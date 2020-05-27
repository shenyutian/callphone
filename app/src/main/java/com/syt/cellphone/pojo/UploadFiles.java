package com.syt.cellphone.pojo;

import java.util.List;

/**
 * @author shenyutian
 * @data 2020/5/8 6:45 PM
 * 功能 上传多个文件的承接
 */
public class UploadFiles {

    /**
     * {
     *     "code": 0,
     *     "data": [
     *         "http://47.115.43.73:8001/测试17.jpg",
     *         "http://47.115.43.73:8001/测试18.jpg"
     *     ],
     *     "error": []
     * }
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
