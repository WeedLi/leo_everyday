package com.leo.everyday;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.leo.everyday.module.news.NewsTablayout;
import com.leo.everyday.module.photo.PhotoTabLayout;
import com.leo.everyday.module.video.VideoTabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "select_item";
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;

    private NewsTablayout newsTablayout;
    private PhotoTabLayout photoTabLayout;
    private VideoTabLayout videoTabLayout;

    private Toolbar toolbar;
    private BottomNavigationView navigation;

    private int position;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    showFragment(FRAGMENT_NEWS);
                    return true;
                case R.id.navigation_photo:
                    showFragment(FRAGMENT_PHOTO);
                    return true;
                case R.id.navigation_video:
                    showFragment(FRAGMENT_VIDEO);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            newsTablayout = (NewsTablayout) getSupportFragmentManager().findFragmentByTag(NewsTablayout.class.getName());
            photoTabLayout = (PhotoTabLayout) getSupportFragmentManager().findFragmentByTag(PhotoTabLayout.class.getName());
            videoTabLayout = (VideoTabLayout) getSupportFragmentManager().findFragmentByTag(VideoTabLayout.class.getName());
            position = savedInstanceState.getInt(POSITION);
            showFragment(position);
            navigation.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_NEWS);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
        outState.putInt(SELECT_ITEM, navigation.getSelectedItemId());
    }

    private void showFragment(int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        position = index;
        switch (index) {
            case FRAGMENT_NEWS:
                toolbar.setTitle("新闻");
                if (newsTablayout == null) {
                    newsTablayout = NewsTablayout.getInstance();
                    fragmentTransaction.add(R.id.container, newsTablayout, NewsTablayout.class.getName());
                } else {
                    fragmentTransaction.show(newsTablayout);
                }
                break;
            case FRAGMENT_PHOTO:
                toolbar.setTitle("图片");
                if (photoTabLayout == null) {
                    photoTabLayout = PhotoTabLayout.getInstance();
                    fragmentTransaction.add(R.id.container, photoTabLayout, PhotoTabLayout.class.getName());
                } else {
                    fragmentTransaction.show(photoTabLayout);
                }
                break;
            case FRAGMENT_VIDEO:
                toolbar.setTitle("视频");
                if (videoTabLayout == null) {
                    videoTabLayout = VideoTabLayout.getInstance();
                    fragmentTransaction.add(R.id.container, videoTabLayout, VideoTabLayout.class.getName());
                } else {
                    fragmentTransaction.show(videoTabLayout);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (newsTablayout != null)
            fragmentTransaction.hide(newsTablayout);

        if (photoTabLayout != null)
            fragmentTransaction.hide(photoTabLayout);

        if (videoTabLayout != null)
            fragmentTransaction.hide(videoTabLayout);
    }


}
