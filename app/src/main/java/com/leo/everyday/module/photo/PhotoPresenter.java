package com.leo.everyday.module.photo;

import android.util.Log;

import com.leo.everyday.bean.photo.PhotoBean;
import com.leo.everyday.networklibrary.ApiBiz;
import com.leo.everyday.request.PhotoRequest;
import com.leo.everyday.transformer.PhotoErrorHandleTransformer;

import io.reactivex.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Leo on 2018/1/29 11:48
 * <p>
 * 描述：
 */
@SuppressWarnings("unchecked")
public class PhotoPresenter implements IPhotoItem.Presenter {

    private IPhotoItem.View view;
    private int page = 1;

    public PhotoPresenter(IPhotoItem.View view) {
        this.view = view;
    }

    @Override
    public void doRefresh() {
        doLoadData(true, false);
    }

    @Override
    public void doLoadMore() {
        doLoadData(false, true);
    }

    @Override
    public void doLoadData(final boolean isRefresh, final boolean isLoadMore) {
        if (isRefresh)
            page = 1;

        ApiBiz.getInstance()
                .get("http://gank.io/api/data/福利/10/" + page, new PhotoRequest(), PhotoBean.class)
                .compose(new PhotoErrorHandleTransformer())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("PhotoPresenter", "onSubscribe");
                        if (!isLoadMore && !isRefresh)
                            view.showLoading();
                    }

                    @Override
                    public void onNext(PhotoBean photoBean) {
                        Log.e("PhotoPresenter", "onNext：");
                        if (isRefresh) {
                            view.onSetRefreshData(photoBean.results);
                            return;
                        }
                        if (isLoadMore) {
                            view.onSetLoadMoreData(photoBean.results);
                            page++;
                            return;
                        }
                        if (photoBean.results.isEmpty())
                            view.showEmptyView();
                        else
                            view.onSetData(photoBean.results);
                        page++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("PhotoPresenter", "onError：" + e.toString());
                        view.showErrorView(isRefresh, isLoadMore);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("PhotoPresenter", "onComplete");
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
