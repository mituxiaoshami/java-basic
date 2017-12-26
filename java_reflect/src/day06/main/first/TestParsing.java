package day06.main.first;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: sea
 * @Description: 解析注解
 * @Date: 16:00 2017/12/18
 */
public class TestParsing {

    public static void main(String[] args) {

        //解析注解
        //获取我们需要解析注解的类
        Class<Test> clz = Test.class;

        boolean clzHasAnnotion = clz.isAnnotationPresent(TestAnnotation.class);
        if (clzHasAnnotion) {

            //类存在我们定义的注解
            // 获取注解
            TestAnnotation annotation = clz.getAnnotation(TestAnnotation.class);
            //输出注解在类上的属性
            System.out.println("name = "+annotation.name()+"\t\tage = "+annotation.age());
        }

        //解析Method
        //两种解析方法上的注解方式
        //获得类中所有方法
        Method[] methods = clz.getMethods();

        for (Method method : methods) {

            //获得方法中是否有注解
            boolean methodHasAnnotation = method.isAnnotationPresent(TestAnnotation.class);

            if (methodHasAnnotation) {

                //注解存在
                //获取注解
                TestAnnotation methodAnnotation = method.getAnnotation(TestAnnotation.class);

                System.out.println("name = "+methodAnnotation.name()+"\tage = "+methodAnnotation.age());
            }
        }

        //第二种解析方式
        for (Method method : methods) {

            //获得方法上所有注解
            Annotation[] annotations = method.getAnnotations();

            for (Annotation annotation : annotations) {

                //如果是我们自定义的注解
                if (annotation instanceof TestAnnotation) {
                    //输出属性，需要强制转换类型
                    System.out.println("name = "+((TestAnnotation) annotation).name()+"\tage = "+ ((TestAnnotation) annotation).age());
                }
            }
        }
    }
}
