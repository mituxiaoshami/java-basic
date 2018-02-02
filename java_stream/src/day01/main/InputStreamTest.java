package day01.main;


import day01.util.InputStreamUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @Author: sea
 * @Description:
 * @Date: 14:05 2018/1/16
 */
public class InputStreamTest {

    public static void main(String[] args) throws  Exception{

        /** 使用try语句中的括号作用: jdk1.7 里面有  AutoCloseable 这么一个接口
         *  而实现这个接口的类 才能在try语句中的括号中声明，否则会提示编译错误
         *  作用: 当try语句执行完之后，会自动调用close方法，而不用在finally语句中执行close方法
         * */
        try (InputStream in = new ByteArrayInputStream(new byte[]{-10,2,3});
             ByteArrayOutputStream out = new ByteArrayOutputStream()){

            /** read(): 返回在流中读取的下一个字节
             *  注: 在声明的流 in中，一开始的字节是-10 而-10转化成二进制是 1111 0110 而二进制转化成int类型的值就是246 所以输出的是246
             *  为什么输出结果是246？
             *  在计算机语言中 为了区分正数还是负数 用二进制的首位来区分 1代表负数 0代表正数
             *  在java中 byte类型的数字区间是 【-128,127】
             *  而在流中返回的却是无符号类型的也就是都是正数，这也就是为什么read()方法的api说返回0-255范围内的一个数的原因
             *  一个字节对应8位，而8位的二进制 -10正好对应246 所以返回246
             *
             *  如果读取到字节末尾，那么返回-1
             * */
            /* 测试代码*/
            /*int result = in.read();
            System.out.println(result);*/

            /** read(byte b[]): 从流中读取字节数，并且存储到byte[]中。
             *
             *  返回真正读取的字节数(integer类型)
             *  如果要存储的数组的长度是0 那么返回0 否则就会有至少读取一个字节的企图
             *  如果达到流的末尾，返回-1 否则就会至少有一个字节被存储到数组中
             *
             *  这个方法和read(b,0,b.length) 是相同的效果
             * */
            /* 测试代码*/
            /*byte[]  byteResult = new byte[3];
            *//** 返回的是真正读取的字节数*//*
            int number = in.read(byteResult);
            System.out.println("number is "+number);
            System.out.println(Arrays.toString(byteResult));*/

            /** 业务场景: 如果流中的字节数太大，而无法一次性存储到byte数组中，那么此时应该如何处理
             *
             *  如果流中的字节数很大，我们不可能一次性存储到一个byte数组中，并且从byte数组中取得数据，进行相应业务。
             *  我们可以做一个缓冲区，每次从缓冲区中取数据，将流输入到缓冲区中，每次从流中截取缓冲区的最大值的数据，返回的是每次真正读取的字节数
             * */
            /* 测试代码*/
            /*byte[] buf = new byte[1]; //定义的缓冲区 为了测试 缓冲区的长度设为2
            int len; //真正读取的字节数
            // 调用read(byte[] buf) 方法: 先将流中的数据存储到缓存数组中，每次都读取缓存数组长度的字节长度，并且保存到一个地方(可以是流，也可以是一个List，一个数组),这里是流
            while ((len = in.read(buf)) > 0) {
                // 当已经读取到缓存数组中的最大长度时，保存到输出流out中 然后进行下次输出
                // 这里不用out.write(buf)的原因: 如果读取到字节流的末尾，而且这次读取的长度小于缓存数组的最大长度，
                // 但是由于buf的长度是固定的，所以剩下的长度会从这次真正读取的字节长度开始从上一次存储到缓存数组中开始读取字节，直到等于数组的长度，导致数据重复
                // 保存到其他地方时也是一样，要截取当次真正读取字节长度作为存储长度。
                out.write(buf,0,len);
            }
            // 将输出流转化为byte数组
            byte[] outResult = out.toByteArray();
            System.out.println(Arrays.toString(outResult));*/

            /** 改善InputStream的read()中的可靠性问题*/
            /*byte[] bufResullt = new byte[3];
            InputStreamUtil.read(in, bufResullt,0 ,3,10);
            System.out.println(Arrays.toString(bufResullt));*/

            /**skip(long n)方法: 从输入流中跳过和丢弃n个字节的数据
             * 由于各种原因，skip方法结束时，跳过的字节数可能小于n，也可能为0 （其中一个情况: 在跳过n个字节之前，到达流的底部）
             * If n is negative, the method will try to skip backwards(API原文) : 如果输入的n是负数，则不跳过任何字节，并且返回0
             * 所以，如果skip方法的返回值小于跳过的字节数，则说明有异常发生，此时需要对异常进行特殊处理
             * (也就是说，需要比较skip的返回值和输入参数，如果两者不相等时，需做特殊处理)
             *
             *  返回: 实际跳过的字节数
             *
             *  skip()实现原理:
             *  通过观察skip()源码发现，skip方法内部有一个数组，它的最大长度是2048，skip的作用是向这个数组中不断输入字节，直到等于n，然后返回。
             *
             * */
            /*byte[] buf = new byte[1];
            // 返回实际跳过的字节数
            long skipNumber = in.skip(buf.length);
            System.out.println(skipNumber);*/

            /** 改善InputStream中的skip方法*/
            /*byte[] skipBuf = new byte[1];
            long skipNumber = InputStreamUtil.skip(in,skipBuf.length,10);
            System.out.println(skipNumber);*/

            /** available(): 返回预计可读取的字节数
             *  这个方法不阻塞输入流，也就是说，下一个调用的可能是同一个线程，也可能是另一个线程。一次读取或跳过此估计数个字节不会受阻塞，但读取或跳过的字节数可能小于该数。
             *
             *  Note that while some implementations of will return the total number of bytes in the stream, many will not.
             * It is never correct to use the return value of this method to allocate a buffer intended to hold all data in this stream.(API原文)
             * 什么意思? 就是说有一些实现available方法的子类会返回总的字节数，但有很多是不会的，这个方法从不来不用于用这个返回值的长度赋值给接收数组
             *
             * InputStream中的available方法总数返回0，具体的实现是靠子类
             *
             * 如果在调用这个方法的时候，流关闭了，则抛出IOException
             *
             * */
            /*int available = in.available();
            System.out.println(available);*/

            /** 标记inputStream的当前位置
             * mark就像书签一样， 用于标记，以后再调用reset时就可以再回到这个mark过的地方。
             * mark方法的参数: the maximum limit of bytes that can be read before the mark position becomes invalid.
             * 什么意思？ 最多读出这么多的字节后，这个mark保持有效。
             * 例: mark(1)：用read()读出1个字节后，reset()操作后可以重新读取已经读出的数据。
             * 如果已经读取的数据超过1个字节，那reset()操作后，就不能正确读取以前的数据了，因为此时mark标记已经失效。
             *
             * 如果流关闭了，此时对流进行标记操作，那么流不会又任何影响(Marking a closed stream should not have any effect on the stream. API原文)
             *
             * */
            /*in.mark(1);*/

            /** 将流重新定位到mark标记的位置
             *  如果流中没有标记或者读取的字节数已经超过了mark参数中的最大值 那么执行reset方法，可能会抛出异常
             *  inputStream 中的reset方法，除了抛出一个异常之外，什么事情都不做。
             * */
            /*in.reset();*/

            //测试mark()和reset()方法
            testMarkAndReset();

            System.out.println();


            /** markSupported()方法 表明这个流是否支持 mark() 和 reset() 方法
             *  支持返回true 不支持返回false
             *  InputStream 返回false
             * */
            /*boolean flag = in.markSupported();
            System.out.println(flag);*/

            /** 关闭输入流，并且关闭任何相关的系统资源*/
            in.close();

        }



    }

    private static void testMarkAndReset() throws IOException{

        String content = "MarkAndReset";
        InputStream in = new ByteArrayInputStream(content.getBytes());

        // 判断当前流是否支持mark和reset方法
        if (!in.markSupported()) {
            System.out.println("mark or reset not supported");
        }

        int ch;

        boolean mark = false;

        while ((ch = in.read()) != -1) {

            // 读取一个字节输出
            System.out.print((char) ch);
            // 读到 "d" 的时候标记
            if (((char) ch == 'd') && !mark) {
                in.mark(content.length());
                mark = true;
            }

            // 读到't'的时候重新回到标记的位置开始读取
            if ((char) ch == 't' && mark) {
                in.reset();
                mark = false;
            }
        }

    }
}
