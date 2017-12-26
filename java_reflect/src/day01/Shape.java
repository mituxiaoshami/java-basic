package day01;

/**
 * @Author: sea
 * @Description: 演示RTTI Shape是基类
 * @Date: 16:54 2017/11/28
 */

/* 基础类可以是一个接口、一个抽象类或者一个普通类
 * 由于Shape没有定义成员，而且并不在意我们创建了一个纯粹的Shape对象，所以最适合的方式就是接口
 * */
public interface Shape {

    //基类方法
    void draw();
}
