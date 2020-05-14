package com.syt.cellphone.pojo;

/**
 * @author shenyutian
 * @data 2020/3/18 9:48 AM
 * 功能 上传文件返回承接
 */
public class UploadFile {


    /**
     * code : 0
     * filepath : /root/upload/IMG_20191227_103246.jpg
     * data : {"src":"http://47.115.43.73:8001/IMG_20191227_103246.jpg"}
     */

    private int code;
    private String filepath;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * src : http://47.115.43.73:8001/IMG_20191227_103246.jpg
         */

        private String src;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
