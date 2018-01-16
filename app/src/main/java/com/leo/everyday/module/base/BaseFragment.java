package com.leo.everyday.module.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * 作者：Leo on 2018/1/15 17:34
 * <p>
 * 描述：
 */

public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseView<T> {

    protected T presenter;

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData() throws NullPointerException;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }

    /**
     * 绑定生命周期 onDestory的时候回收
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
