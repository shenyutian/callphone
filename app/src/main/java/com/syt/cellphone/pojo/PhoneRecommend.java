package com.syt.cellphone.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 推荐类 = 轮播图
 */
@Entity
public class PhoneRecommend {

    /**
     * recommendId : 1
     * phoneId : 1291141
     * recommendReimage : https://dg-fd.zol-img.com.cn/t_s2000x2000/g4/M05/06/05/ChMly113aC-IVGMlAAG11ap9YqUAAXnogBWIakAAbXt993.jpg
     * recommendName : 推荐名称
     */
    @Id
    private Long recommendId;
    private Integer phoneId;
    private String recommendReimage;
    private String recommendName;
    @Generated(hash = 1257395133)
    public PhoneRecommend(Long recommendId, Integer phoneId, String recommendReimage, String recommendName) {
        this.recommendId = recommendId;
        this.phoneId = phoneId;
        this.recommendReimage = recommendReimage;
        this.recommendName = recommendName;
    }
    @Generated(hash = 1407069058)
    public PhoneRecommend() {
    }
    public Long getRecommendId() {
        return this.recommendId;
    }
    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }
    public Integer getPhoneId() {
        return this.phoneId;
    }
    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }
    public String getRecommendReimage() {
        return this.recommendReimage;
    }
    public void setRecommendReimage(String recommendReimage) {
        this.recommendReimage = recommendReimage;
    }
    public String getRecommendName() {
        return this.recommendName;
    }
    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

}