package com.spring.mvc.annotation;

import java.lang.annotation.*;

/**
 * 自定义Controller注解
 */
// 定义在类和方法上
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String value() default "";

}
