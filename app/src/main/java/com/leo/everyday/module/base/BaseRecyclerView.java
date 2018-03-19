package com.leo.everyday.module.base;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.leo.everyday.R;
import com.leo.everyday.RxBus;
import com.leo.everyday.bean.LoadingBean;
import com.leo.everyday.bean.PagerEmptyBean;
import com.leo.everyday.bean.PagerErrorBean;
import com.leo.everyday.bean.PagerLoadingBean;
import com.leo.everyday.widget.OnLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 作者：Leo on 2018/1/16 13:43
 * <p>
 * 描述：RecyclerView为主页面的基类
 */

public abstract class BaseRecyclerView<T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseRecyclerView<T> {

    public static final String TAG = "BaseRecyclerView";
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;
    protected MultiTypeAdapter multiAdapter;
    protected Items oldItems = new Items();
    protected Observable<Integer> observable;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        refreshLayout = view.findViewById(R.id.refresh_layout);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                pullRefresh();
            }
        });

        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //上拉加载
                if (oldItems.contains(LoadingBean.getInstance())) {
                    BaseRecyclerView.this.loadMore();
                }
            }
        });
    }

    @Override
    protected void initData() throws NullPointerException {

    }

    @Override

    public void handleLazyData() {
        observable = RxBus.getInstance().register(BaseRecyclerView.TAG);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                multiAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSetRefreshData(List<?> list) {
        oldItems.clear();
        oldItems.addAll(0, list);
        oldItems.add(LoadingBean.getInstance());
        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSetLoadMoreData(List<?> list) {
        if (oldItems.size() > 50 && list.size() > 8)
            oldItems.clear();
        oldItems.remove(LoadingBean.getInstance());
        oldItems.addAll(list);
        oldItems.add(LoadingBean.getInstance());
        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public void pullRefresh() {
        presenter.doRefresh();
    }

    @Override
    public void loadMore() {
        presenter.doLoadMore();
    }

    @Override
    public void showEmptyView() {
        oldItems.clear();
        oldItems.add(PagerEmptyBean.getInstance());
        multiAdapter.setItems(oldItems);
        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorView(boolean isPullRefresh, boolean isLoadMore) {
        //如果本来有数据的话 弹toast
        if (isLoadMore || isPullRefresh) {
            hideRefreshLoading();
            hideLoadMoreLoading();
            Toast.makeText(getViewContext(), "网络访问出错", Toast.LENGTH_SHORT).show();
            return;
        }
        oldItems.clear();
        oldItems.add(PagerErrorBean.getInstance());
        multiAdapter.setItems(oldItems);
        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        oldItems.clear();
        oldItems.add(PagerLoadingBean.getInstance());
        multiAdapter.setItems(oldItems);
        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideLoading() {
        oldItems.remove(PagerLoadingBean.getInstance());
        multiAdapter.setItems(oldItems);
        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideRefreshLoading() {
        refreshLayout.setRefreshing(false);
        Log.e("VideoTabPresenter", "run 隐藏刷新");
    }

    @Override
    public void hideLoadMoreLoading() {
//        oldItems.remove(LoadingBean.getInstance());
//        multiAdapter.setItems(oldItems);
//        multiAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        RxBus.getInstance().unregister(BaseRecyclerView.TAG, observable);
        super.onDestroy();
    }
}
