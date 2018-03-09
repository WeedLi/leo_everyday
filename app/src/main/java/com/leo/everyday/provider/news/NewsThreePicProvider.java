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
 * 作者：Leo on 2018/1/25 17:54
 * <p>
 * 描述：
 */

public class NewsThreePicProvider extends ItemViewBinder<NewsTabBean.ResultBean.DataBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_three_pic, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull NewsTabBean.ResultBean.DataBean dataBean) {
        holder.setText(R.id.title, dataBean.title);
        if (!TextUtils.isEmpty(dataBean.thumbnail_pic_s))
            ImageLoader.loadImage(holder.getImageView(R.id.imageview1), dataBean.thumbnail_pic_s);
        else
            holder.getImageView(R.id.imageview1).setVisibility(View.GONE);

        if (!TextUtils.isEmpty(dataBean.thumbnail_pic_s02))
            ImageLoader.loadImage(holder.getImageView(R.id.imageview2), dataBean.thumbnail_pic_s02);
        else
            holder.getImageView(R.id.imageview2).setVisibility(View.GONE);

        if (!TextUtils.isEmpty(dataBean.thumbnail_pic_s03))
            ImageLoader.loadImage(holder.getImageView(R.id.imageview3), dataBean.thumbnail_pic_s03);
        else
            holder.getImageView(R.id.imageview3).setVisibility(View.GONE);

        View rootView = holder.getRootView();
        rootView.setTag(dataBean);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsTabBean.ResultBean.DataBean dataBean1 = (NewsTabBean.ResultBean.DataBean) v.getTag();
//                Toast.makeText(InitApp.AppContext, dataBean1.title, Toast.LENGTH_SHORT).show();
                NewsContentActivity.actionToNewsContentActivity(v.getContext(), dataBean1.url, dataBean1.title);
            }
        });
    }

}
