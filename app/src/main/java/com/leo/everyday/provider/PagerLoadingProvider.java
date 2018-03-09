package com.leo.everyday.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.everyday.R;
import com.leo.everyday.bean.PagerLoadingBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/2/27 10:25
 * <p>
 * 描述：loading页面
 */

public class PagerLoadingProvider extends ItemViewBinder<PagerLoadingBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.layout_pager_loading, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull PagerLoadingBean item) {

    }
}
