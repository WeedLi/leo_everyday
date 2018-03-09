package com.leo.everyday.networklibrary;

/**
 * 作者：Leo on 2018/3/9 16:54
 * <p>
 * 描述：
 */

public class RxHttpRequest {

    private BaseApiService baseApiService;


    public RxHttpRequest(RetrofitClient client) {
        baseApiService = client.getApiService(BaseApiService.class);
    }



}
