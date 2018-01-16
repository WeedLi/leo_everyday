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

/**
 * 作者：Leo on 2018/1/15 11:06
 * <p>
 * 描述：新闻分类页
 */

public class NewsTablayout extends Fragment {

    private static NewsTablayout instance = null;
    private ViewPager viewPager;

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
}
