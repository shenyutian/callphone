package com.syt.cellphone.pojo;

/**
 * @author shenyutian
 * @data 2020-01-20 10:02
 * 功能 评价
 */
public class Estimate {
    /**
     * estimateId : 1
     * phoneId : 1293901
     * userId : 666
     * estimateGrade : 真的辣鸡
     * estimateLike : 1
     */

    private int estimateId;
    private int phoneId;
    private int userId;
    private String estimateGrade;
    private int estimateLike;

    public int getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(int estimateId) {
        this.estimateId = estimateId;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEstimateGrade() {
        return estimateGrade;
    }

    public void setEstimateGrade(String estimateGrade) {
        this.estimateGrade = estimateGrade;
    }

    public int getEstimateLike() {
        return estimateLike;
    }

    public void setEstimateLike(int estimateLike) {
        this.estimateLike = estimateLike;
    }
}
