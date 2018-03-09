package com.leo.everyday.bean;

/**
 * 作者：Leo on 2018/2/27 10:26
 * <p>
 * 描述：
 */

public class PagerLoadingBean {

    private static PagerLoadingBean _instance;

    private PagerLoadingBean() {

    }

    public static PagerLoadingBean getInstance() {
        if (_instance == null)
            synchronized (PagerLoadingBean.class) {
                if (_instance == null) {
                    _instance = new PagerLoadingBean();
                }
            }
        return _instance;
    }

}
