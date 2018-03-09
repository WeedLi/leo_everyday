package com.leo.everyday.bean.connotations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：Leo on 2018/2/5 10:34
 * <p>
 * 描述：
 */

public class ConnotationsItemBean {


//    comments	Array
//    display_time	1517796532
//    group	Object
//    online_time	1517796532
//    type	1


    public AdBean ad;

    public List<CommentBean> comments;
    public String display_time;
    public String online_time;
    public int type;
    public GroupBean group;

    public static class GroupBean {

//        large_cover：视频截图
//        user：作者信息
//        play_count：播放次数
//        bury_count：不顶次数
//        digg_count：点赞数量
//        share_count：转发数量
//        content：视频描述


        public LargeCover large_cover;
        public VideoUser user;
        public String play_count;
        public String bury_count;
        public String digg_count;
        public String share_count;
        public String content;
        @SerializedName("360p_video")
        public Video360p video360p;
        @SerializedName("480p_video")
        public Video480p video480p;
        @SerializedName("720p_video")
        public Video720p video720p;
        public String duration;
        public int type;

        //图片
        public List<PicItemBean> large_image_list;
        public PicItemBean large_image;
        public List<PicItemBean> thumb_image_list;

    }

    public static class PicItemBean {
//        height	1225
//        is_gif	false
//        uri	large/5b7e0007fe9844cad810
//        url	http://p1.pstatp.com/large/5b7e0007fe9844cad810.webp
//        url_list	Array
//        width	690

        public int height;
        public int width;
        public boolean is_gif;
        public String uri;
        public String url;
        public List<UrlListBean> url_list;
    }

    public static class VideoUser {
//        avatar_url	http://p1.pstatp.com/medium/5d4c00054cd2dd1fedba
//        followers	46409
//        followings	0
//        is_following	false
//        is_living	false
//        is_pro_user	false
//        medals	Array
//        name	我睡3号铺
//        ugc_count	628
//        user_id	4197383476
//        user_verified	false

        public String name;
        public String avatar_url;

    }


    public static class LargeCover {
//        uri	large/46450012192a00d6fbe7
//        url_list	Array

        public String uri;
        public List<UrlListBean> url_list;
    }

    public static class Video720p {
        public int height;
        public String uri;
        public List<UrlListBean> url_list;
        public int width;
    }

    public static class Video480p {
        public int height;
        public String uri;
        public List<UrlListBean> url_list;
        public int width;
    }

    public static class Video360p {
        public int height;
        public String uri;
        public List<UrlListBean> url_list;
        public int width;
    }

    public static class UrlListBean {
        public String url;
    }

    public static class CommentBean {
        public String avatar_url;
        public String create_time;
        public String text;
        public String user_name;
        public String user_id;

//        avatar_url	http://p1.pstatp.com/thumb/46be000387efdca993a1
//        bury_count	0
//        create_time	1510910906
//        description
//        digg_count	35422
//        group_id	76186496729
//        id	1584304914182190
//        is_digg	0
//        is_pro_user	false
//        medals	Array
//        platform	feifei
//        platform_id	feifei
//        share_type	1
//        share_url	http://m.neihanshequ.com/share/group/76186496729/?comment_id=1584304914182190
//        status	5
//        text	猫:主人，该配合的我尽力了，只能这样子了
//        user_bury	0
//        user_digg	0
//        user_id	6180111820
//        user_name	神-_-落凡尘
//        user_profile_image_url	http://p1.pstatp.com/thumb/46be000387efdca993a1
//        user_profile_url
//        user_verified	false
    }


    public static class AdBean {

    }

}
