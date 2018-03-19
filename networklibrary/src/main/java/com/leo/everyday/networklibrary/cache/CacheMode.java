package com.leo.everyday.networklibrary.cache;


/**
 * 缓存策略<br/>
 * <b>"缓存有效"："缓存存在且缓存未过期"</b><br/>
 * <b>"缓存无效"："缓存已过期或者缓存不存在"</b>
 *
 * @author leo
 */
public enum CacheMode {


//    NO_CACHE：不使用缓存，该模式下cacheKey、cacheTime 参数均无效
//    DEFAULT：按照HTTP协议的默认缓存规则，例如有304响应头时缓存。
//    REQUEST_FAILED_READ_CACHE：先请求网络，如果请求网络失败，则读取缓存，如果读取缓存失败，本次请求失败。
//    IF_NONE_CACHE_REQUEST：如果缓存不存在才请求网络，否则使用缓存。
//    FIRST_CACHE_THEN_REQUEST：先使用缓存，不管是否存在，仍然请求网络。

    //无缓存
    //优先读取缓存 先看有没有缓存 缓存有没有效 没有则做网络访问
    //先做网络访问 没有才查看缓存
    //先使用缓存 再去做网络访问


    /**
     * 不使用缓存，该模式下cacheKey、cacheTime 参数均无效
     */
    NO_CACHE,

    /**
     * 先请求网络，如果请求网络失败，则读取缓存，如果读取缓存失败，本次请求失败。
     */
    REQUEST_FAILED_READ_CACHE,

    /**
     * 如果缓存不存在才请求网络，否则使用缓存。
     */
    IF_NONE_CACHE_REQUEST,

    /**
     * 先使用缓存，不管是否存在，仍然请求网络。
     */
    FIRST_CACHE_THEN_REQUEST,

}
