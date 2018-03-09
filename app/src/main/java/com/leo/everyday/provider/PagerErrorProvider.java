package com.leo.everyday.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.everyday.R;
import com.leo.everyday.bean.PagerErrorBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/2/27 10:25
 * <p>
 * 描述：错误页面
 */

public class PagerErrorProvider extends ItemViewBinder<PagerErrorBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.layout_pager_error,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull PagerErrorBean item) {

    }
}
