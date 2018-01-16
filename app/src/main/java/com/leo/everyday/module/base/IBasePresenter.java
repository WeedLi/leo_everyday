package com.leo.everyday.module.base;

/**
 * 作者：Leo on 2018/1/15 17:32
 * <p>
 * 描述：
 */

public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();


    /**
     * 显示网络错误
     */
    void doShowNetError();

}
