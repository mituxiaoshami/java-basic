package day01.main;

import day01.Shape;
import day01.impl.Circle;
import day01.impl.Triangle;
import day01.impl.Square;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Author: sea
 * @Description: 主类
 * @Date: 17:02 2017/11/28
 */
public class Shapes {

    public static void main(String[] args) {

        Vector vector = new Vector();
        //在向vector添加元素的过程中，其实都都存入到一个 Object[] 数组中(具体看源码) 也就是说 对Vector来说，无论传进去的具体对象是什么类型 它们只是Object
        vector.addElement(new Circle());
        vector.addElement(new Square());
        vector.addElement(new Triangle());

        Enumeration enumeration = vector.elements();

        while (enumeration.hasMoreElements()) {
            // 由于vector中 容纳的是Object 所以nextElement()方法自然地产生一个指向Object对象的引用  但我们知道它实际上都是Shape类型的一个引用，并且希望调用Shape中的基类方法
            // 所以需要使用强转的方式转成Shape类型，而这个就是RTTI最基本的形式,因为在Java中，所有的强转都会在运行期间得到检查，确保其正确性。
            // RTTI的意义：在运行期间，对象的类型会得到鉴定，对象的类型确定了，那么所在类和那个对象的信息也就可以得到了
            ((Shape)enumeration.nextElement()).draw();
            // 但是这种情况下，RTTI造型只是实现了一部分 Object强转成Shape，而不是强转成Circle、Square、Triangle
            // 因为我们在编译的时候不确定它到底调的是哪个具体的类中的draw()方法，我们只有在运行的时候才能得出它具体调用的是哪个实现类中的draw()方法。
            // 所以在编译的时候，需要我们强转成基类的类型，在运行的时候，才能根据具体的类信息，去调用相应的方法。
            // 面向对象其中一项常规指标就是自己的代码尽可能少知道一些与对象的具体类型有关的情况，只需要将注意力放在基类中，这样代码才更容易理解和修改和扩展
        }

    }
}
