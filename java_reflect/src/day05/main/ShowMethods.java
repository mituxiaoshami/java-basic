package day05.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author: sea
 * @Description:
 * @Date: 15:54 2017/12/15
 */
public class ShowMethods {

    static final String usage = "usage: \n" + "ShowMethods qualified.class.name\n" +
            "To show all methods in class or: \n" + "ShowMethods qualified.class.name word\n" + "To search for methods involving 'word' ";

    public static void main(String[] args) {

        if (args.length <= 0) {
            System.out.println(usage);
            System.exit(0);
        }

        try {
            Class c = Class.forName(args[0]);
            // 返回Method对象的一个数组
            Method[] m = c.getMethods();
            Constructor[] constructors = c.getConstructors();
            if (args.length == 1) {
                for (int i = 0; i < m.length; i++) {
                    // 每个单独的Method提供了一系列的方法 可解析出它们所代表的名字、参数以及返回值。
                    // 这里只是生成一个含有完整方法签名的字符串
                    System.out.println(m[i].toString());
                }
                for (int i = 0; i < constructors.length; i++) {
                    System.out.println(constructors[i].toString());
                }
            }else {
                for (int i = 0; i < m.length; i++) {
                    // 判断特定的签名是否与我们的目标字符串相等
                    if (m[i].toString().indexOf(args[1]) != -1) {
                        System.out.println(m[i].toString());
                    }
                }
                for (int i = 0; i < constructors.length; i++) {
                    if (constructors[i].toString().indexOf(args[1]) != -1) {
                        System.out.println(constructors[i].toString());
                    }
                }
            }
        }catch (ClassNotFoundException e) {
            System.out.println("No such class: " + e);
        }

    }
}
