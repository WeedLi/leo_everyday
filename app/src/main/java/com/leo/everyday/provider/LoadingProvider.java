package com.leo.everyday.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.everyday.R;
import com.leo.everyday.bean.LoadingBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/1/29 17:51
 * <p>
 * 描述：
 */

public class LoadingProvider extends ItemViewBinder<LoadingBean, BaseViewHolder> {


    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_loading, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull LoadingBean item) {

    }

}
