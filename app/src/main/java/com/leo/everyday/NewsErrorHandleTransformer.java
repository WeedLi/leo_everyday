package com.leo.everyday;

import com.leo.everyday.bean.news.NewsTabBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 作者：Leo on 2018/2/28 11:59
 * <p>
 * 描述：新闻错误处理
 */

public class NewsErrorHandleTransformer implements ObservableTransformer {

    @Override
    public ObservableSource apply(Observable upstream) {
//ConnotationsListBean
        return upstream.map(new Function<NewsTabBean, NewsTabBean>() {

            @Override
            public NewsTabBean apply(NewsTabBean newsTabBean) throws Exception {
                if (newsTabBean.error_code > 0) {
                    throw new RuntimeException();
                }
                return newsTabBean;
            }

        }).onErrorResumeNext(new Function<Throwable, Observable>() {
            @Override
            public Observable apply(Throwable throwable) throws Exception {
                return Observable.error(throwable);
            }
        });
    }
}
