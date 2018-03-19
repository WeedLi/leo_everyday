package com.leo.everyday.request;

import com.leo.everyday.networklibrary.BaseRequest;
import com.leo.everyday.networklibrary.annotation.Cache;
import com.leo.everyday.networklibrary.cache.CacheMode;

/**
 * 作者：Leo on 2018/3/14 12:16
 * <p>
 * 描述：
 */

@Cache(cacheKey = "neihanneihan", mode = CacheMode.REQUEST_FAILED_READ_CACHE, maxAge = 30 * 24 * 60 * 60)
public class ConnotationRequest extends BaseRequest {

}
