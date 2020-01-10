package com.syt.cellphone.pojo;

import java.util.List;

/**
 * @author shenyutian
 * @data 2020-01-07 14:55
 * 功能 基础数据集bean
 */
public class PhoneBasePageList {
    /**
     * total : 84
     * list : [{"baseId":1293931,"baseName":"华为Mate30（8GB/256GB/全网通/5G版/素皮版）","baseFeature":"屏幕类型:全面屏（刘海屏）主屏尺寸:6.62英寸主屏材质:OLED出厂系统内核:Android10.0主屏分辨率:2340x1080像素屏幕像素密度:389ppiCPU型号:海思麒麟9905G","basePrice":"899","baseImage":"https://2c.zol-img.com.cn/product/201_220x165/672/ceBoctn7RDFTY.jpg","baseRemarks":"/cell_phone/index1293931.shtml"},{"baseId":1293929,"baseName":"华为Mate30（8GB/256GB/全网通/5G版/玻璃版）","baseFeature":"屏幕类型:全面屏（刘海屏）主屏尺寸:6.62英寸主屏材质:OLED出厂系统内核:Android10.0主屏分辨率:2340x1080像素CPU型号:海思麒麟9905G5G网络:移动5G（NRTDD），联通5G（NRTDD），电信5G（NRTDD）","basePrice":"1049","baseImage":"https://2b.zol-img.com.cn/product/201_220x165/665/cexQGCYzDsw.jpg","baseRemarks":"/cell_phone/index1293929.shtml"},{"baseId":1293904,"baseName":"华为Mate30Pro（8GB/256GB/全网通/5G版/素皮版）","baseFeature":"双4000万徕卡电影四摄，超曲面OLED环幕屏","basePrice":"1899","baseImage":"https://2c.zol-img.com.cn/product/201_220x165/426/ceHyCuK9e3BdY.jpg","baseRemarks":"/cell_phone/index1293904.shtml"},{"baseId":1293901,"baseName":"华为Mate30Pro（8GB/128GB/全网通/5G版/玻璃版）","baseFeature":"双4000万徕卡电影四摄，超曲面OLED环幕屏","basePrice":"5899","baseImage":"https://2b.zol-img.com.cn/product/201_220x165/173/ceAHfEiY2o96.jpg","baseRemarks":"/cell_phone/index1293901.shtml"},{"baseId":1293893,"baseName":"华为Mate30Pro（8GB/128GB/全网通）","baseFeature":"双4000万徕卡电影四摄，超曲面OLED环幕屏","basePrice":"5799","baseImage":"https://2e.zol-img.com.cn/product/201_220x165/32/ceUaOtCd01lc.jpg","baseRemarks":"/cell_phone/index1293893.shtml"},{"baseId":1293857,"baseName":"华为Mate30Pro（8GB/256GB/全网通）","baseFeature":"双4000万徕卡电影四摄，超曲面OLED环幕屏","basePrice":"6299","baseImage":"https://2f.zol-img.com.cn/product/201_220x165/175/ceJ6JX9LJ7mXI.jpg","baseRemarks":"/cell_phone/index1293857.shtml"},{"baseId":1293856,"baseName":"华为Mate30（8GB/128GB/全网通）","baseFeature":"IP53防水防尘，磁悬浮发声技术，4000万超感光徕卡影像，OLED全面屏","basePrice":"4299","baseImage":"https://2c.zol-img.com.cn/product/201_220x165/154/ceJsISe43FBm2.jpg","baseRemarks":"/cell_phone/index1293856.shtml"},{"baseId":1292758,"baseName":"华为Mate30 RS保时捷版（12GB/512GB/全网通/5G版）","baseFeature":"后背带设计","basePrice":"12999","baseImage":"http://127.0.0.1:8001/C:\\fakepath\\ce11omDKxAfy6.jpg","baseRemarks":"http://detail.zol.com.cn/cell_phone/index1292757.shtml"},{"baseId":1292757,"baseName":"华为Mate30RS保时捷版（12GB/512GB/全网通/5G版）","baseFeature":"后背带设计","basePrice":"799","baseImage":"https://2a.zol-img.com.cn/product/201_220x165/284/ce11omDKxAfy6.jpg","baseRemarks":"/cell_phone/index1292757.shtml"},{"baseId":1291831,"baseName":"华为P40（全网通）","baseFeature":"主屏尺寸:0英寸出厂系统内核:鸿蒙OS4G网络:移动TD-LTE，联通TD-LTE，联通FDD-LTE，电信TD-LTE，电信FDD-LTE电池类型:不可拆卸式电池上市日期:2020年手机类型:4G手机，3G手机，智能手机，拍照手机，平板手机窄边框:0mm","basePrice":"1999","baseImage":"https://2c.zol-img.com.cn/product/201_220x165/292/ceCgoDl5jeqU.jpg","baseRemarks":"/cell_phone/index1291831.shtml"}]
     * pageNum : 1
     * pageSize : 10
     * size : 10
     * startRow : 1
     * endRow : 10
     * pages : 9
     * prePage : 0
     * nextPage : 2
     * isFirstPage : true
     * isLastPage : false
     * hasPreviousPage : false
     * hasNextPage : true
     * navigatePages : 8
     * navigatepageNums : [1,2,3,4,5,6,7,8]
     * navigateFirstPage : 1
     * navigateLastPage : 8
     */

    private int total;
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private List<PhoneBase> list;
    private List<Integer> navigatepageNums;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public List<PhoneBase> getList() {
        return list;
    }

    public void setList(List<PhoneBase> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
