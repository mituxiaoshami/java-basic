package com.spring.mvc.annotation;

import java.lang.annotation.*;

/**
 * 自定义Controller注解
 */
// 定义在字段上
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowrited {

    String value() default "";

}
