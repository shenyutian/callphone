package com.syt.cellphone.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 品牌
 * @author：syt
 */
@Entity
public class PhoneTrademark {


    /**
     * trademarkId : 360
     * trademarkName : 360
     * trademarkLog : https://2c.zol-img.com.cn/manu_photo/35350.jpg
     */
    @Id
    private String trademarkId;
    private String trademarkName;
    private String trademarkLog;

    @Generated(hash = 1154455719)
    public PhoneTrademark(String trademarkId, String trademarkName,
            String trademarkLog) {
        this.trademarkId = trademarkId;
        this.trademarkName = trademarkName;
        this.trademarkLog = trademarkLog;
    }

    @Generated(hash = 1910035199)
    public PhoneTrademark() {
    }

    public String getTrademarkId() {
        return trademarkId;
    }

    public void setTrademarkId(String trademarkId) {
        this.trademarkId = trademarkId;
    }

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public String getTrademarkLog() {
        return trademarkLog;
    }

    public void setTrademarkLog(String trademarkLog) {
        this.trademarkLog = trademarkLog;
    }
}