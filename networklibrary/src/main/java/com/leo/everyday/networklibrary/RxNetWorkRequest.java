package com.leo.everyday.networklibrary;

import android.util.ArrayMap;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 作者：Leo on 2018/3/9 16:54
 * <p>
 * 描述：
 */

public class RxNetWorkRequest {

    private BaseApiService baseApiService;


    public RxNetWorkRequest(RetrofitClient client) {
        baseApiService = client.getApiService(BaseApiService.class);
    }

    @SuppressWarnings("unchecked")
    public Observable<String> get(String url, BaseRequest request) {
        return baseApiService.get(url, request.getHeaders(), request.getParams())
                .compose(new StringObservableTransformer());
    }

    @SuppressWarnings("unchecked")
    public Observable<String> post(String url, BaseRequest request) {
        return baseApiService.post(url, request.getHeaders(), request.getParams())
                .compose(new StringObservableTransformer());
    }

    @SuppressWarnings("unchecked")
    public <T> Observable<T> getT(String url, BaseRequest request, Class<T> clazz) {
        return baseApiService.get(url, request.getHeaders(), request.getParams())
                .compose(new TypeObservableTransformer(clazz));
    }

    @SuppressWarnings("unchecked")
    public <T> Observable<T> postT(String url, BaseRequest request, Class<T> clazz) {
        return baseApiService.post(url, request.getHeaders(), request.getParams())
                .compose(new TypeObservableTransformer(clazz));
    }


    /**
     * 单个或多个文件上传
     */
    public Observable<String> uploadFile(String url, BaseRequest request) {
        // header
        Map<String, String> headers = request.getHeaders();
        // 额外的参数
        Map<String, String> params = request.getParams();
        Map<String, RequestBody> requestBodyMap = new ArrayMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            MediaType textType = MediaType.parse("text/plain");
            RequestBody requestBody = RequestBody.create(textType, entry.getValue());
            requestBodyMap.put(entry.getKey(), requestBody);
        }
        // 文件
        Map<String, Object> uploads = request.getUploads();
        // 文件的类型
        MediaType mediaType = MediaType.parse(request.contentType);
        Iterator<Map.Entry<String, Object>> iterator = uploads.entrySet().iterator();
        if (!iterator.hasNext())
            throw new NullPointerException("not file!!!");
        Map.Entry<String, Object> next = iterator.next();
        String key = next.getKey();
        Object value = next.getValue();

        //判断是一个文件还是文件集合
        if (value instanceof File) {
            File file = (File) value;
            RequestBody requestBody = RequestBody.create(mediaType, file);
            // 这里的作用应该和@Part("key")是一样的
            // RequestBody只是封装了值，并没有封装key
            // key需要通过MultipartBody.Part来完成封装
            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(),
                    requestBody);
            return baseApiService.uploadFile(url, headers, requestBodyMap, part).map(new Function<ResponseBody, String>() {
                @Override
                public String apply(ResponseBody responseBody) throws Exception {
                    return responseBody.string();
                }
            });

        } else if (value instanceof List) {
            List<File> files = (List<File>) value;
            List<MultipartBody.Part> parts = new ArrayList<>();
            for (File file : files) {
                RequestBody requestBody = RequestBody.create(mediaType, file);
                MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
                parts.add(part);
            }
            return baseApiService.uploadFiles(url, headers, requestBodyMap, parts).map(new Function<ResponseBody, String>() {
                @Override
                public String apply(ResponseBody responseBody) throws Exception {
                    return responseBody.string();
                }
            });
        } else {
            throw new ClassCastException("support only File or List<File>");
        }
    }

}
