package com.leo.everyday.api;

import com.leo.everyday.bean.connotations.ConnotationsListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 作者：Leo on 2018/1/31 17:27
 * <p>
 * 描述：
 */

public interface IConnotationsApi {

    @GET
    Observable<ConnotationsListBean> getVideoListData(@Url String Url);

}
