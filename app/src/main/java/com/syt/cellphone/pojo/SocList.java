package com.syt.cellphone.pojo;

import java.util.List;

/**
 * @author：syt Date: 2019-12-13
 * 作用: SocList
 */
public class SocList {

    /**
     * total : 180
     * list : [{"socId":534,"socName":"MT6582M","socTrademark":"MTK联发科","socProcess":"28nm","socProperty":"单通道LPDDR2/3-533","socBbx":"HSPA+/TD-SCDMA","socCpuSpecification":"四核A7'1.3GHz","socGpuSpecification":"Mali-400MP2400MHz"},{"socId":535,"socName":"MT6592","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR2-533/LPDDR3-666","socBbx":"HSPA+/TD-SCDMA","socCpuSpecification":"八核A7'2.0GHz","socGpuSpecification":"Mali-450MP4700MHz"},{"socId":536,"socName":"MT6592M","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR2-533/LPDDR3-666","socBbx":"HSPA+/TD-SCDMA","socCpuSpecification":"八核A7'1.4GHz","socGpuSpecification":"Mali-450MP4600MHz"},{"socId":537,"socName":"MT6591","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR2/3","socBbx":"HSPA+/TD-SCDMA","socCpuSpecification":"六核A7'1.5GHz","socGpuSpecification":"Mali-450MP4700MHz"},{"socId":538,"socName":"MT6595","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"双通道LPDDR3-933","socBbx":"移动/联通双4G","socCpuSpecification":"八核A7'2.2+1.7GHz","socGpuSpecification":"PowerVR6200600MHz"},{"socId":539,"socName":"MT6595M","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"双通道LPDDR3-933","socBbx":"移动/联通双4G","socCpuSpecification":"八核A7'2.0+1.5GHz","socGpuSpecification":"PowerVR6200450MHz"},{"socId":540,"socName":"MT6595T","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"双通道LPDDR3-933","socBbx":"移动/联通双4G","socCpuSpecification":"八核A7'2.5+1.7GHz","socGpuSpecification":"PowerVR6200600MHz"},{"socId":541,"socName":"MT6732/6732M","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR3-800","socBbx":"LTECat.4","socCpuSpecification":"四核A53'1.5/1.3GHz","socGpuSpecification":"Mali-T760MP2500/?MHz"},{"socId":542,"socName":"MT6735/6735M","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR3-640","socBbx":"LTECat.4","socCpuSpecification":"四核A53'1.3/1.0GHz","socGpuSpecification":"Mali-T720MP2600/400MHz"},{"socId":543,"socName":"MT6737/6737M/6737T","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR3-533","socBbx":"LTECat.4VoLTE","socCpuSpecification":"四核A53'1.3/1.1/1.5GHz","socGpuSpecification":"Mali-T860MP1400MHz"},{"socId":544,"socName":"MT6738","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR3-533","socBbx":"LTECat.4VoLTE","socCpuSpecification":"四核A53'1.5GHz","socGpuSpecification":"Mali-T860MP1400MHz"},{"socId":545,"socName":"MT6739","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR3-667","socBbx":"LTECat.4/5VoLTE","socCpuSpecification":"四核A53'1.5GHz","socGpuSpecification":"PowerVRGE8100570MHz"},{"socId":546,"socName":"MT6750","socTrademark":"MTK联发科","socProcess":"28nmHPC+","socProperty":"单通道LPDDR3-667","socBbx":"LTECat.6双载波聚合/VoLTE","socCpuSpecification":"八核A53'1.5GHz","socGpuSpecification":"Mali-T860MP2400MHz"},{"socId":547,"socName":"MT6752/6752M","socTrademark":"MTK联发科","socProcess":"28nmHPM","socProperty":"单通道LPDDR3-800","socBbx":"LTECat.4","socCpuSpecification":"八核A53'1.7/1.5GHz","socGpuSpecification":"Mali-T720MP2700MHz"},{"socId":548,"socName":"MT6753","socTrademark":"MTK联发科","socProcess":"28nm","socProperty":"单通道LPDDR3-800","socBbx":"LTECat.4","socCpuSpecification":"八核A53'1.5GHz","socGpuSpecification":"Mali-T720MP3700MHz"},{"socId":549,"socName":"HelioA22","socTrademark":"MTK联发科","socProcess":"12nm","socProperty":"LPDDR3-933LPDDR4X-1600","socBbx":"LTECat.7","socCpuSpecification":"四核A53'2.0GHz","socGpuSpecification":"PowerVRGE8300"},{"socId":550,"socName":"HelioP10MT6755/6755M","socTrademark":"MTK联发科","socProcess":"28nmHPC+","socProperty":"单通道LPDDR3-933","socBbx":"LTECat.6","socCpuSpecification":"八核A53'2/1.8GHz","socGpuSpecification":"Mali-T860MP2700MHz"},{"socId":551,"socName":"HelioP15MT6755T","socTrademark":"MTK联发科","socProcess":"28nmHPC+","socProperty":"单通道LPDDR3-933","socBbx":"LTECat.6","socCpuSpecification":"八核A53'2.2Hz","socGpuSpecification":"Mali-T860MP2700MHz"},{"socId":552,"socName":"HelioP18MT6755S","socTrademark":"MTK联发科","socProcess":"28nmHPC+","socProperty":"单通道LPDDR3-933","socBbx":"LTECat.6","socCpuSpecification":"八核A53'?","socGpuSpecification":"Mali-T860MP2700MHz"},{"socId":553,"socName":"HelioP20MT6757","socTrademark":"MTK联发科","socProcess":"16nmFFC","socProperty":"单通道LPDDR3-933双通道LPDDR4X-1600","socBbx":"LTECat.6","socCpuSpecification":"八核A53'2.3+1.6GHz","socGpuSpecification":"Mali-T880MP2900MHz"}]
     * pageNum : 5
     * pageSize : 20
     * size : 20
     * startRow : 81
     * endRow : 100
     * pages : 9
     * prePage : 4
     * nextPage : 6
     * isFirstPage : false
     * isLastPage : false
     * hasPreviousPage : true
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
    private List<Soc> list;
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

    public List<Soc> getList() {
        return list;
    }

    public void setList(List<Soc> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
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

        private int socId;
        private String socName;
        private String socTrademark;
        private String socProcess;
        private String socProperty;
        private String socBbx;
        private String socCpuSpecification;
        private String socGpuSpecification;

        public int getSocId() {
            return socId;
        }

        public void setSocId(int socId) {
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
    }
}
