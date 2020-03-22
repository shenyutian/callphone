package com.syt.cellphone.pojo;

import java.util.List;

/**
 * @author shenyutian
 * @data 2020-01-19 17:16
 * 功能 手机详情 todo 评价需要修改
 */
public class PhoneDetails {


    /**
     * show : {"showId":1293901,"showTexture":"OLED","showSize":"6.53英寸需双手打字","showResolratio":"2400x1176像素1080P高清（击败90.58%手机）","showArrange":"","showPpi":"","showAod":"","showColorcontrol":"","showMaxluminance":"","showStimulateluminance":"","showColortem":"","showSrgb":"","showP3":"","showQuality":"","showOther":"1670万色，DCI-P3广色域，最多支持10点触控 "}
     * estimate : [{"estimateId":1,"phoneId":1293901,"userId":666,"estimateGrade":"真的辣鸡","estimateLike":1},{"estimateId":1293906,"phoneId":1293901,"userId":666,"estimateGrade":"好香呀","estimateLike":1}]
     * facade : {"facadeId":1293901,"facadeSize":"158.1x73.1x8.8mm ","facadeWeight":"198g ","facadeTexture":"玻璃机身查看外观图 ","facadeOther":""}
     * photo : [{"photoId":604,"phoneId":1293901,"phoneLocation":"亮黑色","photoMin":"2b.zol-img.com.cn/product/201_800x600/123/ce80fPntRU6AY.jpg","photoMax":"2b.zol-img.com.cn/product/201/123/ce80fPntRU6AY.jpg"},{"photoId":605,"phoneId":1293901,"phoneLocation":"正面","photoMin":"2c.zol-img.com.cn/product/201_800x600/124/ceALMUcxnofc.jpg","photoMax":"2c.zol-img.com.cn/product/201/124/ceALMUcxnofc.jpg"},{"photoId":606,"phoneId":1293901,"phoneLocation":"背面","photoMin":"2e.zol-img.com.cn/product/201_800x600/126/ceJvqslHem0U6.jpg","photoMax":"2e.zol-img.com.cn/product/201/126/ceJvqslHem0U6.jpg"},{"photoId":607,"phoneId":1293901,"phoneLocation":"顶端","photoMin":"2e.zol-img.com.cn/product/201_800x600/132/ceyKHDVwPbnyo.jpg","photoMax":"2e.zol-img.com.cn/product/201/132/ceyKHDVwPbnyo.jpg"},{"photoId":608,"phoneId":1293901,"phoneLocation":"顶端","photoMin":"2c.zol-img.com.cn/product/201_800x600/732/ceYcrbD1vw1j.jpg","photoMax":"2c.zol-img.com.cn/product/201/732/ceYcrbD1vw1j.jpg"}]
     * battery : {"batteryId":1293901,"batteryCapacity":"4500mAh大电池","batteryScheme":"快速充电（40W），无线快速充电（27W），反向充电","batteryCablecharger":"","batteryWirelesscharger":"","batteryCabletime":"","batteryWirelesstime":"","batteryEndurance":"理论通话时间：35小时理论待机时间：14.25天","batteryOther":"NPU:双核NPU+微核NPU（神经网络处理单元）"}
     * camera : {"cameraId":1293901,"cameraAmount":"五摄像头（后四）","cameraCmos":"索尼IMX600","cameraPixel":"后置摄像头:4000万像素电影超广角镜头+4000万像素超感光镜头+800万像素长焦镜头+3D深感镜头高清级像素 前置摄像头:3200万像素高清级像素 ","cameraFocallength":"17mm，27mm，80mm","cameraAperture":"后置f/1.8+f/1.6+f2.4，前置f/2.0","cameraOis":"","cameraFosusing":"","cameraGraph":"后置摄像头：超高速摄影，超高清夜摄，延时摄影，超大广角，大光圈虚化，双景录像，超级夜景，人像模式，专业模式，慢动作，全景模式，黑白艺术，流光快门，HDR，智能滤镜，水印，文档矫正，AI摄影大师，动态照片，4D预测追焦，笑脸抓拍，声控拍照，定时拍照，连拍前置摄像头：人像模式，全景模式，趣AR，延时摄影，动态照片，智能滤镜，水印，笑脸抓拍，自拍镜像，声控拍照，定时拍照","cameraVideo":"后置：最高支持4K（3840x2160，30帧/秒）视频录制720P（1280x720，7680帧/秒）慢动作视频录制1080p（1920×1080，960帧/秒）慢动作视频录制前置：最高支持FHD+（2336×1080）视频录制","cameraSteadyvideo":"","cameraSlowmotion":"","cameraRemarks":"照片分辨率后置：最大7296×5472像素，前置：最大6528×4896像素支持OIS/AIS防抖后置镜头支持3倍光学变焦，5倍混合变焦，30倍数字变焦 "}
     * config : {"configId":1293901,"socName":"海思麒麟9905G ","configMemory":"8GB游戏运行流畅 128GB2.6万张照片 ","configUnlock":"屏幕指纹识别，面部识别 ","configNetworking":"双频WIFI，IEEE802.11a/b/g/n/ac（支持802.11acwave2，MIMO，VHT160） 双频GPS导航（L1+L5），A-GPS，FLONASS，北斗，双频伽利略（E1+E5a），双频QZSS（L1+L5） WLAN热点，OTG，红外遥控 支持NFC（支持读卡器模式，点对点模式，卡模拟模式） USBType-C接口 3D人脸识别传感器，重力感应器，环境光传感器，屏内指纹识别传感器，霍尔传感器，陀螺仪，指南针，气压计，接近光传感器，Camera激光对焦传感器，色温传感器，红外传感器，姿态感应器 华为Histen音效 ","configRemork":"移动5G（NRTDD），联通5G（NRTDD），电信5G（NRTDD）更多5G手机＞ 移动TD-LTE，联通TD-LTE，联通FDD-LTE，电信TD-LTE，电信FDD-LTE 移动3G（TD-SCDMA），联通3G（WCDMA），电信3G（CDMA2000）移动2G（GSM），联通2G（GSM），电信2G（CDMA1X） NR：n77/n78/n79，n1/n3/n28（TX：703-733MHz，RX：758-788MHz/n38/n41（2515-2690MHz）LTE:B1/B2/B3/B4/B5/B6/B7/B8/B9/B12/B17/B18/B19/B20/B26TD-LTE：B34/B38/B39/B40/B41UMTS(WCDMA)/HSPA+/DC-HSDPA：B1/B2/B4/B5/B6/B8/B19TD-SCDMA：B34/B39（主副卡二选一）CDMA：BC0(800MHz)；(仅限中国电信（中国大陆+澳门）)GSM：B2/B3/B5/B8(850/900/1800/1900MHz) 双卡（三选二卡槽，双Nano-Sim卡或单Nano-Sim卡+NM存储卡） 支持5GNSA和SA以及5GSoc "}
     * base : {"baseId":1293901,"baseName":"华为Mate30Pro（8GB/128GB/全网通/5G版/玻璃版）","baseFeature":"双4000万徕卡电影四摄，超曲面OLED环幕屏","basePrice":"5899","baseImage":"https://2b.zol-img.com.cn/product/201_220x165/173/ceAHfEiY2o96.jpg","baseRemarks":"/cell_phone/index1293901.shtml"}
     */

