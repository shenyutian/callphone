package com.syt.cellphone.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * 基础数据类
 * @author space
 */
@Entity
public class PhoneBase {

    /**
     * baseId : 1290286
     * baseName : realmeX2（6GB/64GB/全网通）
     * baseFeature : VOOC4.0，6400万后置主摄像头，3200万超清前置
     * basePrice : 1599
     * baseImage : https://2f.zol-img.com.cn/product/201_220x165/847/ceSLJZzIV76l.jpg
     * baseRemarks : /cell_phone/index1290286.shtml
     */
    @Id
    private int baseId;
    private String baseName;
    private String baseFeature;
    private String basePrice;
    private String baseImage;
    private String baseRemarks;

    public int getBaseId() {
        return baseId;
    }

    public void setBaseId(int baseId) {
        this.baseId = baseId;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getBaseFeature() {
        return baseFeature;
    }

    public void setBaseFeature(String baseFeature) {
        this.baseFeature = baseFeature;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }

    public String getBaseRemarks() {
        return baseRemarks;
    }

    public void setBaseRemarks(String baseRemarks) {
        this.baseRemarks = baseRemarks;
    }
}