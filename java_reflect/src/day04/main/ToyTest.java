package day04.main;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:23 2017/12/14
 */
public class ToyTest {

    // 获取Class的一个引用，通过getName()获得它的名字,并用isInterface()方法判断它是不是一个接口
    static void printInfo (Class cc) {
        System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("day04.main.FancyToy");
        }catch (ClassNotFoundException e) { e.printStackTrace(); }
        printInfo(c);
        // Class.getInterfaces方法会返回Class对象的一个数组，用于表示包含Class对象内的接口
        Class[] faces = c.getInterfaces();
        for (int i = 0; i < faces.length; i++) {
            printInfo(faces[i]);
        }
        // Class.getSuperclass方法会直接返回该Class对象的直接基础类是什么。 这种做法会返回一个Class引用，可用它作进一步的查询。
        // 也就是说，在运行期的时候，完全有机会调查到对象的完整层次结构。
        Class cy = c.getSuperclass();
        Object object = null;
        try {
            // cy是Class对象的一个引用，在编译期间并不知道进一步的类型信息。一旦新建了一个实例后，可以得到Object引用，但是那个引用指向的是一个Toy对象
            // 如果要调用特定的方法，那么必须进行检查，之后强转。
            // 用newInstance必须有一个默认构建器。没有办法用newInstance()创建拥有非默认构造器的对象，所以在Java 1.0中可能存在一些限制。
            // 在Java 1.1 中提出的反射，允许动态地使用类里的任何构造器。
            object = cy.newInstance();

            // 总结使用RTTI语法:
            // RTTI虽然能帮我们在运行时调查对象的准确类型，但是调查的类必须是在编译器已知的 也就是说，编译器必须明确知道RTTI要处理的所有类
            // 例: Class.forName("FancyToy"); 中的"FancyToy"是已知的
        }catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        printInfo(object.getClass());
    }

}

interface HasBatteries {}
interface Waterproof {}
interface ShootsThings {}

class Toy {

    public Toy (){

    }

    public Toy(int i) {

    }
}

//
class FancyToy extends Toy implements HasBatteries, Waterproof, ShootsThings {

    public FancyToy() { super(1);}
}
