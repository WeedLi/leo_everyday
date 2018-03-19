package com.leo.everyday.networklibrary;

import com.google.gson.Gson;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 作者：Leo on 2018/3/15 11:09
 * <p>
 * 描述：
 */

public class TypeObservableTransformer<T> implements ObservableTransformer {

    private Class<T> aClass;

    public TypeObservableTransformer(Class<T> aClass) {
        this.aClass = aClass;
    }

    @Override
    public ObservableSource apply(Observable upstream) {
        return upstream
                .compose(new StringObservableTransformer())
                .map(new Function<String, T>() {
                    @Override
                    public T apply(String s) throws Exception {
                        return new Gson().fromJson(s, aClass);
                    }

                });
    }
}
