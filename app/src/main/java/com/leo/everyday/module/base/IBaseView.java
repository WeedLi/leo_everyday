package com.leo.everyday.module.base;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * 作者：Leo on 2018/1/15 15:47
 * <p>
 * 描述：
 */

public interface IBaseView<T extends IBasePresenter> {

//    /**
//     * 显示加载动画
//     */
//    void onShowLoading();
//
//    /**
//     * 隐藏加载动画
//     */
//    void onHideLoading();
//

    Context getViewContext();


    /**
     * 显示空数据页面
     */
    void showEmptyView();

    /**
     * 显示加载错误
     */
    void showErrorView(boolean isRefresh, boolean isLoadMore);

    void pullRefresh();

    void loadMore();

    void hideRefreshLoading();

    void hideLoadMoreLoading();

    void showLoading();

    void hideLoading();

    /**
     * 设置Presenter
     */
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();

}
