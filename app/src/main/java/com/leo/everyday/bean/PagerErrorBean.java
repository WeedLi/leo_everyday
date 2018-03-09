package com.leo.everyday.bean;

/**
 * 作者：Leo on 2018/2/27 10:26
 * <p>
 * 描述：
 */

public class PagerErrorBean {

    private static PagerErrorBean _instance;

    private PagerErrorBean() {

    }

    public static PagerErrorBean getInstance() {
        if (_instance == null)
            synchronized (PagerErrorBean.class) {
                if (_instance == null) {
                    _instance = new PagerErrorBean();
                }
            }
        return _instance;
    }

}
