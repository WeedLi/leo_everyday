package com.leo.everyday.provider.news;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.everyday.R;
import com.leo.everyday.bean.news.NewsTabBean;
import com.leo.everyday.module.news.NewsContentActivity;
import com.leo.everyday.provider.BaseViewHolder;
import com.leo.everyday.util.ImageLoader;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/1/25 17:43
 * <p>
 * 描述：
 */

public class NewsOnePicProvider extends ItemViewBinder<NewsTabBean.ResultBean.DataBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_one_pic, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull NewsTabBean.ResultBean.DataBean dataBean) {
        holder.setText(R.id.title, dataBean.title);
        if (!TextUtils.isEmpty(dataBean.thumbnail_pic_s))
            ImageLoader.loadImage(holder.getImageView(R.id.imageview1), dataBean.thumbnail_pic_s);
        else
            holder.getImageView(R.id.imageview1).setVisibility(View.GONE);
        holder.getRootView().setTag(dataBean);
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsTabBean.ResultBean.DataBean dataBean = (NewsTabBean.ResultBean.DataBean) v.getTag();
                NewsContentActivity.actionToNewsContentActivity(v.getContext(), dataBean.url, dataBean.title);
            }
        });
    }

}
