package com.leo.everyday.module.photo;

import android.view.View;

import com.leo.everyday.bean.LoadingBean;
import com.leo.everyday.bean.PagerEmptyBean;
import com.leo.everyday.bean.PagerErrorBean;
import com.leo.everyday.bean.PagerLoadingBean;
import com.leo.everyday.bean.photo.PhotoItemBean;
import com.leo.everyday.module.base.BaseRecyclerView;
import com.leo.everyday.provider.LoadingProvider;
import com.leo.everyday.provider.PagerEmptyProvider;
import com.leo.everyday.provider.PagerErrorProvider;
import com.leo.everyday.provider.PagerLoadingProvider;
import com.leo.everyday.provider.photo.PhotoItemProvider;

import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 作者：Leo on 2018/1/15 11:11
 * <p>
 * 描述：多媒体页面
 */

public class PhotoTabLayout extends BaseRecyclerView<IPhotoItem.Presenter> implements IPhotoItem.View {

    private static final String TAG = "PhotoTabLayout";

    private static PhotoTabLayout instance;

    public static PhotoTabLayout getInstance() {
        if (instance == null) {
            instance = new PhotoTabLayout();
        }
        return instance;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        multiAdapter = new MultiTypeAdapter();
        multiAdapter.register(PhotoItemBean.class, new PhotoItemProvider());

        multiAdapter.register(LoadingBean.class, new LoadingProvider());
        multiAdapter.register(PagerEmptyBean.class, new PagerEmptyProvider());
        multiAdapter.register(PagerErrorBean.class, new PagerErrorProvider());
        multiAdapter.register(PagerLoadingBean.class, new PagerLoadingProvider());

        recyclerView.setAdapter(multiAdapter);
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        refreshLayout.setEnabled(false);
    }

    @Override
    protected void initData() throws NullPointerException {
        super.initData();
        presenter.doLoadData(false, false);
    }

    @Override
    public void onSetData(List<?> list) {
        oldItems.clear();
        oldItems.addAll(list);
        oldItems.add(LoadingBean.getInstance());
        multiAdapter.setItems(oldItems);
        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(IPhotoItem.Presenter presenter) {
        if (presenter == null)
            this.presenter = new PhotoPresenter(this);
    }
}
