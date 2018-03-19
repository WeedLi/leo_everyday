package com.leo.everyday.networklibrary.annotation;


import com.leo.everyday.networklibrary.cache.CacheMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：Leo on 2018/3/12 10:39
 * <p>
 * 描述：缓存的注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cache {

    /**
     * 缓存的有效期。默认不过期 以秒为单位 1天：1*24*60*60
     */
    long maxAge() default Long.MAX_VALUE;

    /**
     * 每个不同功能的请求都要设置不一样的cacheKey，
     * 如果相同，会导致缓存数据发生覆盖或错乱。如果不指定cacheKey，默认是用url带参数的全路径名为cacheKey。
     *
     * @return
     */
    String cacheKey() default "";

    /**
     * 缓存的状态
     */
    CacheMode mode();


}
