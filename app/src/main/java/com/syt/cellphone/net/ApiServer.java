package com.syt.cellphone.net;

import com.syt.cellphone.pojo.PhoneBasePageList;
import com.syt.cellphone.pojo.PhoneDetails;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.pojo.PhoneTrademark;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.pojo.SocList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
     * 分类查询 ， 搜索也走这个
     * @param pageNum 页号
     * @param name 分类名称 小米....
     * @return 分类结果数据
     */
    @GET("basePhone/classify/{pageNum}")
    Observable<PhoneBasePageList> getClassifyPhone(@Path("pageNum") int pageNum, @Query("name") String name);

    /**
     * 分页查询手机数据，这就直接查询最近更新
     * @param id 页号
     * @return 基础数据集
     */
    @GET("basePhone/list/{id}")
    Observable<PhoneBasePageList> getBasePhone(@Path("id") int id);

    /**
     * 全部品牌
     * @return 品牌列表
     */
    @GET("trademark/all")
    Observable<List<PhoneTrademark>> getAllTrademark();


    @GET("PhoneAllById/{id}")
    Observable<PhoneDetails> getPhoneDetailsById(@Path("id") int phoneId);
}
