package com.syt.cellphone.pojo;

/**
 * @author shenyutian
 * @data 2020-01-20 10:02
 * 功能 评价
 */
public class Estimate {
    /**
     * estimateId : 10
     * phoneId : 1293901
     * userId : 666
     * estimateGrade : null
     * estimateComment : 真的**辣鸡123da**as**gaga
     * estimateLike : null
     * estimateTime : 1583319143747
     */

    private int estimateId;
    private int phoneId;
    private int userId;
    private String estimateGrade;
    private String estimateComment;
    private String estimateLike;
    private long estimateTime;

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

    public String getEstimateComment() {
        return estimateComment;
    }

    public void setEstimateComment(String estimateComment) {
        this.estimateComment = estimateComment;
    }

    public Object getEstimateLike() {
        return estimateLike;
    }

    public void setEstimateLike(String estimateLike) {
        this.estimateLike = estimateLike;
    }

    public long getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(long estimateTime) {
        this.estimateTime = estimateTime;
    }

    @Override
    public String toString() {
        return "Estimate{" +
                "estimateId=" + estimateId +
                ", phoneId=" + phoneId +
                ", userId=" + userId +
                ", estimateGrade=" + estimateGrade +
                ", estimateComment='" + estimateComment + '\'' +
                ", estimateLike=" + estimateLike +
                ", estimateTime=" + estimateTime +
                '}';
    }
}
