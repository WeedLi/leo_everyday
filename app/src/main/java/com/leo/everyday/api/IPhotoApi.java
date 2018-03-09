package com.leo.everyday.api;

import com.leo.everyday.bean.photo.PhotoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 作者：Leo on 2018/1/29 11:36
 * <p>
 * 描述：
 */

public interface IPhotoApi {


    //http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1

    @GET
    Observable<PhotoBean> getPhotoData(@Url String url);


}
