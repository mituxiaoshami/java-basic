package com.spring.mvc.annotation;

import java.lang.annotation.*;

/**
 * 自定义Controller注解
 */
// 定义在类上
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {

    String value() default "";

}
