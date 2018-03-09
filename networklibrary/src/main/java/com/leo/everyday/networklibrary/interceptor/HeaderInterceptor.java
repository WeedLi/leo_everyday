package com.leo.everyday.networklibrary.interceptor;

import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：Leo on 2018/3/9 15:43
 * <p>
 * 描述：通过拦截器给Retrofit添加头部参数
 */

public class HeaderInterceptor implements Interceptor {

    private Map<String, String> headers;

    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (TextUtils.isEmpty(entry.getKey()) || TextUtils.isEmpty(entry.getValue()))
                continue;
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        return chain.proceed(builder.build());
    }


}
