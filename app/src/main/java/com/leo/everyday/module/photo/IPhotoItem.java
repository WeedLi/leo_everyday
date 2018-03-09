package com.leo.everyday.module.photo;

import com.leo.everyday.module.base.IBasePresenter;
import com.leo.everyday.module.base.IBaseRecyclerView;

/**
 * 作者：Leo on 2018/1/29 11:44
 * <p>
 * 描述：
 */

public interface IPhotoItem {

    interface Presenter extends IBasePresenter {

        /**
         * 做网络加载
         */
        void doLoadData(boolean isRefresh, boolean isLoadMore);

    }

    interface View extends IBaseRecyclerView<Presenter> {

    }
}
