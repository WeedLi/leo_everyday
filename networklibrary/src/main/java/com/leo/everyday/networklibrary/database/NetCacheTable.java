package com.leo.everyday.networklibrary.database;

/**
 * 作者：Leo on 2018/3/15 16:32
 * <p>
 * 描述：
 */

public class NetCacheTable {

    /**
     * 新闻频道信息表
     */
    public static final String TABLENAME = "NetCacheTable";

    /**
     * 字段部分
     */
    public static final String ID = "id";
    public static final String CAHCE_KEY = "cachekey";
    public static final String SAVE_TIME = "saveTime";
    public static final String CACHE_CONTENT = "cacheContent";

    /**
     * 字段ID 数据库操作建立字段对应关系 从0开始
     */
    public static final int ID_ID = 0;
    public static final int ID_CAHCE_KEY = 1;
    public static final int ID_SAVE_TIME = 2;
    public static final int ID_CACHE_CONTENT = 3;

    /**
     * 创建表
     */
    public static final String CREATE_TABLE = "create table if not exists " + TABLENAME + "(" +
            ID + " integer primary key autoincrement, " +
            CAHCE_KEY + " text, " +
            SAVE_TIME + " text, " +
            CACHE_CONTENT + " text) ";
}
