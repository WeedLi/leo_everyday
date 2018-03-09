package com.leo.everyday.bean.connotations;

import java.util.List;

/**
 * 作者：Leo on 2018/1/31 17:55
 * <p>
 * 描述：
 */

public class ConnotationsListBean {

    public DataBean data;

    public String message;// 成功是为 success

    public static class DataBean {
        public String tip;
        public String max_time;
        public String min_time;
        public boolean has_more;
        public boolean has_new_message;
        public List<ConnotationsItemBean> data;
    }

}
