package com.leo.everyday.networklibrary.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.leo.everyday.networklibrary.utils.ObjectUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 作者：Leo on 2018/3/15 16:32
 * <p>
 * 描述：
 */

public class NetCacheDao {

    private SQLiteDatabase db;

    public NetCacheDao() {
        this.db = DatabaseHelper.db;
    }


    /**
     * 添加或更新缓存
     *
     * @return
     */
    public boolean saveData(String cacheKey, String saveTime, Object obj) throws IOException {
        if (isExist(cacheKey)) {
            return update(cacheKey, saveTime, obj);
        } else
            return add(cacheKey, saveTime, obj);
    }

    /**
     * 添加缓存
     *
     * @param cacheKey
     * @param saveTime
     * @param obj
     * @return
     * @throws IOException
     */
    public boolean add(String cacheKey, String saveTime, Object obj) throws IOException {
        ContentValues values = new ContentValues();
        values.put(NetCacheTable.CAHCE_KEY, cacheKey);
        values.put(NetCacheTable.SAVE_TIME, saveTime);
        values.put(NetCacheTable.CACHE_CONTENT, ObjectUtil.serializeObj(obj));
        long result = db.insert(NetCacheTable.TABLENAME, null, values);
        return result != -1;
    }


    /**
     * 缓存更新
     *
     * @return
     */
    public boolean update(String cacheKey, String saveTime, Object obj) throws IOException {
        ContentValues values = new ContentValues();
        values.put(NetCacheTable.SAVE_TIME, saveTime);
        values.put(NetCacheTable.CACHE_CONTENT, ObjectUtil.serializeObj(obj));
        String whereClause = NetCacheTable.CAHCE_KEY + "=?";
        String[] whereArgs = new String[]{cacheKey};
        int update = db.update(NetCacheTable.TABLENAME, values, whereClause, whereArgs);
        return update != 0;
    }


    /**
     * 查询缓存是否存在
     *
     * @return
     */
    public boolean isExist(String cacheKey) {
        String sql = "select * from " + NetCacheTable.TABLENAME + " where " + NetCacheTable.CAHCE_KEY + "=? ";

        if (TextUtils.isEmpty(cacheKey)) {
            return false;
        }
        Cursor cursor = db.rawQuery(sql, new String[]{cacheKey});
        int count = 0;
        if (cursor != null) {
            if (cursor.moveToNext()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        return count > 0;
    }


    public Object queryCacheContent(String cacheKey) throws Exception {
        Cursor cursor = db.query(NetCacheTable.TABLENAME, null, NetCacheTable.CAHCE_KEY + "=?", new String[]{cacheKey}, null, null, null);
        byte[] bytes = new byte[0];
        while (cursor.moveToNext()) {
            bytes = cursor.getBlob(NetCacheTable.ID_CACHE_CONTENT);
        }
        cursor.close();
        return ObjectUtil.deserializeObj(bytes);
    }

    public String querySaveTime(String cacheKey) {
        Cursor cursor = db.query(NetCacheTable.TABLENAME, null, NetCacheTable.CAHCE_KEY + "=?", new String[]{cacheKey}, null, null, null);
        String saveTime = null;
        while (cursor.moveToNext()) {
            saveTime = cursor.getString(NetCacheTable.ID_SAVE_TIME);
        }
        cursor.close();
        return saveTime;
    }


    public boolean removeItemCache(String cacheKey) {
        String whereClause = NetCacheTable.CAHCE_KEY + "=?";
        String[] whereArgs = new String[]{cacheKey};
        int result = db.delete(NetCacheTable.TABLENAME, whereClause, whereArgs);
        return result != -1;
    }

}
