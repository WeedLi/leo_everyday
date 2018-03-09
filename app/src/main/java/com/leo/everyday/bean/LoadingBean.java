package com.leo.everyday.bean;

/**
 * 作者：Leo on 2018/1/29 17:51
 * <p>
 * 描述：
 */

public class LoadingBean {

    private static LoadingBean _instance;

    private LoadingBean() {

    }

    public static LoadingBean getInstance() {
        if (_instance == null) {
            synchronized (LoadingBean.class) {
                if (_instance == null)
                    _instance = new LoadingBean();
            }
        }
        return _instance;
    }

}