    private PhoneShow show;
    private PhoneFacade facade;
    private PhoneBattery battery;
    private PhoneCamera camera;
    private PhoneConfig config;
    private PhoneBase base;
    private List<Estimate> estimate;
    private List<PhotoBean> photo;

    public PhoneShow getShow() {
        return show;
    }

    public void setShow(PhoneShow show) {
        this.show = show;
    }

    public PhoneFacade getFacade() {
        return facade;
    }

    public void setFacade(PhoneFacade facade) {
        this.facade = facade;
    }

    public PhoneBattery getBattery() {
        return battery;
    }

    public void setBattery(PhoneBattery battery) {
        this.battery = battery;
    }

    public PhoneCamera getCamera() {
        return camera;
    }

    public void setCamera(PhoneCamera camera) {
        this.camera = camera;
    }

    public PhoneConfig getConfig() {
        return config;
    }

    public void setConfig(PhoneConfig config) {
        this.config = config;
    }

    public PhoneBase getBase() {
        return base;
    }

    public void setBase(PhoneBase base) {
        this.base = base;
    }

    public List<Estimate> getEstimate() {
        return estimate;
    }

    public void setEstimate(List<Estimate> estimate) {
        this.estimate = estimate;
    }

    public List<PhotoBean> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoBean> photo) {
        this.photo = photo;
    }
}
