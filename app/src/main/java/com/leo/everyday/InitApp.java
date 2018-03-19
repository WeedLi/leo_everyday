package com.leo.everyday;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.leo.everyday.networklibrary.ApiBiz;
import com.leo.everyday.networklibrary.RetrofitClient;

import java.util.concurrent.TimeUnit;

/**
 * 作者：Leo on 2018/1/12 17:33
 * <p>
 * 描述：
 */

public class InitApp extends MultiDexApplication {

    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();

        //初始化全局的retrofit
        RetrofitClient retrofitClient = new RetrofitClient
                .Builder(AppContext)
                .baseUrl("http://v.juhe.cn/")
                .log(true)
                .joinParamsIntoUrl(false)
//                .header("user-agent", "android")
                .readTimeout(30)
                .writeTimeout(30)
                .connectTimeout(60)
                .timeoutUnit(TimeUnit.SECONDS)
                .build();

        ApiBiz.getInstance().setRetorfitClient(retrofitClient);

    }


}
