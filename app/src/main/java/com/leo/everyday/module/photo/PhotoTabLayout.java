package com.leo.everyday.module.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leo.everyday.R;

/**
 * 作者：Leo on 2018/1/15 11:11
 * <p>
 * 描述：多媒体页面
 */

public class PhotoTabLayout extends Fragment {

    private static PhotoTabLayout instance;

    public static PhotoTabLayout getInstance() {
        if (instance == null) {
            instance = new PhotoTabLayout();
        }
        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_test, container, false);
        TextView textView = view.findViewById(R.id.textview);
        textView.setText("PHOTO");
        return view;
    }
}
