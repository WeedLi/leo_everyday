package com.leo.everyday.provider.photo;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leo.everyday.R;
import com.leo.everyday.bean.photo.PhotoItemBean;
import com.leo.everyday.module.photo.content.PhotoContentActivity;
import com.leo.everyday.provider.BaseViewHolder;
import com.leo.everyday.util.ImageLoader;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/1/29 14:02
 * <p>
 * 描述：图片的展示类
 */

public class PhotoItemProvider extends ItemViewBinder<PhotoItemBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_photo, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull PhotoItemBean item) {
        ImageView imageView = holder.getImageView(R.id.iv_photo);
        ImageLoader.loadImage(imageView, item.url);
        View cdPhoto = holder.getView(R.id.cd_photo);
        cdPhoto.setTag(item);
        cdPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoItemBean item = (PhotoItemBean) view.getTag();
//                Toast.makeText(view.getContext(), item.url, Toast.LENGTH_SHORT).show();
                PhotoContentActivity.actionToPhotoContentActivity(view.getContext(), item.url,
                        null, null, 0, true);
            }
        });
    }
}
