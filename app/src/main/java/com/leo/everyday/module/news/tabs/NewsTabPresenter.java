package com.leo.everyday.module.news.tabs;

import android.util.Log;

import com.leo.everyday.NewsErrorHandleTransformer;
import com.leo.everyday.RetrofitFactory;
import com.leo.everyday.api.INewsApi;
import com.leo.everyday.bean.news.NewsTabBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Leo on 2018/1/17 11:33
 * <p>
 * 描述：
 */

public class NewsTabPresenter implements INewsTab.Presenter {

    private static final String TAG = "NewsArticlePresenter";
    private INewsTab.View view;
    private String type;

    public NewsTabPresenter(INewsTab.View view) {
        this.view = view;
    }

    @Override
    public void doRefresh() {
        doLoadData(true, false, type);
    }

    @Override
    public void doLoadMore() {
        doLoadData(false, true, type);
    }

    @Override
    public void doLoadData(final boolean isRefresh, final boolean isLoadMore, String... type) {
        try {
            if (this.type == null) {
                this.type = type[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        RetrofitFactory.getRetrofit().create(INewsApi.class)
                .getNewsItems("233358d4fdb8a6e0f1dd4868f328e752", this.type)
                .compose(new NewsErrorHandleTransformer())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("NewsTabPresenter", "onSubscribe");
                        if (!isLoadMore && !isRefresh)
                            view.showLoading();
                    }

                    @Override
                    public void onNext(NewsTabBean newsTabBean) {
                        Log.e("NewsTabPresenter", "onNext");
                        if (isRefresh) {
                            view.onSetRefreshData(newsTabBean.result.data);
                            return;
                        }
                        if (isLoadMore) {
                            view.onSetLoadMoreData(newsTabBean.result.data);
                            return;
                        }
                        if (newsTabBean.result.data.isEmpty())
                            view.showEmptyView();
                        else
                            view.onSetData(newsTabBean.result.data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("NewsTabPresenter", "onError" + e.toString());
                        view.showErrorView(isRefresh, isLoadMore);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("NewsTabPresenter", "onComplete");
                        if (isRefresh)
                            view.hideRefreshLoading();
                        else if (isLoadMore)
                            view.hideLoadMoreLoading();
                        else
                            view.hideLoading();
                    }
                });
    }

}
