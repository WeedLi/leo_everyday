package com.leo.everyday.provider.connotations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.leo.everyday.R;
import com.leo.everyday.bean.connotations.ConnotationsItemBean;
import com.leo.everyday.module.photo.content.PhotoContentActivity;
import com.leo.everyday.provider.BaseViewHolder;
import com.leo.everyday.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/2/6 16:07
 * <p>
 * 描述：内涵图片的item
 */

public class ConnotationsPicProvider extends ItemViewBinder<ConnotationsItemBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_connotation_pic, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ConnotationsItemBean item) {
        if (item.group.user != null) {
            ImageLoader.loadImage(holder.getImageView(R.id.iv_head_user), item.group.user.avatar_url);
            holder.setText(R.id.tv_username, item.group.user.name);
        }
        holder.setText(R.id.tv_content, item.group.content);
        holder.getView(R.id.card_view_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "haha", Toast.LENGTH_SHORT).show();
            }
        });

        NineGridImageView nineGridImageView = (NineGridImageView) holder.getView(R.id.ninegrid_iv_connotations);
        nineGridImageView.setAdapter(new NineGridImageViewAdapter<ConnotationsItemBean.PicItemBean>() {

            @Override
            protected void onDisplayImage(Context context, ImageView imageView, ConnotationsItemBean.PicItemBean picItemBean) {
                ImageLoader.loadImage(imageView, picItemBean.url_list.get(0).url, picItemBean.is_gif + "");
            }

            @Override
            protected void onItemImageClick(Context context, int index, List<ConnotationsItemBean.PicItemBean> list) {
                super.onItemImageClick(context, index, list);
                List<String> urls = new ArrayList<>();
                List<String> isGifs = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    ConnotationsItemBean.PicItemBean picItemBean = list.get(i);
                    urls.add(picItemBean.url_list.get(0).url);
                    isGifs.add(picItemBean.is_gif + "");
                }
                PhotoContentActivity.actionToPhotoContentActivity(context, "", urls, isGifs, index, false);
            }

            @Override
            protected ImageView generateImageView(Context context) {
                return super.generateImageView(context);
            }
        });

        if (item.group.thumb_image_list == null || item.group.thumb_image_list.isEmpty()) {
            if (item.group.large_image != null) {
                List<ConnotationsItemBean.PicItemBean> list = new ArrayList();
                list.add(item.group.large_image);
                nineGridImageView.setImagesData(list);
            } else {
                nineGridImageView.setVisibility(View.GONE);
            }
        } else {
            nineGridImageView.setImagesData(item.group.thumb_image_list);
//            nineGridImageView.setImagesData(item.group.large_image_list);
        }
    }
}
