package day06.main.second;

import day06.main.second.annotation.*;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:48 2017/12/19
 */
@TestTypeAnnotation(id = 10)
public class TestAnnotationClass {


    @TestFieldAnnotation(value = "成员属性注解")
    public String value;

    @TestConstructorAnnotation(value = "构造器注解")
    public TestAnnotationClass() {

    }

    @TestMethodAnnotation(description = "TestAnnotationClass.hasSuccess的描述信息")
    public void hasSuccess(@TestParameterAnnotation(value = "parameter info") String parameter) { }

}
