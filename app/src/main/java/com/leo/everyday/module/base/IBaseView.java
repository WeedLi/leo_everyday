package com.leo.everyday.module.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * 作者：Leo on 2018/1/15 15:47
 * <p>
 * 描述：
 */

public interface IBaseView<T> {

    /**
     * 显示加载动画
     */
    void onShowLoading();

    /**
     * 隐藏加载动画
     */
    void onHideLoading();

    /**
     * 显示加载错误
     */
    void onShowNetError();

    /**
     * 设置Presenter
     */
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();

}
