package com.leo.everyday.networklibrary;

import android.content.Context;
import android.text.TextUtils;

import com.leo.everyday.networklibrary.annotation.Cache;
import com.leo.everyday.networklibrary.cache.RxCache;
import com.leo.everyday.networklibrary.utils.NetWorkUtil;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 作者：Leo on 2018/3/9 16:48
 * <p>
 * 描述：处理网络访问与缓存 会有get、post、uploadFile
 */

public class ApiBiz {

    private static ApiBiz _Instance;

    private Context mContext;

    /**
     * 缓存控制类
     */
    private RxCache rxCache;

    private RxNetWorkRequest rxNetWorkRequest;

    private Cache cache;

    public RetrofitClient retorfitClient;

    private String cacheKey;

    private ApiBiz() {

    }

    public static ApiBiz getInstance() {
        if (_Instance == null) {
            synchronized (ApiBiz.class) {
                if (_Instance == null) {
                    _Instance = new ApiBiz();
                }
            }
        }
        return _Instance;
    }

    public void setRetorfitClient(RetrofitClient retorfitClient) {
        if (retorfitClient == null)
            throw new NullPointerException("RetrofitClient can not be null!!!");

        this.retorfitClient = retorfitClient;

        rxNetWorkRequest = new RxNetWorkRequest(retorfitClient);
        mContext = retorfitClient.getContext();
        rxCache = new RxCache(mContext);
    }

    public ApiBiz cacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
        return _Instance;
    }

    private String getCacheKey() {
        return TextUtils.isEmpty(cacheKey) ? cache.cacheKey() : cacheKey;
    }

    /**
     * get请求
     *
     * @param url
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Observable<T> get(final String url, final BaseRequest request, Class<T> clazz) {
        cache = request.getClass().getAnnotation(Cache.class);
        if (cache == null)
            return rxNetWorkRequest.getT(url, request, clazz);
        Observable<T> cacheObservable = rxCache.getCacheObj(getCacheKey(), url, request.getParams(), this.cache);
        Observable<T> networkObservable = rxNetWorkRequest.getT(url, request, clazz)
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        //保存
                        rxCache.saveCacheObj(getCacheKey(), url, request.getParams(), t);
                    }
                });
        return concatOnservable(cacheObservable, networkObservable);
    }

    /**
     * post请求
     *
     * @param url
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Observable<T> post(final String url, final BaseRequest request, Class<T> clazz) {
        cache = request.getClass().getAnnotation(Cache.class);
        if (cache == null)
            return rxNetWorkRequest.postT(url, request, clazz);
        Observable<T> cacheObservable = rxCache
                .getCache(getCacheKey(), url, request.getParams(), this.cache)
                .compose(new TypeObservableTransformer<T>(clazz));
        Observable<T> networkObservable = rxNetWorkRequest.<T>postT(url, request, clazz)
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        rxCache.saveCacheObj(getCacheKey(), url, request.getParams(), t);
                    }
                });
        return concatOnservable(cacheObservable, networkObservable);
    }

    /**
     * get请求
     *
     * @param url
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Observable<String> get(final String url, final BaseRequest request) {
        cache = request.getClass().getAnnotation(Cache.class);
        if (cache == null)
            return rxNetWorkRequest.get(url, request);
        Observable<String> cacheObservable = rxCache.getCache(getCacheKey(), url, request.getParams(), this.cache);
        Observable<String> networkObservable = rxNetWorkRequest.get(url, request)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //保存
                        rxCache.saveCache(getCacheKey(), url, request.getParams(), s);
                    }
                });
        return concatOnservable(cacheObservable, networkObservable);
    }

    /**
     * post请求
     *
     * @param url
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Observable<String> post(final String url, final BaseRequest request) {
        cache = request.getClass().getAnnotation(Cache.class);
        if (cache == null)
            return rxNetWorkRequest.post(url, request);
        Observable<String> cacheObservable = rxCache
                .getCache(getCacheKey(), url, request.getParams(), this.cache);
        Observable<String> networkObservable = rxNetWorkRequest.post(url, request)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        rxCache.saveCache(getCacheKey(), url, request.getParams(), s);
                    }
                });
        return concatOnservable(cacheObservable, networkObservable);
    }


    /**
     * 上传文件
     *
     * @param url
     * @param request
     * @return
     */
    public Observable<String> uploadFile(String url, BaseRequest request) {
        return rxNetWorkRequest.uploadFile(url, request);
    }


    private <T> Observable<T> concatOnservable(Observable<T> cacheObservable, Observable<T> networkObservable) {
        switch (cache.mode()) {
            case REQUEST_FAILED_READ_CACHE:
                if (!NetWorkUtil.isNetworkConnected(mContext)) {
                    return cacheObservable;
                } else {
                    return Observable.concat(networkObservable, cacheObservable).firstElement().toObservable();
                }
            case IF_NONE_CACHE_REQUEST:
                return Observable.concat(cacheObservable, networkObservable).firstElement().toObservable();
            case FIRST_CACHE_THEN_REQUEST:
                return Observable.concat(cacheObservable, networkObservable);
            case NO_CACHE:
            default:
                return networkObservable;
        }
    }

}
