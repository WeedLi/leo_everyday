package com.leo.everyday.request;

import com.leo.everyday.bean.news.NewsTabBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：Leo on 2018/3/15 11:32
 * <p>
 * 描述：
 */

public interface INewsApi {

    @POST("http://v.juhe.cn/toutiao/index")
    Observable<NewsTabBean> getNewsItems(@Query("key") String key, @Query("type") String type);

}
