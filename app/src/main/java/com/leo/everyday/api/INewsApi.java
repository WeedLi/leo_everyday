package com.leo.everyday.api;

import com.leo.everyday.bean.news.NewsTabBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：Leo on 2018/1/17 15:08
 * <p>
 * 描述：
 */

public interface INewsApi {

    String HOST = "http://v.juhe.cn/";

    //String NEWS_URl = "http://v.juhe.cn/toutiao/index";
//    params.put("key", appKey);// 设置参数
//        params.put("type", type);// 设置参数

    //    @GET("http://v.juhe.cn/toutiao/index")
    @POST("http://v.juhe.cn/toutiao/index")
    Observable<NewsTabBean> getNewsItems( @Query("key") String key, @Query("type") String type);


}
