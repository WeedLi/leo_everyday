package com.leo.everyday.networklibrary;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.leo.everyday.networklibrary.annotation.ContentType;
import com.leo.everyday.networklibrary.annotation.Header;
import com.leo.everyday.networklibrary.annotation.Ignore;
import com.leo.everyday.networklibrary.annotation.Upload;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 作者：Leo on 2018/3/12 10:42
 * <p>
 * 描述：
 */

public class BaseRequest {

    @Ignore
    private boolean isParsing;

    @Ignore
    private Map<String, String> params = new ArrayMap<>();

    @Ignore
    private Map<String, String> headers = new ArrayMap<>();

    @Ignore
    private Map<String, Object> uploads = new ArrayMap<>();

    @Ignore
    String contentType;


    /**
     * 获取请求参数
     */
    public Map<String, String> getParams() {
        parseAnnotation();
        return params;
    }

    /**
     * 获取请求Headers
     */
    public Map<String, String> getHeaders() {
        parseAnnotation();
        return headers;
    }

    /**
     * 获取文件上传的参数
     */
    public Map<String, Object> getUploads() {
        parseAnnotation();
        return uploads;
    }


    private void parseAnnotation() {
        if (isParsing)
            return;
        isParsing = true;
        parse(getClass());
    }

    /**
     * 解析注解，获取参数
     */
    private void parse(Class clazz) {

        for (Field field : clazz.getDeclaredFields()) {

            field.setAccessible(true);

            try {
                // 对不符合要求的字段进行过滤
                if (doFilter(field, this)) {
                    continue;
                }
                // 获取Field上的注解
                SerializedName nameTag = field.getAnnotation(SerializedName.class);
                Header headerTag = field.getAnnotation(Header.class);
                Upload uploadTag = field.getAnnotation(Upload.class);
                if (uploadTag != null) {
                    // 上传的单个文件
                    ContentType fileType = field.getAnnotation(ContentType.class);
                    if (fileType == null) {
                        throw new IllegalArgumentException(
                                "add annotation : ContentType for " + clazz.getCanonicalName()
                                        + "'s filed : " + field.getName() + " first!!!");
                    }
                    // 获取注解值
                    contentType = fileType.value();

                    if (TextUtils.isEmpty(uploadTag.value())) {
                        uploads.put(field.getName(), field.get(this));
                    } else {
                        uploads.put(uploadTag.value(), field.get(this));
                    }
                } else if (headerTag == null) {
                    // 接口参数
                    if (nameTag == null) {
                        // 没写 SerializedName 注解默认就是接口参数
                        params.put(field.getName(), String.valueOf(field.get(this)));
                    } else {
                        params.put(nameTag.value(), String.valueOf(field.get(this)));
                    }
                } else {
                    // 接口header
                    if (TextUtils.isEmpty(headerTag.value())) {
                        headers.put(field.getName(), String.valueOf(field.get(this)));
                    } else {
                        headers.put(headerTag.value(), String.valueOf(field.get(this)));
                    }
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // 获取父类上的请求
        // 排除Object类中的字段
        Class superClazz = clazz.getSuperclass();
        if (superClazz == null || superClazz.equals(Object.class)) {
            return;
        }

        parse(superClazz);
    }


    /**
     * 字段过滤
     */
    private boolean doFilter(Field field, Object obj) throws IllegalAccessException {
        Object value = field.get(obj);
        if (value == null) {
            // 过滤掉没有值的
            return true;
        } else if (field.getName().contains("serialVersionUID")) {
            // 过滤掉serialVersionUID
            return true;
        } else if (field.getAnnotation(Ignore.class) != null) {
            // 过滤掉有Filter注解的
            return true;
        } else if (field.getType() == String.class) {
            // 字符串不过滤
            return false;
        } else if (value instanceof Number) {
            // 数字Byte,Short,Integer,Long,Float,Double 不过滤
            return false;
        } else if (value instanceof Boolean) {
            // Boolean 不过滤
            return false;
        } else if (field.getAnnotation(Upload.class) != null) {
            // Upload注解不过滤
            return false;
        }

        // 其他的都过滤掉
        return true;
    }

}
