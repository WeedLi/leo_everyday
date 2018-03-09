package com.leo.everyday.module.connotations.tabs;

import com.leo.everyday.module.base.IBasePresenter;
import com.leo.everyday.module.base.IBaseRecyclerView;

/**
 * 作者：Leo on 2018/1/31 11:13
 * <p>
 * 描述：
 */

public interface IConnotationsTab {

    interface View extends IBaseRecyclerView<Presenter> {

    }

    interface Presenter extends IBasePresenter {

        void onLoadData(boolean isRefresh, boolean isLoadMore, int type);

    }

}
