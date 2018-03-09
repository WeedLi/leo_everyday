package com.leo.everyday.module.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leo.everyday.R;
import com.leo.everyday.adapter.BasePagerAdapter;
import com.leo.everyday.module.news.tabs.NewsTabView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：Leo on 2018/1/15 11:06
 * <p>
 * 描述：新闻分类页
 */

public class NewsTablayout extends Fragment {

    private static NewsTablayout instance = null;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private Map<String, Fragment> map = new HashMap<>();

    public static NewsTablayout getInstance() {
        if (instance == null) {
            instance = new NewsTablayout();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_tablayout, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.news_view_pager);
        TabLayout tabLayout = view.findViewById(R.id.news_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ImageView add_channel_iv = view.findViewById(R.id.add_channel_iv);
        add_channel_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), NewsChannelActivity.class));
            }
        });
    }

    private void initData() {
        initTabs();
        BasePagerAdapter adapter = new BasePagerAdapter(getFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(titleList.size());
    }

    private void initTabs() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("头条");
        titleList.add("社会");
        titleList.add("国内");
        titleList.add("娱乐");
        titleList.add("体育");
        titleList.add("军事");
        titleList.add("科技");
        titleList.add("财经");
        titleList.add("时尚");

        List<String> channelIds = new ArrayList<>();
        channelIds.add("top");
        channelIds.add("shehui");
        channelIds.add("guonei");
        channelIds.add("guoji");
        channelIds.add("yule");
        channelIds.add("tiyu");
        channelIds.add("junshi");
        channelIds.add("keji");
        channelIds.add("caijing");
        channelIds.add("shishang");

        for (int i = 0; i < channelIds.size(); i++) {
            final String channelId = channelIds.get(i);
            if (map.containsKey(channelId)) {
                fragmentList.add(map.get(channelId));
            } else {
                Fragment fragment = NewsTabView.newInstance(channelId);
                fragmentList.add(fragment);
                map.put(channelId, fragment);
            }
        }
    }

}
