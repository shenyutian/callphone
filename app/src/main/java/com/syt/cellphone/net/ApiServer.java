package com.syt.cellphone.net;

import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.pojo.SocList;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

    /**
     * @param name 分类名称 最近更新, 小米....
     * @return 分类结果数据
     */
    @GET("basePhone/classify")
    Observable<List<PhoneBase>> getClassifyPhone(@Query("name") String name);

    /**
     * 分页查询手机数据
     * @param params
     * @return
     */
    @GET("basePhone/list")
    Observable<List<PhoneBase>> getBasePhone(@QueryMap Map params);
}
