package com.leo.everyday.module.connotations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.everyday.R;
import com.leo.everyday.adapter.BasePagerAdapter;
import com.leo.everyday.module.connotations.tabs.ConnotationsTabView;

import java.util.ArrayList;
import java.util.List;

import static com.leo.everyday.Constant.TAB_PIC;
import static com.leo.everyday.Constant.TAB_RECOMMEND;
import static com.leo.everyday.Constant.TAB_SHOW;
import static com.leo.everyday.Constant.TAB_TEXT;
import static com.leo.everyday.Constant.TAB_VIDEO;

/**
 * 作者：Leo on 2018/1/15 11:11
 * <p>
 * 描述：多媒体页面
 */

public class ConnotationsTabLayout extends Fragment {

    private static ConnotationsTabLayout instance;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    public static ConnotationsTabLayout getInstance() {
        if (instance == null) {
            instance = new ConnotationsTabLayout();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_video_tab, container, false);
        ViewPager viewPager = view.findViewById(R.id.vp_video);
        TabLayout tabLayout = view.findViewById(R.id.tl_video);
        tabLayout.setupWithViewPager(viewPager);
        initTabs();
        viewPager.setAdapter(new BasePagerAdapter(getFragmentManager(), fragmentList, titleList));
        viewPager.setOffscreenPageLimit(titleList.size());
        return view;
    }

    private void initTabs() {
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        String[] stringArray = getActivity().getResources().getStringArray(R.array.videoCategory);
        for (int i = 0; i < stringArray.length; i++) {
            titleList.add(stringArray[i]);
            switch (i) {
                case 0:
                    //推荐
                    fragmentList.add(ConnotationsTabView.newInstance(TAB_RECOMMEND));
                    break;
                case 1:
                    //视频
                    fragmentList.add(ConnotationsTabView.newInstance(TAB_VIDEO));
//                    fragmentList.add(ConnotationsTabView.newInstance("http://iu.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-104&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=北京市&am_loc_time=1463225362814&count=30&min_time=1489143837&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120"));
                    break;
                case 2:
                    //图片
                    fragmentList.add(ConnotationsTabView.newInstance(TAB_PIC));
//                    fragmentList.add(ConnotationsTabView.newInstance("http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-103&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=北京市&am_loc_time=1489226058493&count=30&min_time=1489226061&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120"));
                    break;
                case 3:
                    //段子
                    fragmentList.add(ConnotationsTabView.newInstance(TAB_TEXT));
//                    fragmentList.add(ConnotationsTabView.newInstance("http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-102&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=北京市&am_loc_time=1489226058493&count=30&min_time=1489205901&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120"));
                    break;
                case 4:
                    //段友秀
                    fragmentList.add(ConnotationsTabView.newInstance(TAB_SHOW));
//                    fragmentList.add(ConnotationsTabView.newInstance("http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-102&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=北京市&am_loc_time=1489226058493&count=30&min_time=1489205901&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120"));
                    break;
            }
        }
    }
}
