package day03.main;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * @Author: sea
 * @Description: 发送者线程
 * @Date: 14:08 2018/2/6
 */
public class Sender extends Thread{

    // 管道输出流对象
    // 它和"管道输入流(PipedInputStream)"对象绑定
    // 从而可以将数据发送给“管道输入流”的数据，然后用户可以从“管道输入流”读取数据。
    private PipedOutputStream out = new PipedOutputStream();

    // 获得"管道输出流"对象
    public PipedOutputStream getPipedOutputStream() {
        return out;
    }

    @Override
    public void run() {
        writeShortMessage();
        //writeLongMessage();
    }

    private void writeShortMessage() {
        String strInfo = "this is a short message";
        try {
            out.write(strInfo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLongMessage() {
        StringBuilder builder = new StringBuilder();
        // 通过for循环写入1020个字节
        for (int i = 0; i < 102; i++) {
            builder.append("0123456789");
        }
        // 再写入26个字节
        builder.append("abcdefghijklmnopqrstuvwxyz");
        // 此时总长度是1046个字节
        try {
            // 将1046个字节的数据写入到"管道输出流"中
            out.write(builder.toString().getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
