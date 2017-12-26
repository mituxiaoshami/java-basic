package day02.main;

/**
 * @Author: sea
 * @Description:
 * @Date: 15:22 2017/11/29
 */
public class SweepShop {

    public static void main(String[] args) {
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");
        try {
            // 该方法是Class类的一个静态方法。任何Class对象能够通过这种方式获取Class对象的一个引用  而获取方式大致上有三种
            // 。
            Class<?> gum1 = Class.forName("day02.main.Gum");
            System.out.println(gum1.getName());
            // 使用.class来产生Class对象的引用 这种方式更加简单，更安全，因为它会在编译期间得到检查。 同时，由于取消了对方法的调用，所以执行效率也会高一点
            // 这种方式同时可以可以应用于接口、数组以及基本数据类型。
            // 针对每种基本数据类型的包装类，它还存在一个名为TYPE的字段，为相关的基本数据类型产生Class对象的一个句柄
            Class<?> num = Integer.TYPE;
            System.out.println(num);
            Class<?> gum2 = day02.main.Gum.class;

            // 根据打印结果会发现 全部返回true 从而得出结论：同时，针对于Class类，虚拟机只会产生一份字节码文件，然后使用这份字节码文件产生多个实例对象
            // 也就是说 只会产生一个Class对象。
            Gum gum3 = new Gum();
            Class<?> gum4 = gum3.getClass();

            System.out.println(gum1 == gum2);
            System.out.println(gum1 == gum4);

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("After Class.forName(\"day02.Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");

        // 从打印结果来看，每个Class只有在它需要的时候才会载入，而static初始化工作是在类载入的时候执行的 并且是在执行构造方法之前执行静态代码块
    }
}


/**
 * 对每个类来说(Candy，Gum和Cookie)，它们都有一个static从句，用于在类首次载入时执行。
 */

class Candy {

    static {
        System.out.println("Loading Candy");
    }

}

class Gum {

    static {
        System.out.println("Loading day02.Gum");
    }

}

class Cookie {

    static {
        System.out.println("Loading Cookie");
    }

}
