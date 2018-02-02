package day01.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: sea
 * @Description: 改善InputStream自带方法的不可靠缺陷
 * @Date: 13:51 2018/1/20
 */
public class InputStreamUtil {

    /**
     * 改善InputStream自带read方法不可靠的问题
     * @param in 要读取的流
     * @param buf 接收数组
     * @param off 从数组的哪个位置
     * @param len 读的长度
     * @param maxCount 可靠系数(返回)
     * @return
     * @throws IOException
     */
    public static int read(InputStream in, byte[] buf, int off, int len, int maxCount) throws IOException{

        /** 这段和jdk中InputStream read(byte b[], int off, int len) 处理方式相同 */
        // 如果要接收的数组是null(也就是没有初始化)
        if (buf == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > buf.length - off) { //如果数组的位置或者读的长度或者读的长度大于(接收数组的长度-数组的位置）
            throw new IndexOutOfBoundsException();
        } else if (len == 0) { // 如果要读取的长度为0
            return 0;
        }

        /** 这段和jdk中InputStream的方法相同*/
        int c = -1;
        try {
            // 读取流中的下一个字节
            c = in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (c == -1) { // 如果读到流的末尾，返回-1
            return -1;
        }
        // 将刚刚读取到的下一个字节赋值给数组中的起始位置
        buf[off] = (byte) c;

        if (maxCount <= 0) {
            maxCount = 1;
        }

        int i = 1;
        int count = 1;
        while (i < len && count <= maxCount) {
            // 如果读取的次数小于可靠系数，则继续读取
            // 如果产生异常，那么读取次数+1，这里和jdk中的源码有差别
            // jdk中，read(byte b[], int off, int len)方法，当出现一次IO异常时，则该次读取结束，这样写提高了性能的同时，其可靠性打了折扣
            // 这里通过设置可靠系数，以牺牲发生IO异常时的性能来提高其可靠性。
            try {
                c = in.read();
                if (c == -1) {
                    break;
                }
                buf[off + i] = (byte) c;
                i++;
            } catch (IOException e) {
                count++;
            }
        }

        if (count > maxCount && maxCount != 1) {
            throw new IOException();
        }

        return i;

    }

    /**
     * 改善InputStream中的skip方法不可靠的问题(避免产生IO异常时，就直接抛出异常)
     * @param in 输入流
     * @param n 要跳过的字节数
     * @param maxCount 可靠系数
     * @return
     * @throws IOException
     */
    public static long skip(InputStream in, long n, int maxCount) throws IOException {

        long remaining = n;
        int nr = 0;

        //如果要跳过的字节数小于等于0 则直接返回
        if (n <= 0) {
            return 0;
        }

        int count = 1;
        if (maxCount <= 0) {
            maxCount = 1;
        }

        int size = (int) remaining;
        byte[] skipBuffer = new byte[size];
        // 如果跳过的字节数大于0 并且跳过的次数小于可靠次数
        while (remaining > 0 && count <= maxCount) {

            // 在jdk中，当产生IO异常时，抛出异常，不继续输入，这样导致可靠性不高
            // 这里改善，牺牲性能，增加可靠性
            try {
                nr = in.read(skipBuffer, nr, (int) Math.min(size, remaining));
            } catch (IOException e) {
                count++;
            }
            // 如果读取到流的末尾，则跳出循环
            if (nr < 0) {
                break;
            }

            remaining -= nr;
        }

        if (count > maxCount && maxCount !=1) {
            throw new IOException();
        }

        // 返回跳过的字节数
        return n-remaining;

    }


}
