package com.leo.everyday.networklibrary;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * 作者：Leo on 2018/3/15 12:19
 * <p>
 * 描述：
 */

public class StringObservableTransformer implements ObservableTransformer<ResponseBody, String> {

    @Override
    public ObservableSource<String> apply(Observable<ResponseBody> upstream) {
        return upstream.map(new Function<ResponseBody, String>() {
            @Override
            public String apply(ResponseBody responseBody) throws Exception {
                return responseBody.string();
            }
        });
    }
}
