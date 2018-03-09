package com.leo.everyday.widget.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 作者：Leo on 2018/1/31 12:22
 * <p>
 * 描述：
 */

public class VisibleBehavior extends CoordinatorLayout.Behavior<TextView> {

    boolean flag;

    public VisibleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Button;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        flag = !flag;
        Log.e("TAG", "来到这里啦");
        child.setVisibility(flag ? View.VISIBLE : View.GONE);

        return true;
    }
}
