package com.syt.cellphone.net;

import com.alibaba.fastjson.JSONObject;
import com.syt.cellphone.pojo.PhoneBasePageList;
import com.syt.cellphone.pojo.PhoneDetails;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.pojo.PhoneTrademark;
import com.syt.cellphone.pojo.PhoneUser;
import com.syt.cellphone.pojo.Registered;
import com.syt.cellphone.pojo.Soc;
import com.syt.cellphone.pojo.SocList;
import com.syt.cellphone.pojo.UnloadFile;
import com.syt.cellphone.pojo.UnloadFiles;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    /**
     * 查询一个设备的所有信息
     * @param phoneId 设备id
     * @return 设备信息大全
     */
    @GET("PhoneAllById/{id}")
    Observable<PhoneDetails> getPhoneDetailsById(@Path("id") int phoneId);

    /**
     * 上传评价设备
     * 如果需要登录，那么就需要请求头token
     * @param body 请求体
     * @return 请求结果
     */
    @POST("estimate/add")
    Observable<JSONObject> setEstimate(@Body RequestBody body);

    /**
     * 登录操作
     * @param user 登录信息
     * @return 返回结果
     */
    @POST("/user/login")
    Observable<PhoneUser> setUserLogin(@Body PhoneUser user);

    /**
     * 通知服务器发送  邮箱对应的验证码
     * @param email 邮箱号
     * @return 1 成功  其它 错误信息
     * Field from-data
     */
    @POST("/user/email")
    Observable<JSONObject> setUserEmail(@Query("email") String email);

    /**
     * 发送注册的所有信息
     * @param user 注册信息
     * @return 返回成功的用户名 + 用户token
     */
    @POST("/user/register")
    Observable<Registered> setRegistered(@Body PhoneUser user);

    /**
     * 上传图片接口
     * @param file 上传的图像文件
     * @return 上传结果
     * "code": 0,
     *     "filepath": "/Users/space/Downloads/qq_file/IMG_20191227_103246.jpg",
     *     "data": {
     *         "src": "http://127.0.0.1/IMG_20191227_103246.jpg"
     *     }
     */
    @POST("/api/upload")
    @Multipart
    Observable<UnloadFile> unload(@Part MultipartBody.Part file);

    /**
     * 上传多文件接口
     * @param body 上传的数据
     * @return 上传结果
     * {
     *     "code": 0,
     *     "data": [
     *         "http://47.115.43.73:8001/cw.png"
     *     ],
     *     "error": []
     * }
     */
    @POST("/api/uploadFiles")
    Observable<UnloadFiles> unloadFiles(@Body RequestBody body);

    /**
     * 上传用户头像
     * @param portraitSrc 头像路径
     * @return 结果 msg = 1 + protraitSrc 成功  失败 msg = 0
     */
    @POST("/user/portrait")
    Observable<JSONObject> setPortrait(@Query("portraitSrc") String portraitSrc);


}
