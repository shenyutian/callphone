package com.syt.cellphone.pojo;

/**
 * 品牌类
 */
public class PhoneRecommend {
    private Integer recommendId;

    private Integer phoneId;

    private String recommendReimage;

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getRecommendReimage() {
        return recommendReimage;
    }

    public void setRecommendReimage(String recommendReimage) {
        this.recommendReimage = recommendReimage;
    }
}