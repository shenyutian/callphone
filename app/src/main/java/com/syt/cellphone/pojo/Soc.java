package com.syt.cellphone.pojo;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * @author syt
 * soc类
 */
public class Soc extends LitePalSupport implements Serializable {

    private Integer socId;

    // soc型号
    private String socName;

    // 品牌
    private String socTrademark;

    // 制程 例如 14nm
    private String socProcess;

    // 内存性能 例如 64位双通道LPDDR3-1600
    private String socProperty;

    // 基带  例如 LTECat.9
    private String socBbx;

    // Cpu规格 例如 四核A57+四核A53'2.1+1.5GHz
    private String socCpuSpecification;

    // Gpu规格 例如 Mali-T720MP2668MHz
    private String socGpuSpecification;

    public Integer getSocId() {
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
}