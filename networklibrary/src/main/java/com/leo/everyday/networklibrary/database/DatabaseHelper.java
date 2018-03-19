package com.leo.everyday.networklibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：Leo on 2018/3/15 16:22
 * <p>
 * 描述：数据库打开帮助类
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "leo_network_cache";
    private static final int DB_VERSION = 1;
    private static DatabaseHelper instance = null;
    public static SQLiteDatabase db = null;

    private static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        }
        return instance;
    }

    public static synchronized SQLiteDatabase openDatabase(Context context) {
        if (db == null) {
            db = getInstance(context).getReadableDatabase();
        }
        return db;
    }

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NetCacheTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
