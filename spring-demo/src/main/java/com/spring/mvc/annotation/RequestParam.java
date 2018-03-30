package com.spring.mvc.annotation;

import java.lang.annotation.*;

/**
 * 自定义Controller注解
 */
// 定义在参数上
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {

    String value() default "";

}
