package com.leo.everyday.module.news.tabs;

import com.leo.everyday.module.base.IBasePresenter;
import com.leo.everyday.module.base.IBaseRecyclerView;

/**
 * 作者：Leo on 2018/1/17 11:33
 * <p>
 * 描述：
 */

public interface INewsTab {

    interface View extends IBaseRecyclerView<Presenter> {

    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(boolean isRefresh, boolean isLoadMore, String... category);

    }

}
