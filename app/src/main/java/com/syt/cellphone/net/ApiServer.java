package com.syt.cellphone.net;

import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.pojo.SocList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author：syt
 * Date: 2019-12-03
 * 作用: Api接口
 */
public interface ApiServer {

    /**
     * 请求soc列表
     * @param pageNum 页数
     * @return soc列表
     */
    @GET("soc/list/{pageNum}")
    Observable<SocList> getNetListSoc(@Path("pageNum") int pageNum);

    /**
     * 请求推荐列表
     * @return 推荐列表
     */
    @GET("recommend/list")
    Observable<List<PhoneRecommend>> getRecommendList();

    /**
     * 获取soc详情
     * @param socId 处理器id
     * @return 处理器详情
     */
    @GET("soc/list/{socId}")
    Observable<Soc> getSocById(@Path("socId") int socId);



}
