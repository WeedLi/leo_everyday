package com.leo.everyday.module.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.leo.everyday.R;
import com.leo.everyday.RxBus;

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

public abstract class BaseRecyclerView extends LazyLoadFragment implements IBaseRecyclerView {

    public static final String TAG = "BaseRecyclerView";
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;
    protected MultiTypeAdapter multiAdapter;
    protected Items oldItems = new Items();
    protected boolean canLoadMore = false;
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
    public void onShowLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onHideLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onShowSuccessFinish() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (oldItems.size() > 0) {
                    Items newItems = new Items(oldItems);
                    newItems.remove(newItems.size() - 1);
//                    newItems.add(new LoadingEndBean());
                    multiAdapter.setItems(newItems);
                    multiAdapter.notifyDataSetChanged();
                } else if (oldItems.size() == 0) {
//                    oldItems.add(new LoadingEndBean());
                    multiAdapter.setItems(oldItems);
                    multiAdapter.notifyDataSetChanged();
                }
                canLoadMore = false;
            }
        });
    }


    @Override
    public void onShowNetError() {
        Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_SHORT).show();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                multiAdapter.setItems(new Items());
                multiAdapter.notifyDataSetChanged();
                canLoadMore = false;
            }
        });
    }

    @Override
    public void onDestroy() {
        RxBus.getInstance().unregister(BaseRecyclerView.TAG, observable);
        super.onDestroy();
    }
}
