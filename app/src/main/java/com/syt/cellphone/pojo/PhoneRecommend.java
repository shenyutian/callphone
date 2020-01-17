package com.syt.cellphone.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 品牌类
 */
@Entity
public class PhoneRecommend {

    /**
     * recommendId : 1
     * phoneId : 1291141
     * recommendReimage : https://dg-fd.zol-img.com.cn/t_s2000x2000/g4/M05/06/05/ChMly113aC-IVGMlAAG11ap9YqUAAXnogBWIakAAbXt993.jpg
     */
    @Id
    private int recommendId;
    private int phoneId;
    private String recommendReimage;

    @Generated(hash = 817267004)
    public PhoneRecommend(int recommendId, int phoneId, String recommendReimage) {
        this.recommendId = recommendId;
        this.phoneId = phoneId;
        this.recommendReimage = recommendReimage;
    }

    @Generated(hash = 1407069058)
    public PhoneRecommend() {
    }

    public int getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(int recommendId) {
        this.recommendId = recommendId;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public String getRecommendReimage() {
        return recommendReimage;
    }

    public void setRecommendReimage(String recommendReimage) {
        this.recommendReimage = recommendReimage;
    }
}