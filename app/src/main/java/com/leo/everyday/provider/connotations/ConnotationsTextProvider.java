package com.leo.everyday.provider.connotations;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leo.everyday.R;
import com.leo.everyday.bean.connotations.ConnotationsItemBean;
import com.leo.everyday.provider.BaseViewHolder;
import com.leo.everyday.util.ImageLoader;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/2/6 16:07
 * <p>
 * 描述：内涵段子的item
 */

public class ConnotationsTextProvider extends ItemViewBinder<ConnotationsItemBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_connotation_text, parent, false));
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
                Toast.makeText(view.getContext(), "haha", 0).show();
            }
        });
    }
}
