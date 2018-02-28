package day03.main;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: sea
 * @Description: volatile关键字保证不了原子性
 * @Date: 10:35 2018/2/27
 */
public class VolatileTest {

    public volatile int inc = 0;

    Lock lock = new ReentrantLock();

    public AtomicInteger atomicInteger = new AtomicInteger();

    public void increase() {
        inc++;
    }

    /**
     * 第一种方式保证原子性 synchronized:
     * @param
     */
    /*
    public synchronized void increase() {
        inc++;
    }
    */

    /**
     * 第二种方式保证原子性 Lock
     * @param
     */
    /*
    public void increase() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }
    */

    /**
     * 第三种方式保证原子性: AtomicInteger
     * @param
     */
    /*
    public  void increase() {
        atomicInteger.getAndIncrement();
    }
    */

    public static void main(String[] args) {

        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                }
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }

}
