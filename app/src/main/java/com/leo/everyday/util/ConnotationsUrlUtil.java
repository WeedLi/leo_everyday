package com.leo.everyday.util;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 作者：Leo on 2018/3/1 13:53
 * <p>
 * 描述：内涵链接的工具类
 */

public class ConnotationsUrlUtil {



    public static String spliteUrl(Context context, String min_time) {
        long am_longitude = 110;
        long am_latitude = 120;
        String am_city = "北京";
        String am_loc_time = System.currentTimeMillis() + "";
//        String min_time = "1465232121";

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int screen_width = dm.widthPixels;         // 屏幕宽度（像素）
        int screen_height = dm.heightPixels;       // 屏幕高度（像素）

        String url = " http://iu.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-101&message_cursor=-1&am_longitude=" + am_longitude + "&am_latitude=" + am_latitude + "&am_city=" + am_city + "&am_loc_time=" + am_loc_time + "&count=30" +
                "&min_time=" + min_time + "&screen_width=" + screen_width + "&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612" +
                "&version_name=6.1.2&device_platform=android&ssmix=a&device_type=" + Build.MODEL + "&device_brand=" + Build.DEVICE + "&os_api=28&os_version=" + Build.VERSION.RELEASE + "&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=" + screen_width + "*" + screen_height + "&dpi=620&update_version_code=6120";
        return url;
    }


}
