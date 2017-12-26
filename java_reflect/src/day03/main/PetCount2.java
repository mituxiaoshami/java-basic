package day03.main;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @Author: sea
 * @Description:
 * @Date: 11:26 2017/12/11
 */
public class PetCount2 {

    public static void main(String[] args) {
        Vector pets = new Vector();
        // 在这里和PetCount的区别在与不用使用try结构语句包括起来，也就是说
        // 它会在编译期得到检查(在day02的SweepShop类中有说明，使用.class产生Class对象的引用会在编译期得到检查)
        // 不会像Class.forName()那样"掷"出任何违例
        Class[] petTypes = {
             Pet.class,
             Dog.class,
             Pug.class,
             Cat.class,
             Rodent.class,
             Gerbil.class,
             Hamster.class
            };
        // 将petTypes中的元素添加到pets列表中
        try {
            for (int i = 0; i < petTypes.length; i++) {
                 int random = 1 + (int)(Math.random()*(petTypes.length-1));
                 pets.addElement(petTypes[random].newInstance());
            }
        }catch (InstantiationException | IllegalAccessException e) { e.printStackTrace();}

        Hashtable table = new Hashtable();
        for (int i = 0; i < petTypes.length; i++) {
            // 这里和PetCount的区别在与这里typenames(类型名)数组已经被删除，改为从Class对象中获取
            table.put(petTypes[i].toString(),new Counter());
        }
        for (int i = 0; i < pets.size(); i++) {
            Object object = pets.elementAt(i);
            // Java 1.1 为Class类添加了isInstance方法。利用它可以动态调用instanceof运算符。目前只能静态的去调用。所以在PetCount3类中会优化
            if (object instanceof Pet) {
                // 因为当初put到table中的key是从Class对象中获取的，所以当取的时候 要包括上包名
                ((Counter)table.get("class day03.main.Pet")).i++;
            }
            if (object instanceof Dog) {
                ((Counter)table.get("class day03.main.Dog")).i++;
            }
            if (object instanceof Pug) {
                ((Counter)table.get("class day03.main.Pug")).i++;
            }
            if (object instanceof Cat) {
                ((Counter)table.get("class day03.main.Cat")).i++;
            }
            if (object instanceof Rodent) {
                ((Counter)table.get("class day03.main.Rodent")).i++;
            }
            if (object instanceof Gerbil) {
                ((Counter)table.get("class day03.main.Gerbil")).i++;
            }
            if (object instanceof Hamster) {
                ((Counter)table.get("class day03.main.Hamster")).i++;
            }
        }
        for (int i = 0; i < pets.size(); i++) {
            System.out.println(pets.elementAt(i).getClass().toString());
        }
        // Enumeration（枚举）接口的作用和Iterator类似，只提供了遍历Vector和HashTable类型集合元素的功能，不支持元素的移除操作。
        Enumeration keys = table.keys();
        while (keys.hasMoreElements()) {
            String num = (String) keys.nextElement();
            Counter counter = (Counter) table.get(num);
            System.out.println(num.substring(num.lastIndexOf(',') + 1) + " quantity: "+ counter.i);
        }
    }

}
