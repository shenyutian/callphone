package com.syt.cellphone.pojo;

/**
 * @author shenyutian
 * @data 2020-01-20 10:02
 * 功能 评价
 */
public class Estimate {

    /**
     * estimateId : 196
     * phoneId : 1293901
     * userId : 666
     * estimateGrade : 9.9
     * estimateComment : 真的**辣鸡123da**as**gaga
     * estimateLike : null
     * estimateObjectto : null
     * estimateTime : 1583717182858
     * model : null
     * userPortrait : "http:xxx.xxx.xyz.png"
     * userPermissions : 1
     */

    private int estimateId;
    private int phoneId;
    private int userId;
    private float estimateGrade;
    private String estimateComment;
    private int estimateLike;
    private int estimateObjectto;
    private long estimateTime;
    private String model;
    private String userPortrait;
    private String userPermissions;

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

    public Object getEstimateGrade() {
        return estimateGrade;
    }

    public void setEstimateGrade(float estimateGrade) {
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

    public void setEstimateLike(int estimateLike) {
        this.estimateLike = estimateLike;
    }

    public Object getEstimateObjectto() {
        return estimateObjectto;
    }

    public void setEstimateObjectto(int estimateObjectto) {
        this.estimateObjectto = estimateObjectto;
    }

    public long getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(long estimateTime) {
        this.estimateTime = estimateTime;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public String getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(String userPermissions) {
        this.userPermissions = userPermissions;
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
                ", estimateObjectto=" + estimateObjectto +
                ", estimateTime=" + estimateTime +
                ", model='" + model + '\'' +
                ", userPortrait='" + userPortrait + '\'' +
                ", userPermissions='" + userPermissions + '\'' +
                '}';
    }
}
