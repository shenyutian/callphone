package com.syt.cellphone.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author shenyutian
 * @data 2020-01-13 17:34
 * 功能 查询历史实体类
 */
@Entity
public class SearchHistory {

    /**
     * searchId             唯一id
     * searchContent        搜索输入内容
     * searchLocal          搜索时间
     */
    @Id
    private Long            id;
    @Unique
    private String          searchContent;
    private Long            searchLocal;
    @Generated(hash = 202578753)
    public SearchHistory(Long id, String searchContent, Long searchLocal) {
        this.id = id;
        this.searchContent = searchContent;
        this.searchLocal = searchLocal;
    }
    @Generated(hash = 1905904755)
    public SearchHistory() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSearchContent() {
        return this.searchContent;
    }
    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
    public Long getSearchLocal() {
        return this.searchLocal;
    }
    public void setSearchLocal(Long searchLocal) {
        this.searchLocal = searchLocal;
    }

}
