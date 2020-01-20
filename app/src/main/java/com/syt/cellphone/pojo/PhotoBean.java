package com.syt.cellphone.pojo;

/**
 * @author shenyutian
 * @data 2020-01-20 10:04
 * 功能
 */
public class PhotoBean {

    /**
     * photoId : 604
     * phoneId : 1293901
     * phoneLocation : 亮黑色
     * photoMin : 2b.zol-img.com.cn/product/201_800x600/123/ce80fPntRU6AY.jpg
     * photoMax : 2b.zol-img.com.cn/product/201/123/ce80fPntRU6AY.jpg
     */
    private int photoId;
    private int phoneId;
    private String phoneLocation;
    private String photoMin;
    private String photoMax;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneLocation() {
        return phoneLocation;
    }

    public void setPhoneLocation(String phoneLocation) {
        this.phoneLocation = phoneLocation;
    }

    public String getPhotoMin() {
        return photoMin;
    }

    public void setPhotoMin(String photoMin) {
        this.photoMin = photoMin;
    }

    public String getPhotoMax() {
        return photoMax;
    }

    public void setPhotoMax(String photoMax) {
        this.photoMax = photoMax;
    }
}
