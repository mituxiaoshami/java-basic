package day06.main.first;

import java.lang.annotation.*;

/**
 * @Author: sea
 * @Description:
 * @Date: 15:29 2017/12/18
 */
@Documented
@Inherited
// JVM会读取注解，利用反射可以获得注解
@Retention(RetentionPolicy.RUNTIME)
// ElementType.TYPE 用于类，接口，枚举但不能是注解
@Target({ElementType.METHOD,ElementType.TYPE})
// @interface用来定义注解接口，接口中只能定义成员变量，且定义的成员变量必须以()结尾。
public @interface TestAnnotation {

    //定义成员变量
    //成员变量可以通过default指定默认值
    //如果成员变量不指定默认值的情况下
    //我们在引用接口时则必须给没有默认值的成员变量赋值
    String name();

    int age() default 18;
}
