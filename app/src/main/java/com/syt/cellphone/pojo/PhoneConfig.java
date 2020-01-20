package com.syt.cellphone.pojo;

public class PhoneConfig {

    /**
     * configId : 1293901
     * socName : 海思麒麟9905G
     * configMemory : 8GB游戏运行流畅 128GB2.6万张照片
     * configUnlock : 屏幕指纹识别，面部识别
     * configNetworking : 双频WIFI，IEEE802.11a/b/g/n/ac（支持802.11acwave2，MIMO，VHT160） 双频GPS导航（L1+L5），A-GPS，FLONASS，北斗，双频伽利略（E1+E5a），双频QZSS（L1+L5） WLAN热点，OTG，红外遥控 支持NFC（支持读卡器模式，点对点模式，卡模拟模式） USBType-C接口 3D人脸识别传感器，重力感应器，环境光传感器，屏内指纹识别传感器，霍尔传感器，陀螺仪，指南针，气压计，接近光传感器，Camera激光对焦传感器，色温传感器，红外传感器，姿态感应器 华为Histen音效
     * configRemork : 移动5G（NRTDD），联通5G（NRTDD），电信5G（NRTDD）更多5G手机＞ 移动TD-LTE，联通TD-LTE，联通FDD-LTE，电信TD-LTE，电信FDD-LTE 移动3G（TD-SCDMA），联通3G（WCDMA），电信3G（CDMA2000）移动2G（GSM），联通2G（GSM），电信2G（CDMA1X） NR：n77/n78/n79，n1/n3/n28（TX：703-733MHz，RX：758-788MHz/n38/n41（2515-2690MHz）LTE:B1/B2/B3/B4/B5/B6/B7/B8/B9/B12/B17/B18/B19/B20/B26TD-LTE：B34/B38/B39/B40/B41UMTS(WCDMA)/HSPA+/DC-HSDPA：B1/B2/B4/B5/B6/B8/B19TD-SCDMA：B34/B39（主副卡二选一）CDMA：BC0(800MHz)；(仅限中国电信（中国大陆+澳门）)GSM：B2/B3/B5/B8(850/900/1800/1900MHz) 双卡（三选二卡槽，双Nano-Sim卡或单Nano-Sim卡+NM存储卡） 支持5GNSA和SA以及5GSoc
     */
    private Integer configId;

    private String socName;

    private String configMemory;

    private String configUnlock;

    private String configNetworking;

    private String configRemork;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getSocName() {
        return socName;
    }

    public void setSocName(String socName) {
        this.socName = socName;
    }

    public String getConfigMemory() {
        return configMemory;
    }

    public void setConfigMemory(String configMemory) {
        this.configMemory = configMemory;
    }

    public String getConfigUnlock() {
        return configUnlock;
    }

    public void setConfigUnlock(String configUnlock) {
        this.configUnlock = configUnlock;
    }

    public String getConfigNetworking() {
        return configNetworking;
    }

    public void setConfigNetworking(String configNetworking) {
        this.configNetworking = configNetworking;
    }

    public String getConfigRemork() {
        return configRemork;
    }

    public void setConfigRemork(String configRemork) {
        this.configRemork = configRemork;
    }
}