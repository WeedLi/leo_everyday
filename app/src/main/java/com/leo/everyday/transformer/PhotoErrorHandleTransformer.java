package com.leo.everyday.transformer;

import com.leo.everyday.bean.photo.PhotoBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 作者：Leo on 2018/2/28 11:59
 * <p>
 * 描述：新闻错误处理
 */

public class PhotoErrorHandleTransformer implements ObservableTransformer {

    @Override
    public ObservableSource apply(Observable upstream) {
        return upstream.map(new Function<PhotoBean, PhotoBean>() {

            @Override
            public PhotoBean apply(PhotoBean photoBean) throws Exception {
                if (photoBean.error) {
                    throw new RuntimeException();
                }
                return photoBean;
            }

        }).onErrorResumeNext(new Function<Throwable, Observable>() {
            @Override
            public Observable apply(Throwable throwable) throws Exception {
                return Observable.error(throwable);
            }
        });
    }
}
