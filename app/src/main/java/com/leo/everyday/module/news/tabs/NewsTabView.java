package com.leo.everyday.module.news.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.leo.everyday.bean.BaseLine;
import com.leo.everyday.bean.LoadingBean;
import com.leo.everyday.bean.PagerEmptyBean;
import com.leo.everyday.bean.PagerErrorBean;
import com.leo.everyday.bean.PagerLoadingBean;
import com.leo.everyday.bean.news.NewsTabBean;
import com.leo.everyday.module.base.BaseRecyclerView;
import com.leo.everyday.provider.BaseLineProvider;
import com.leo.everyday.provider.LoadingProvider;
import com.leo.everyday.provider.PagerEmptyProvider;
import com.leo.everyday.provider.PagerErrorProvider;
import com.leo.everyday.provider.PagerLoadingProvider;
import com.leo.everyday.provider.news.NewsOnePicProvider;
import com.leo.everyday.provider.news.NewsThreePicProvider;

import java.util.List;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 作者：Leo on 2018/1/17 11:34
 * <p>
 * 描述：
 */

public class NewsTabView extends BaseRecyclerView<INewsTab.Presenter> implements INewsTab.View {

    private static final String TAG = "NewsTabView";
    private String channelId;

    public static NewsTabView newInstance(String channelId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, channelId);
        NewsTabView newsTabView = new NewsTabView();
        newsTabView.setArguments(bundle);
        return newsTabView;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        multiAdapter = new MultiTypeAdapter(oldItems);
        multiAdapter.register(NewsTabBean.ResultBean.DataBean.class)
                .to(new NewsOnePicProvider(), new NewsThreePicProvider())
                .withClassLinker(new ClassLinker<NewsTabBean.ResultBean.DataBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<NewsTabBean.ResultBean.DataBean, ?>> index(@NonNull NewsTabBean.ResultBean.DataBean item) {
                        if (TextUtils.isEmpty(item.thumbnail_pic_s02) || TextUtils.isEmpty(item.thumbnail_pic_s03))
                            return NewsOnePicProvider.class;
                        else
                            return NewsThreePicProvider.class;
                    }
                });

        multiAdapter.register(BaseLine.class, new BaseLineProvider());
        multiAdapter.register(LoadingBean.class, new LoadingProvider());
        multiAdapter.register(PagerEmptyBean.class, new PagerEmptyProvider());
        multiAdapter.register(PagerErrorBean.class, new PagerErrorProvider());
        multiAdapter.register(PagerLoadingBean.class, new PagerLoadingProvider());

        recyclerView.setAdapter(multiAdapter);
        refreshLayout.setEnabled(false);
    }

    @Override
    protected void initData() throws NullPointerException {
        super.initData();
        channelId = getArguments().getString(TAG);
    }

    @Override
    public void handleLazyData() {
        super.handleLazyData();
        presenter.doLoadData(false, false, channelId);
    }

    @Override
    public void onSetData(List list) {
        oldItems.clear();
        oldItems.addAll(list);
        oldItems.add(new BaseLine());
        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(INewsTab.Presenter presenter) {
        if (presenter == null)
            this.presenter = new NewsTabPresenter(this);
    }
}
