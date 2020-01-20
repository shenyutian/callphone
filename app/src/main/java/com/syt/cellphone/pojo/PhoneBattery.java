package com.syt.cellphone.pojo;

public class PhoneBattery {

    /**
     * batteryId : 1293901
     * batteryCapacity : 4500mAh大电池
     * batteryScheme : 快速充电（40W），无线快速充电（27W），反向充电
     * batteryCablecharger :
     * batteryWirelesscharger :
     * batteryCabletime :
     * batteryWirelesstime :
     * batteryEndurance : 理论通话时间：35小时理论待机时间：14.25天
     * batteryOther : NPU:双核NPU+微核NPU（神经网络处理单元）
     */
    private Integer batteryId;

    private String batteryCapacity;

    private String batteryScheme;

    private String batteryCablecharger;

    private String batteryWirelesscharger;

    private String batteryCabletime;

    private String batteryWirelesstime;

    private String batteryEndurance;

    private String batteryOther;

    public Integer getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(Integer batteryId) {
        this.batteryId = batteryId;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getBatteryScheme() {
        return batteryScheme;
    }

    public void setBatteryScheme(String batteryScheme) {
        this.batteryScheme = batteryScheme;
    }

    public String getBatteryCablecharger() {
        return batteryCablecharger;
    }

    public void setBatteryCablecharger(String batteryCablecharger) {
        this.batteryCablecharger = batteryCablecharger;
    }

    public String getBatteryWirelesscharger() {
        return batteryWirelesscharger;
    }

    public void setBatteryWirelesscharger(String batteryWirelesscharger) {
        this.batteryWirelesscharger = batteryWirelesscharger;
    }

    public String getBatteryCabletime() {
        return batteryCabletime;
    }

    public void setBatteryCabletime(String batteryCabletime) {
        this.batteryCabletime = batteryCabletime;
    }

    public String getBatteryWirelesstime() {
        return batteryWirelesstime;
    }

    public void setBatteryWirelesstime(String batteryWirelesstime) {
        this.batteryWirelesstime = batteryWirelesstime;
    }

    public String getBatteryEndurance() {
        return batteryEndurance;
    }

    public void setBatteryEndurance(String batteryEndurance) {
        this.batteryEndurance = batteryEndurance;
    }

    public String getBatteryOther() {
        return batteryOther;
    }

    public void setBatteryOther(String batteryOther) {
        this.batteryOther = batteryOther;
    }
}