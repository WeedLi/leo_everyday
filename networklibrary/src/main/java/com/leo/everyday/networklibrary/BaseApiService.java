package com.leo.everyday.networklibrary;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者：Leo on 2018/3/9 16:54
 * <p>
 * 描述：
 */

public interface BaseApiService {

    @GET
    Observable<ResponseBody> get(
            @Url String url,
            @HeaderMap Map<String, String> headers,
            @QueryMap Map<String, String> params
    );

    @POST
    @FormUrlEncoded
    Observable<ResponseBody> post(
            @Url String url,
            @HeaderMap Map<String, String> headers,
            @FieldMap Map<String, String> params
    );

    @GET
    @Streaming
    Observable<ResponseBody> download(@Url String url);

    // 单个文件上传
    @POST
    @Multipart
    Observable<ResponseBody> uploadFile(
            @Url String url,
            @HeaderMap Map<String, String> headers,
            @PartMap Map<String, RequestBody> params,
            @Part MultipartBody.Part file
    );

    // 多个文件上传
    @POST
    @Multipart
    Observable<ResponseBody> uploadFiles(
            @Url String url,
            @HeaderMap Map<String, String> headers,
            @PartMap Map<String, RequestBody> params,
            @Part List<MultipartBody.Part> files
    );


}
