package com.leo.everyday.module.connotations.tabs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.leo.everyday.bean.LoadingBean;
import com.leo.everyday.bean.PagerEmptyBean;
import com.leo.everyday.bean.PagerErrorBean;
import com.leo.everyday.bean.PagerLoadingBean;
import com.leo.everyday.bean.connotations.ConnotationsItemBean;
import com.leo.everyday.module.base.BaseRecyclerView;
import com.leo.everyday.provider.LoadingProvider;
import com.leo.everyday.provider.PagerEmptyProvider;
import com.leo.everyday.provider.PagerErrorProvider;
import com.leo.everyday.provider.PagerLoadingProvider;
import com.leo.everyday.provider.connotations.ConnotationsPicProvider;
import com.leo.everyday.provider.connotations.ConnotationsTextProvider;
import com.leo.everyday.provider.connotations.ConnotationsVideoProvider;

import java.util.List;

import io.reactivex.annotations.NonNull;
import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 作者：Leo on 2018/1/31 11:14
 * <p>
 * 描述：
 */

public class ConnotationsTabView extends BaseRecyclerView<IConnotationsTab.Presenter> implements IConnotationsTab.View {

    public static ConnotationsTabView newInstance(int type) {
        ConnotationsTabView connotationsTabView = new ConnotationsTabView();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        connotationsTabView.setArguments(bundle);
        return connotationsTabView;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        multiAdapter = new MultiTypeAdapter();
        multiAdapter.register(ConnotationsItemBean.class)
                .to(new ConnotationsVideoProvider(), new ConnotationsPicProvider(), new ConnotationsTextProvider())
                .withClassLinker(new ClassLinker<ConnotationsItemBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<ConnotationsItemBean, ?>> index(@NonNull ConnotationsItemBean connotationsItemBean) {
                        //逐个判断 视频 图片 段子
                        if (connotationsItemBean.group.video360p != null
                                || connotationsItemBean.group.video480p != null || connotationsItemBean.group.video720p != null) {
                            return ConnotationsVideoProvider.class;
                        }
                        //逐个判断 视频 图片 段子
                        if (connotationsItemBean.group.large_image != null
                                || connotationsItemBean.group.thumb_image_list != null || connotationsItemBean.group.large_image_list != null) {
                            return ConnotationsPicProvider.class;
                        }
                        return ConnotationsTextProvider.class;
                    }
                });

        multiAdapter.register(LoadingBean.class, new LoadingProvider());
        multiAdapter.register(PagerEmptyBean.class, new PagerEmptyProvider());
        multiAdapter.register(PagerErrorBean.class, new PagerErrorProvider());
        multiAdapter.register(PagerLoadingBean.class, new PagerLoadingProvider());
        recyclerView.setAdapter(multiAdapter);
    }

    @Override
    protected void initData() throws NullPointerException {
        super.initData();
//        presenter.onLoadData(getArguments().getString("url"));
    }

    @Override
    public void handleLazyData() {
        super.handleLazyData();
        presenter.onLoadData(false, false, getArguments().getInt("type"));
    }

    @Override
    public void onSetData(List<?> list) {
        Log.e("LLL", "适配列表：" + list.size());
        oldItems.clear();
        oldItems.addAll(list);
        oldItems.add(LoadingBean.getInstance());
        multiAdapter.setItems(oldItems);
        multiAdapter.notifyDataSetChanged();
    }


    @Override
    public void setPresenter(IConnotationsTab.Presenter presenter) {
        if (presenter == null)
            this.presenter = new ConnotationsTabPresenter(this);
    }

}
