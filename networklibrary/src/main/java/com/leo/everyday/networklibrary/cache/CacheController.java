package com.leo.everyday.networklibrary.cache;

import android.content.Context;

import com.jakewharton.disklrucache.DiskLruCache;
import com.leo.everyday.networklibrary.database.NetCacheDao;
import com.leo.everyday.networklibrary.utils.CacheUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


/**
 * 作者：Leo on 2018/3/13 11:17
 * <p>
 * 描述：采用LRU算法的缓存 使用DisLruCache实现硬盘缓存 http://blog.csdn.net/guolin_blog/article/details/28863651
 */

public class CacheController {

    DiskLruCache mDiskLruCache;

    public CacheController(Context context) {
        try {
            File retorfitcache = CacheUtil.getDiskCacheDir(context, "retorfitcache");
            if (!retorfitcache.exists())
                retorfitcache.mkdirs();
            //最大缓存20M
            mDiskLruCache = DiskLruCache.open(retorfitcache, CacheUtil.getAppVersion(context), 1, 20 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储缓存
     *
     * @param key
     * @param value
     */
    public void saveCache(String key, String value) {
        DiskLruCache.Editor editor = null;
        try {
            String keyForDisk = CacheUtil.hashKeyForDisk(key);
            editor = mDiskLruCache.edit(keyForDisk);
            long currTime = System.currentTimeMillis() / 1000;
            OutputStream outputStream = editor.newOutputStream(0);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(String.valueOf(currTime));
            bufferedWriter.newLine();
            bufferedWriter.write(value);
            bufferedWriter.flush();
            editor.commit();
        } catch (IOException e) {
            if (editor != null) {
                try {
                    editor.abort();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                mDiskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getCache(String key, long maxCacheTime) {
        if (mDiskLruCache == null) {
            return null;
        }
        String keyForDisk = CacheUtil.hashKeyForDisk(key);
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(keyForDisk);
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                long cacheTime = Long.parseLong(bufferedReader.readLine());
                //判断缓存是否过期
                if (System.currentTimeMillis() / 1000 - cacheTime > maxCacheTime) {
                    //缓存已过期
                    mDiskLruCache.remove(keyForDisk);
                    return null;
                } else {
                    //缓存没过期
                    StringBuilder stringBuilder = new StringBuilder();
                    String strLine = null;
                    while ((strLine = bufferedReader.readLine()) != null) {
                        stringBuilder.append(strLine);
                        stringBuilder.append("\n");
                    }
                    // 删除最后的换行符
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    String result = stringBuilder.toString();
                    return result;
                }
            } else
                return null;
        } catch (Exception e) {
            try {
                // 异常时删除缓存
                mDiskLruCache.remove(keyForDisk);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 存储缓存
     *
     * @param key
     */
    public void saveCacheObj(String key, Object object) {
        try {
            new NetCacheDao().saveData(key, System.currentTimeMillis() + "", object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Object getCacheObj(String key, long maxCacheTime) {
        try {
            NetCacheDao netCacheDao = new NetCacheDao();
            long cacheTime = Long.parseLong(netCacheDao.querySaveTime(key));
            //判断缓存是否过期
            if (System.currentTimeMillis() / 1000 - cacheTime > maxCacheTime) {
                netCacheDao.removeItemCache(key);
                return null;
            }
            return netCacheDao.queryCacheContent(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
