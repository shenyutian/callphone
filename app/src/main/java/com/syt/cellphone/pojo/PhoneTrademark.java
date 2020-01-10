package com.syt.cellphone.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

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