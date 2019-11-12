package com.syt.cellphone.service;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class SocService {

    private static int state = 500;

    /**
     * 根据 页号 来获取 soc列表
     * @param i
     * @return
     */
    public static List<Soc> getSocList(int i) {
        List<Soc> socList = new ArrayList<>();
        String body = "/soc/list/" + i;
        String responseBody = HttpUtil.getUrl(body);
        // string -> Json
        JSONObject json = JSONObject.parseObject(responseBody);
        if (json == null) {
            Soc soc1 = new Soc();
            soc1.setSocId(433);
            soc1.setSocName("骁龙845");
            soc1.setSocTrademark("骁龙800");
            Soc soc2 = new Soc();
            soc2.setSocId(434);
            soc2.setSocName("骁龙710");
            soc2.setSocTrademark("高通骁龙700");
            socList.add(soc1);
            socList.add(soc2);
        } else {
            String liststr = json.getString("list");
            socList = JSONObject.parseArray(liststr, Soc.class);
            Log.e("shenyutian", "getSocList: " + socList);
        }
        state = 200;
        return socList;
    }

    /**
     * 根据id  获取 soc
     * @param socId
     * @return
     */
    public static Soc getSocByid(int socId) {
        String body = "soc/get/" + socId;
        String responseBody = HttpUtil.getUrl(body);
        Soc soc = JSONObject.parseObject(responseBody, Soc.class);
        return soc;
    }
}
