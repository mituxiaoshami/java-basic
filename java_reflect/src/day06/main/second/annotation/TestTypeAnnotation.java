package day06.main.second.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: sea
 * @Description: 测试类注解
 * @Date: 13:42 2017/12/19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestTypeAnnotation {

    int id() default 0;
}
