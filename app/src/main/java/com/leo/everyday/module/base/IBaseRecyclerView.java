package com.leo.everyday.module.base;

import java.util.List;

/**
 * 作者：Leo on 2018/1/15 15:50
 * <p>
 * 描述：
 */

public interface IBaseRecyclerView<T extends IBasePresenter> extends IBaseView<T> {

    /**
     * 设置适配器
     *
     * @param list
     */
    void onSetData(List<?> list);


    void onSetRefreshData(List<?> list);


    void onSetLoadMoreData(List<?> list);

}
