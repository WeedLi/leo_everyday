package com.leo.everyday.bean;

/**
 * 作者：Leo on 2018/2/27 10:26
 * <p>
 * 描述：
 */

public class PagerEmptyBean {

    private static PagerEmptyBean _instance;

    private PagerEmptyBean() {

    }

    public static PagerEmptyBean getInstance() {
        if (_instance == null)
            synchronized (PagerEmptyBean.class) {
                if (_instance == null) {
                    _instance = new PagerEmptyBean();
                }
            }
        return _instance;
    }

}
