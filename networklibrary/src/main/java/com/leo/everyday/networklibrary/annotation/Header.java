package com.leo.everyday.networklibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：Leo on 2018/3/12 10:35
 * <p>
 * 描述：请求的Header
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Header {

    /**
     * 参数的名称
     */
    String value() default "";

}
