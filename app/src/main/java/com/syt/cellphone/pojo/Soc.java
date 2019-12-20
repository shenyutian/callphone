package com.syt.cellphone.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author syt
 * soc类
 */
@Entity
public class Soc {

    @Id
    private long socId;

    /**
     * socId : 534
     * socName : MT6582M
     * socTrademark : MTK联发科
     * socProcess : 28nm
     * socProperty : 单通道LPDDR2/3-533
     * socBbx : HSPA+/TD-SCDMA
     * socCpuSpecification : 四核A7'1.3GHz
     * socGpuSpecification : Mali-400MP2400MHz
     */
    private String socName;
    private String socTrademark;
    private String socProcess;
    private String socProperty;
    private String socBbx;
    private String socCpuSpecification;
    private String socGpuSpecification;

    @Generated(hash = 626981844)
    public Soc(long socId, String socName, String socTrademark, String socProcess,
            String socProperty, String socBbx, String socCpuSpecification,
            String socGpuSpecification) {
        this.socId = socId;
        this.socName = socName;
        this.socTrademark = socTrademark;
        this.socProcess = socProcess;
        this.socProperty = socProperty;
        this.socBbx = socBbx;
        this.socCpuSpecification = socCpuSpecification;
        this.socGpuSpecification = socGpuSpecification;
    }

    @Generated(hash = 1181664419)
    public Soc() {
    }

    public long getSocId() {
        return socId;
    }

    public void setSocId(Integer socId) {
        this.socId = socId;
    }

    public String getSocName() {
        return socName;
    }

    public void setSocName(String socName) {
        this.socName = socName;
    }

    public String getSocTrademark() {
        return socTrademark;
    }

    public void setSocTrademark(String socTrademark) {
        this.socTrademark = socTrademark;
    }

    public String getSocProcess() {
        return socProcess;
    }

    public void setSocProcess(String socProcess) {
        this.socProcess = socProcess;
    }

    public String getSocProperty() {
        return socProperty;
    }

    public void setSocProperty(String socProperty) {
        this.socProperty = socProperty;
    }

    public String getSocBbx() {
        return socBbx;
    }

    public void setSocBbx(String socBbx) {
        this.socBbx = socBbx;
    }

    public String getSocCpuSpecification() {
        return socCpuSpecification;
    }

    public void setSocCpuSpecification(String socCpuSpecification) {
        this.socCpuSpecification = socCpuSpecification;
    }

    public String getSocGpuSpecification() {
        return socGpuSpecification;
    }

    public void setSocGpuSpecification(String socGpuSpecification) {
        this.socGpuSpecification = socGpuSpecification;
    }

    @Override
    public String toString() {
        return "Soc{" +
                "socId=" + socId +
                ", socName='" + socName + '\'' +
                ", socTrademark='" + socTrademark + '\'' +
                ", socProcess='" + socProcess + '\'' +
                ", socProperty='" + socProperty + '\'' +
                ", socBbx='" + socBbx + '\'' +
                ", socCpuSpecification='" + socCpuSpecification + '\'' +
                ", socGpuSpecification='" + socGpuSpecification + '\'' +
                '}';
    }

    public void setSocId(long socId) {
        this.socId = socId;
    }
}