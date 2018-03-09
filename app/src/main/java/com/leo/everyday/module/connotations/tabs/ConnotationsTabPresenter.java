package com.leo.everyday.module.connotations.tabs;

import android.text.TextUtils;
import android.util.Log;

import com.leo.everyday.ConnotationsErrorHandleTransformer;
import com.leo.everyday.RetrofitFactory;
import com.leo.everyday.api.IConnotationsApi;
import com.leo.everyday.bean.connotations.ConnotationsItemBean;
import com.leo.everyday.bean.connotations.ConnotationsListBean;
import com.leo.everyday.util.ConnotationsUrlUtil;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Leo on 2018/1/31 11:14
 * <p>
 * 描述：
 */

public class ConnotationsTabPresenter implements IConnotationsTab.Presenter {

    private IConnotationsTab.View view;
    private int currTab;
    private String min_time;
    private String max_time;


    public ConnotationsTabPresenter(IConnotationsTab.View view) {
        this.view = view;
    }

    private String getNetUrl(boolean isRefresh) {
        if (isRefresh) {
            if (TextUtils.isEmpty(min_time))
                min_time = System.currentTimeMillis() + "";
            return ConnotationsUrlUtil.spliteUrl(view.getViewContext(), min_time);
        }
        if (TextUtils.isEmpty(max_time))
            max_time = System.currentTimeMillis() + "";
        return ConnotationsUrlUtil.spliteUrl(view.getViewContext(), max_time);
    }

    @Override
    public void doRefresh() {
        onLoadData(true, false, currTab);
    }

    @Override
    public void doLoadMore() {
        onLoadData(false, true, currTab);
    }

    @Override
    public void onLoadData(final boolean isRefresh, final boolean isLoadMore, int type) {
        this.currTab = type;
        RetrofitFactory.getRetrofit().create(IConnotationsApi.class)
                .getVideoListData(getNetUrl(isRefresh))
                .compose(new ConnotationsErrorHandleTransformer())
                .map(new Function<ConnotationsListBean, List<ConnotationsItemBean>>() {
                    @Override
                    public List<ConnotationsItemBean> apply(ConnotationsListBean connotationsListBean) throws Exception {
                        Log.e("ConnotationsTab", Thread.currentThread().getName());
                        List<ConnotationsItemBean> videoItemBeans = connotationsListBean.data.data;
                        max_time = connotationsListBean.data.max_time;
                        min_time = connotationsListBean.data.min_time;
                        //去除广告
                        Iterator<ConnotationsItemBean> iterator = videoItemBeans.iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().ad != null)
                                iterator.remove();
                        }
                        return videoItemBeans;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ConnotationsItemBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("VideoTabPresenter", "onSubscribe");
                        //建立网络链接
                        if (!isLoadMore && !isRefresh)
                            view.showLoading();
                    }

                    @Override
                    public void onNext(List<ConnotationsItemBean> videoItemBeans) {
                        Log.e("VideoTabPresenter", "onNext");
                        if (isRefresh) {
                            view.onSetRefreshData(videoItemBeans);
                            return;
                        }
                        if (isLoadMore) {
                            view.onSetLoadMoreData(videoItemBeans);
                            return;
                        }
                        if (videoItemBeans.isEmpty())
                            view.showEmptyView();
                        else
                            view.onSetData(videoItemBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("VideoTabPresenter", "onError：" + e.toString());
                        view.showErrorView(isRefresh, isLoadMore);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("VideoTabPresenter", "onComplete...isRefresh：" + isRefresh);
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
