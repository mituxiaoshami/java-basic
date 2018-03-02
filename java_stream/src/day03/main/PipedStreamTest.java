package day03.main;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;

/**
 * @Author: sea
 * @Description: PipedInputStream and PipedOutputStream
 * @Date: 13:46 2018/2/6
 */
public class PipedStreamTest {

    public static void main(String[] args) {

        Sender sender = new Sender();

        Receiver receiver = new Receiver();

        PipedOutputStream out = sender.getPipedOutputStream();

        PipedInputStream in = receiver.getPipedInputStream();

        try {
            /**
             * 第一种方式进行管道连接: 调用任意一个管道的connect()方法
             */
            /**
             * 对于同一个PipedInputStream对象和同一个PipedOutputStream对象，不能既调用PipedInpuSteam对象的connect方法又调用PipedOutputStream的connect方法
             * 一个PipedInputStream对象不能连接多个PipedOutputStream对象；一个PipedOutputStream对象也不能连接多个PipedInputStream对象。
             * 也即一个管道输入流只能对应一个管道输出流，只能一对一。
             *
             * 进行管道连接注意点:
             * 1、只能选择其中的一个而不能两个connect同时调用 这样会报 Already Connected异常
             */
            // 管道连接。下面两句话的本质相同
            out.connect(in);
           // in.connect(out);

            /**
             * 第二种方式进行管道连接: 通过构造函数
             */
            /*PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = null;
            try {
                outputStream = new PipedOutputStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }*/


            /**
             * Thread类的START方法：
             * 使该线程开始执行；Java 虚拟机调用该线程的 run 方法。
             * 结果是两个线程并发地运行；当前线程（从调用返回给 start 方法）和另一个线程（执行其 run 方法）。
             * 多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
             *
             * 正常使用PipedInputStream和PipedOutputStream
             */
            /*sender.start();
            receiver.start();*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 注意点: 在一个线程里使用PipedInpuStream和PipedOutputStream会造成死锁
         *
         * 例子:(都在主线程中使用管道输入输出流)
         */
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();
        try {
            // 进行管道连接
            inputStream.connect(outputStream);
            // 默认一次最多只能写入1024个字节
            byte[] data = new byte[1000];
            byte[] store = new byte[20];
            // 填充数组
            Arrays.fill(data, (byte) 1);
            System.out.println("first writing data");
            // 每次写1000字节数据
            outputStream.write(data, 0 , data.length);
            System.out.println("finish first writing");
            int count = 1;
            while (count < 100) {
                System.out.println(count+" times read data");
                inputStream.read(store, 0, store.length); //每次读20字节数据
                System.out.println(count+" times finish reading data");
                System.out.println((count+1)+" times write data");
                outputStream.write(data);//每次写1000字节数据
                System.out.println((count+1)+" times finish writing data");
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 从结果来看，当第二次尝试通过管道输出流outputStream写数据的时候发生阻塞，同时，也无法从管道输入流inputStream中读取数据。
        /**
         * 1、我们通过 outputStream.write(data, 0 , data.length);第一次向管道中写入1000个字节的数据
         * write源码:
         */
        /*public void write(byte b[], int off, int len) throws IOException {
        // 当试图通过输出流PipedOutputStream对象往”管道”写数据时，如果事先没有输入流PipedInputStream对象与该输出流建立连接（即sink==null），则报错。
            if (sink == null) {
                throw new IOException("Pipe not connected");
            } else if (b == null) {
                throw new NullPointerException();
            } else if ((off < 0) || (off > b.length) || (len < 0) ||
                    ((off + len) > b.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return;
            }
            // 管道输出流是通过与其建立了连接的管道输入流PipedInputStream对象来写入数据的。
            sink.receive(b, off, len);
        }*/

        /**
         * PipedInputStream中的receive源码:
         *
         * receive是一个同步方法，当在一个PipedInputStream对象上调用其receive方法时，该对象所在的线程必须先获得这个PipedInputStream对象的锁，才能进入该方法。
         * 首先执行管道的检测 checkStateForReceive();方法
         */
        /*synchronized void receive(byte b[], int off, int len)  throws IOException {
            checkStateForReceive();
            writeSide = Thread.currentThread();
            int bytesToTransfer = len;
            while (bytesToTransfer > 0) {
                if (in == out)
                    awaitSpace();
                int nextTransferAmount = 0;
                if (out < in) {
                    nextTransferAmount = buffer.length - in;
                } else if (in < out) {
                    if (in == -1) {
                        in = out = 0;
                        nextTransferAmount = buffer.length - in;
                    } else {
                        nextTransferAmount = out - in;
                    }
                }
                if (nextTransferAmount > bytesToTransfer)
                    nextTransferAmount = bytesToTransfer;
                assert(nextTransferAmount > 0);
                System.arraycopy(b, off, buffer, in, nextTransferAmount);
                bytesToTransfer -= nextTransferAmount;
                off += nextTransferAmount;
                in += nextTransferAmount;
                if (in >= buffer.length) {
                    in = 0;
                }
            }
        }*/

        /**
         *  检查管道状态
         */
        /*private void checkStateForReceive() throws IOException {
            if (!connected) {
                throw new IOException("Pipe not connected");
            } else if (closedByWriter || closedByReader) {
                throw new IOException("Pipe closed");
            } else if (readSide != null && !readSide.isAlive()) {
            // 管道输入流所在的线程不为空但是线程已死(之前的管道输入流所在的线程已死)，则会抛出异常。
                throw new IOException("Read end dead");
            }
        }*/

        /**
         *  最开始的时候buffer为1024，in为-1, out为0。
         *  第一次写入1000字节的时候能够全部成功写入，写入完成后，in为1000，out为0，receive方法释放锁。
         *  然后进入循环当中，进行第一次读取字节数据 inputStream.read(store, 0, store.length);
         *
         *  当第一次读取20字节的数据后，缓冲数组还剩余980字节数据，此时in为1000，out为20
         *
         *  在循环中，往里面写入1000字节数据，来到receive方法，由于不能一次性将1000字节写入循环缓冲数组buffer中，
         *  因此receive里的第一次循环写入了24字节的数据，此时in=1024, out=20
         *
         *  由于数组前面还有20个字节的剩余空空间，同时in!=out，且还有数据未写入，进入第二次循环写入20字节的数据，此时in==out=20，还有1000-24-20字节数据待写入
         *
         *  再次进入循环，由于in==out，进入awaitSpace()
         */
        /*private void awaitSpace() throws IOException {
            while (in == out) {
                checkStateForReceive();

            *//* full: kick any waiting readers *//*
                notifyAll();
                try {
                    wait(1000);
                } catch (InterruptedException ex) {
                    throw new java.io.InterruptedIOException();
                }
            }
        }*/

        /**
         * 管道输入流和输出流处于同一个线程，输出流一直处于while(in==out)循环中，而输入流因为得不到对象锁而无法读数据(由于输入流和输出流位于且只位于同一个线程，
         * 当调用notifyAll()的时候是肯定没有输入流wait在read()方法里)，当输出流wait(1000)之后in还是等于out，便进入了死循环，造成死锁
         */

    }

}
