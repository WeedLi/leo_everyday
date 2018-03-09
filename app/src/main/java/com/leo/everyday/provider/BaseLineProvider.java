package com.leo.everyday.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.everyday.R;
import com.leo.everyday.bean.BaseLine;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/1/26 18:09
 * <p>
 * 描述：底线
 */

public class BaseLineProvider extends ItemViewBinder<BaseLine, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_base_line,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull BaseLine line) {

    }
}
