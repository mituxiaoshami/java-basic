package day06.main.second;

import day06.main.second.annotation.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author: sea
 * @Description: 系统的测试反射解析注解
 * @Date: 13:41 2017/12/19
 */
public class AnnotationTracker {

    public static void main(String[] args) {
        System.out.println("===========Type Annotation==========");
        AnnotationTracker.trackTypeAnnotation(TestAnnotationClass.class);

        System.out.println("===========Constructor Annotation========");
        AnnotationTracker.trackConstructorAnnotation(TestAnnotationClass.class);

        System.out.println("===========Method & Parameter Annotation=========");
        AnnotationTracker.trackMehtodAnnotation(TestAnnotationClass.class);

        System.out.println("===========Field Annotation=========");
        AnnotationTracker.trackFieldAnnotation(TestAnnotationClass.class);
    }

    public static void trackTypeAnnotation(Class<?> clz) {

        boolean hasPresent = clz.isAnnotationPresent(TestTypeAnnotation.class);
        if (hasPresent) {
            TestTypeAnnotation testTypeAnnotation = clz.getAnnotation(TestTypeAnnotation.class);
            System.out.println("class id: " + testTypeAnnotation.id());
        }
    }

    public static void trackConstructorAnnotation(Class<?> clz) {
        for (Constructor<?> constructor : clz.getConstructors()) {
            boolean hasPresent = constructor.isAnnotationPresent(TestConstructorAnnotation.class);
            if (hasPresent) {
                TestConstructorAnnotation annotation = constructor.getAnnotation(TestConstructorAnnotation.class);
                System.out.println("描述信息：" + annotation.value());
            }
        }
    }

    public static void trackMehtodAnnotation(Class<?> clz) {
        for (Method method : clz.getDeclaredMethods()) { //遍历有注解的方法
            boolean hasPresent = method.isAnnotationPresent(TestMethodAnnotation.class);
            if (hasPresent) {
                TestMethodAnnotation annotation = method.getAnnotation(TestMethodAnnotation.class);
                System.out.println("描述信息: " + annotation.description());
            }

            for (Parameter parameter : method.getParameters()) {
                boolean isPresent = parameter.isAnnotationPresent(TestParameterAnnotation.class);
                if (isPresent) {
                    TestParameterAnnotation annotation = parameter.getAnnotation(TestParameterAnnotation.class);
                    System.out.println("参数的注解信息: " + annotation.value());
                }
            }
        }
    }

    public static void trackFieldAnnotation(Class<?> clz) {
        for (Field field : clz.getDeclaredFields()) { //获取有注解的字段
            boolean hasPresent = field.isAnnotationPresent(TestFieldAnnotation.class);
            if (hasPresent) {
                TestFieldAnnotation annotation = field.getAnnotation(TestFieldAnnotation.class);
                System.out.println("字段注解的值: " + annotation.value());
            }
        }
    }

}
