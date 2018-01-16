package com.leo.everyday.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 作者：Leo on 2018/1/15 17:52
 * <p>
 * 描述：懒加载的Fragment
 */

public abstract class LazyLoadFragment extends BaseFragment {

    protected boolean isViewPrepareded;
    protected boolean isDataPrepareded;
    protected boolean isVisibleToUser;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isViewPrepareded = true;
    }

    //ViewPager转页的时候使用  事务的show和hide 是用另外的方法
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareObtainData();
    }

    private void prepareObtainData() {
        if (isViewPrepareded && isVisibleToUser && !isDataPrepareded) {
            isDataPrepareded = true;
            handleLazyData();
        }
    }

    /**
     * 处理数据
     */
    public abstract void handleLazyData();


}
