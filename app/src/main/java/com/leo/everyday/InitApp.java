package com.leo.everyday;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

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
    }


}
