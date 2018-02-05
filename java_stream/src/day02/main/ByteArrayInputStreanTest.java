package day02.main;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @Author: sea
 * @Description:
 * @Date: 16:08 2018/2/2
 */
public class ByteArrayInputStreanTest {


    private static final int LEN = 5;

    // 对应英文字母(abcddefghijklmnopqrsttuvwxyz)
    private static final byte[] ArrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) throws IOException{
        String temp = new String(ArrayLetters);
        System.out.println("ArrayLetters=" + temp);

        testByteArrayInputStream();
    }

    private static void testByteArrayInputStream() throws IOException{

        // 创建ByteArrayInputStream字节流，内容是ArrayLetters数组
        ByteArrayInputStream in = new ByteArrayInputStream(ArrayLetters);

        // 读取流的下一个字节
        int temp;

        // 从字节流中读取5个字节
        for (int i = 0; i < LEN; i++) {
            if ((temp  = in.read()) > 0) {
                // 将Integer 类型的值转换成16进制 (返回String类型)
                System.out.printf("%d : 0x%s\n", i, Integer.toHexString(temp));
            }
        }
        // 若"该字节流不支持"标记功能，则直接退出
        if (!in.markSupported()) {
            System.out.println("mark not support");
            return;
        }

        // 标记“字节流中下一个被读取的位置”。即--标记“0x66”，因为因为前面已经读取了5个字节，所以下一个被读取的位置是第6个字节”
        // (01), ByteArrayInputStream类的mark(0)函数中的“参数0”是没有实际意义的。
        // (02), mark()与reset()是配套的，reset()会将“字节流中下一个被读取的位置”重置为“mark()中所保存的位置”
        in.mark(0);

        // 跳过5个字节，跳过5个字节后，字节流中下一个被读取的值应该是“0x6B”。
        in.skip(5);

        // 从字节流中读取5个数据。即读取“0x6B, 0x6C, 0x6D, 0x6E, 0x6F”
        byte[] buf = new byte[LEN];
        in.read(buf, 0, LEN);
        // 将buf转换为String字符串。“0x6B, 0x6C, 0x6D, 0x6E, 0x6F”对应字符是“klmno”
        String str1 = new String(buf);
        System.out.printf("str1=%s\n", str1);

        // 重置“字节流”：即，将“字节流中下一个被读取的位置”重置到“mark()所标记的位置”，即0x66。
        in.reset();
        // 从“重置后的字节流”中读取5个字节到buf中。即读取“0x66, 0x67, 0x68, 0x69, 0x6A”
        in.read(buf, 0, LEN);

        // 将buf转换为String字符串。“0x66, 0x67, 0x68, 0x69, 0x6A”对应字符是“fghij”
        String str2 = new String(buf);
        System.out.printf("str2=%s\n", str2);
    }

}
