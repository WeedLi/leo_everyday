package com.leo.everyday.bean.news;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Leo on 2016/10/19 10:19
 * <p>
 * 描述：新闻类的实体类
 */
public class NewsTabBean {

    public String type;//自行添加用来判断的

    public int error_code;
    public String reason;
    public ResultBean result;

    public class ResultBean {
        public String stat;
        public List<DataBean> data;

        public class DataBean implements Serializable {

            public String author_name;
            public String date;
            public String realtype;
            public String thumbnail_pic_s;
            public String thumbnail_pic_s02;
            public String thumbnail_pic_s03;
            public String title;
            public String type;
            public String uniquekey;
            public String url;


        }
    }


}
