package com.syt.cellphone.service;

import com.alibaba.fastjson.JSONObject;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class PhoneService {

    /**
     *     获取 phone基础数据 列表 根据 页号
      */
    public static List<PhoneBase> getListBaseByid(int phoneListId) {
        List<PhoneBase> phoneBaseList = new ArrayList<>();
        String body = "/basePhone/list/" + phoneListId;
        String responseBody = HttpUtil.getUrl(body);
        if (responseBody == null) {
            return phoneBaseList;
        }
        // string -> Json
        JSONObject json = JSONObject.parseObject(responseBody);
        String liststr = json.getString("list");
        phoneBaseList.addAll(JSONObject.parseArray(liststr, PhoneBase.class));
        return phoneBaseList;
    }

    /**
     * 获取 最大页数
     * @return
     */
    public static int getPageNum() {
        String responseBody = HttpUtil.getUrl("/basePhone/list/1");
        if (responseBody == null) {
            return 0;
        }
        JSONObject json = JSONObject.parseObject(responseBody);
        return json.getInteger("pageNum");
    }

    /**
     * 获取推荐表
     * @return
     */
    public static List<PhoneRecommend> getRecommend() {
        String responseBody = HttpUtil.getUrl("/recommend/list");
        return JSONObject.parseArray(responseBody, PhoneRecommend.class);
    }
}
