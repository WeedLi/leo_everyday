package com.leo.everyday.networklibrary.cache;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.leo.everyday.networklibrary.annotation.Cache;
import com.leo.everyday.networklibrary.database.DatabaseHelper;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 作者：Leo on 2018/3/13 17:23
 * <p>
 * 描述：
 */

public class RxCache {

    private CacheController cacheController;

    public RxCache(Context context) {
        //打开数据库
        DatabaseHelper.openDatabase(context);
        cacheController = new CacheController(context);
    }


    public Observable<String> getCache(final String cacheKey, final String url, final Map<String, String> params, final Cache cache) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                long maxAge = cache.maxAge();
                String key = getKey(cacheKey, url, params);
                String cache = cacheController.getCache(key, maxAge);
                if (!TextUtils.isEmpty(cache)) {
                    Log.i("Cache", "get data from cache：" + cache);
                    e.onNext(cache);
                }
                e.onComplete();
            }
        });
    }

    public void saveCache(String cacheKey, String url, Map<String, String> params, String value) {
        String key = getKey(cacheKey, url, params);
        cacheController.saveCache(key, value);
        Log.i("Cache", "Saved cache: " + value);
    }


    public <T> Observable<T> getCacheObj(final String cacheKey, final String url, final Map<String, String> params, final Cache cache) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                String key = getKey(cacheKey, url, params);
                Log.e("Cache", "获取缓存缓存：" + key);
                e.onNext((T) cacheController.getCacheObj(key, cache.maxAge()));
                e.onComplete();
            }
        });

    }

    public <T> void saveCacheObj(String cacheKey, String url, Map<String, String> params, T value) {
        String key = getKey(cacheKey, url, params);
        Log.e("Cache", "Saved cache: " + key);
        cacheController.saveCacheObj(key, value);
    }


    private String getKey(String cacheKey, String url, Map<String, String> params) {
        if (TextUtils.isEmpty(cacheKey)) {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            sb.append(params.toString());
            return sb.toString();
        }
        return cacheKey;
    }

}
