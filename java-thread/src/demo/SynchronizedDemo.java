package demo;

/**
 * @Author: sea
 * @Description: synchronized demo
 * @Date: 15:28 2018/4/18
 */
public class SynchronizedDemo {

    /*
    *   synchronized(作用域) 作用域取决于对象的范围
    *   解决在多线程环境下的资源共享
    * */

    static Object lock = new Object();  // 静态对象 存在在方法区 (是一直存在的对象)

    //  对象锁  (在其他一个以上线程中执行该对象的同步方法会产生互斥)
    //  SynchronizedDemo sd1 = new SynchronizedDemo();
    //  sd1.test(); sd1.test(); (两个在不同的线程中，那么必须等待前一个释放对象锁 后面一个得到对象锁 )
    //  SynchronizedDemo sd2 = new SynchronizedDemo();
    //  sd2.test();

    // 第一种用法
    public synchronized void test() {

        // sd1.test();
        // sd2.test();
        // 最终是下面这种用法 (等价于下面这种用法)
        synchronized (this) {
            // 缩小锁的粒度
            // 缩小范围 把不安全的代码块尽可能的放到这里
        }

    }


    public static void main(String[] args) {

        // 对整个类加锁
        synchronized (SynchronizedDemo.class) {

        }

        // 第三种用法 静态对象里面加synchronized 表示全局锁 (类锁的一种)
        // 在调这个对象中的方法的时候 不管有多少线程同时访问 都会排队
        synchronized (lock) {

        }

    }


    // synchronized作用于静态方法和非静态方法的区别:
    /*
     *  非静态方法:
     *  给对象加锁(可以理解为给这个对象的内存上锁,注意 只是这块内存,其他同类对象都会有各自的内存锁),这时候
     *  在其他一个以上线程中执行该对象的这个同步方法(注意:是该对象)就会产生互斥

     *  静态方法:
     * 相当于在类上加锁(*.class 位于代码区,静态方法位于静态区域,这个类产生的对象公用这个静态方法,所以这块
     * 内存，N个对象来竞争), 这时候,只要是这个类产生的对象,在调用这个静态方法时都会产生互斥
     */
}
