package com.leo.everyday;

import com.leo.everyday.bean.connotations.ConnotationsListBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 作者：Leo on 2018/2/28 11:59
 * <p>
 * 描述：内涵错误处理
 */

public class ConnotationsErrorHandleTransformer<T> implements ObservableTransformer {

    @Override
    public ObservableSource apply(Observable upstream) {
//ConnotationsListBean
        return upstream.map(new Function<ConnotationsListBean, ConnotationsListBean>() {

            @Override
            public ConnotationsListBean apply(ConnotationsListBean connotationsListBean) throws Exception {
                if (!"success".equals(connotationsListBean.message)) {
                    throw new RuntimeException();
                }
                return connotationsListBean;
            }

        }).onErrorResumeNext(new Function<Throwable, Observable>() {
            @Override
            public Observable apply(Throwable throwable) throws Exception {
                return Observable.error(throwable);
            }
        });
    }
}
