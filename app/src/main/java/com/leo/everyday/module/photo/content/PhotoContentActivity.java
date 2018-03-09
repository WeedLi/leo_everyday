package com.leo.everyday.module.photo.content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.leo.everyday.R;
import com.leo.everyday.util.ImageLoader;
import com.leo.everyday.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class PhotoContentActivity extends AppCompatActivity {

    private PhotoView photoView;
    private MyViewPager viewPager;
    private PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return photoViews.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(photoViews.get(position));
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(photoViews.get(position));
            return photoViews.get(position);
        }
    };
    private List<View> photoViews;

    /**
     * @param context
     * @param url
     * @param urls
     * @param position      点击的位置
     * @param isSingleImage
     */
    public static void actionToPhotoContentActivity(Context context, String url, List<String> urls, List<String> isGifs, int position, boolean isSingleImage) {
        Intent intent = new Intent(context, PhotoContentActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("single", isSingleImage);
        intent.putExtra("position", position);
        intent.putStringArrayListExtra("isGifs", (ArrayList<String>) isGifs);
        intent.putStringArrayListExtra("urls", (ArrayList<String>) urls);
        context.startActivity(intent);
    }

    private void displaySingleImage() {
        photoView = findViewById(R.id.photo_view);
        photoView.setVisibility(View.VISIBLE);
        String url = getIntent().getStringExtra("url");
        ImageLoader.loadImage(photoView, url);
        setPhotoViewListener(photoView, url);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_photo_content);
        boolean isSingleImage = getIntent().getBooleanExtra("single", false);
        if (isSingleImage) {
            displaySingleImage();
        } else {
            viewPagerInit();
        }
    }

    private void viewPagerInit() {
        int position = getIntent().getIntExtra("position", 0);
        photoViews = new ArrayList();
        List<String> urls = getIntent().getStringArrayListExtra("urls");
        List<String> isGifs = getIntent().getStringArrayListExtra("isGifs");
        if (urls != null) {
            for (int i = 0; i < urls.size(); i++) {
                PhotoView photoView = new PhotoView(this);
                String url = urls.get(i);
                Log.e("gif", "gif：" + isGifs.get(i));
                ImageLoader.loadImage(photoView, url, isGifs.get(i));
                setPhotoViewListener(photoView, url);
                photoViews.add(photoView);
            }
            viewPager = findViewById(R.id.photo_view_pager);
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setCurrentItem(position, false);
            viewPager.setOffscreenPageLimit(photoViews.size());
        }
    }

    private void setPhotoViewListener(PhotoView photoView, String url) {
        PhotoViewAttacher p = new PhotoViewAttacher(photoView);
        p.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                PhotoContentActivity.this.finish();
            }
        });

        p.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //长按保存
                return true;
            }
        });
    }

}

