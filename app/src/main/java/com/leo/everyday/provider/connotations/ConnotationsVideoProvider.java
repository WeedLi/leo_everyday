package com.leo.everyday.provider.connotations;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.everyday.R;
import com.leo.everyday.bean.connotations.ConnotationsItemBean;
import com.leo.everyday.provider.BaseViewHolder;
import com.leo.everyday.util.ImageLoader;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：Leo on 2018/2/1 16:58
 * <p>
 * 描述：
 */

public class ConnotationsVideoProvider extends ItemViewBinder<ConnotationsItemBean, BaseViewHolder> {

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_video_list, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ConnotationsItemBean item) {
        ImageLoader.loadImage(holder.getImageView(R.id.iv_head_user), item.group.user.avatar_url);
        holder.setText(R.id.tv_username, item.group.user.name);
        holder.setText(R.id.tv_content, item.group.content);

        String videoUrl = "";
        if (item.group.video720p != null && !item.group.video720p.url_list.isEmpty()) {
            videoUrl = item.group.video720p.url_list.get(0).url;
        } else if (item.group.video480p != null && !item.group.video480p.url_list.isEmpty()) {
            videoUrl = item.group.video480p.url_list.get(0).url;
        } else if (item.group.video360p != null && !item.group.video360p.url_list.isEmpty()) {
            videoUrl = item.group.video360p.url_list.get(0).url;
        }

        String imageUrl = "";
        if (item.group.large_cover != null && !item.group.large_cover.url_list.isEmpty()) {
            imageUrl = item.group.large_cover.url_list.get(0).url;
        }

        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) holder.getView(R.id.video_palyer_jz);
        jzVideoPlayerStandard.setUp(videoUrl
                , JZVideoPlayer.SCREEN_WINDOW_LIST, "哈哈哈哈哈");
        ImageLoader.loadImage(jzVideoPlayerStandard.thumbImageView, imageUrl);

    }

}
