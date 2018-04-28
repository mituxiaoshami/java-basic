package Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sea
 * @Description:
 * @Date: 17:20 2018/4/17
 */
public class Collection {

    public static void main(StringTest[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("key","1");
        map.put("key","2");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }


}
