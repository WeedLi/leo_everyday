package com.leo.everyday;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.leo.everyday.module.connotations.ConnotationsTabLayout;
import com.leo.everyday.module.news.NewsTablayout;
import com.leo.everyday.module.photo.PhotoTabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "select_item";
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;

    private NewsTablayout newsTablayout;
    private PhotoTabLayout photoTabLayout;
    private ConnotationsTabLayout connotationsTabLayout;

    private Toolbar toolbar;
    private BottomNavigationView navigation;
    private DrawerLayout drawerLayout;

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
        drawerLayout = findViewById(R.id.drawer_layout);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState != null) {
            newsTablayout = (NewsTablayout) getSupportFragmentManager().findFragmentByTag(NewsTablayout.class.getName());
            photoTabLayout = (PhotoTabLayout) getSupportFragmentManager().findFragmentByTag(PhotoTabLayout.class.getName());
            connotationsTabLayout = (ConnotationsTabLayout) getSupportFragmentManager().findFragmentByTag(ConnotationsTabLayout.class.getName());
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
                getSupportActionBar().setTitle(getResources().getString(R.string.title_news));
                if (newsTablayout == null) {
                    newsTablayout = NewsTablayout.getInstance();
                    fragmentTransaction.add(R.id.container, newsTablayout, NewsTablayout.class.getName());
                } else {
                    fragmentTransaction.show(newsTablayout);
                }
                break;
            case FRAGMENT_PHOTO:
                getSupportActionBar().setTitle(getResources().getString(R.string.title_photo));
                if (photoTabLayout == null) {
                    photoTabLayout = PhotoTabLayout.getInstance();
                    fragmentTransaction.add(R.id.container, photoTabLayout, PhotoTabLayout.class.getName());
                } else {
                    fragmentTransaction.show(photoTabLayout);
                }
                break;
            case FRAGMENT_VIDEO:
                getSupportActionBar().setTitle(getResources().getString(R.string.title_connotation));
                if (connotationsTabLayout == null) {
                    connotationsTabLayout = ConnotationsTabLayout.getInstance();
                    fragmentTransaction.add(R.id.container, connotationsTabLayout, ConnotationsTabLayout.class.getName());
                } else {
                    fragmentTransaction.show(connotationsTabLayout);
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

        if (connotationsTabLayout != null)
            fragmentTransaction.hide(connotationsTabLayout);
    }


    // 返回键的监听
    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }

}
