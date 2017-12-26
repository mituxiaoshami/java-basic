package day03.main;

import java.util.Hashtable;
import java.util.Vector;

/**
 * @Author: sea
 * @Description:
 * @Date: 11:26 2017/12/11
 */
public class PetCount {

    static String[] typenames = {
        "Pet","Dog","Pug","Cat","Rodent","Gerbil","Hamster"
    };

    public static void main(String[] args) {
        Vector pets = new Vector();
        try {
            Class[] petTypes = {
                Class.forName("day03.main.Pet"),
                Class.forName("day03.main.Dog"),
                Class.forName("day03.main.Pug"),
                Class.forName("day03.main.Rodent"),
                Class.forName("day03.main.Gerbil"),
                Class.forName("day03.main.Hamster"),
            };
            for (int i = 0; i < petTypes.length; i++) {
                pets.addElement(petTypes[(int)(Math.random()*petTypes.length)].newInstance());
            }
        }catch ( ClassNotFoundException | IllegalAccessException | InstantiationException e) {}

        Hashtable table = new Hashtable();
        for (int i = 0; i < typenames.length; i++) {
            table.put(typenames[i],new Counter());
        }
        for (int i = 0; i < pets.size(); i++) {
            Object object = pets.elementAt(i);
            // 在java 1.0中，对instanceof有一个比较小的限制：只可将其与一个已命名的类型比较，不能同Class对象作比较 也就是说，必须要在编译时期要知道比较的类型
            // 而且编写那么多instanceof表达式，设计可能有点问题 在之后会逐步优化
            if (object instanceof Pet) {
                ((Counter)table.get("Pet")).i++;
            }
            if (object instanceof Dog) {
                ((Counter)table.get("Dog")).i++;
            }
            if (object instanceof Pug) {
                ((Counter)table.get("Pug")).i++;
            }
            if (object instanceof Cat) {
                ((Counter)table.get("Cat")).i++;
            }
            if (object instanceof Rodent) {
                ((Counter)table.get("Rodent")).i++;
            }
            if (object instanceof Gerbil) {
                ((Counter)table.get("Gerbil")).i++;
            }
            if (object instanceof Hamster) {
                ((Counter)table.get("Hamster")).i++;
            }
        }
        for (int i = 0; i < pets.size(); i++) {
            System.out.println(pets.elementAt(i).getClass().toString());
        }
        for (int i = 0; i < typenames.length; i++) {
            System.out.println(typenames[i] + " quantity: " + ((Counter)table.get(typenames[i])).i);
        }
    }

}

class Counter {
    int i;
}

class Pet {}

class Dog extends Pet {}

class Pug extends Dog {}

class Cat extends Pet {}

class Rodent extends Pet {}

class Gerbil extends Rodent {}

class Hamster extends Rodent {}