package Test;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:51 2018/4/26
 */
public class StringTest {

    public static void main(String[] args) {

        java.lang.String s1 = "abc";
        java.lang.String s2 = "abc";

        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        
    }
}
