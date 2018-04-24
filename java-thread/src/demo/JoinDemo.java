package demo;

/**
 * @Author: sea
 * @Description: Thread join demo
 * @Date: 13:34 2018/4/18
 */
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException{

        Thread t1 = new Thread(() -> {
            System.out.println("thread----t1");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("thread----t2");
        });

        Thread t3 = new Thread(() -> {
            System.out.println("thread----t3");
        });

        // 发现三个线程的执行结果是未知的 因为start方法只是让线程从新建状态--->可运行状态  之后等待OS调用 具体先调用哪个线程是不确定的 所以是不可控的
        t1.start();
        // t1.join();

        t2.start();
        // t2.join();

        t3.start();
        // t3.join();

        // 在每个线程后面加上join方法保证线程的顺序执行 使得线程之间的并行执行变为串行执行
        // join 必须在start 方法调用之后调用才有意义。

        /*
        *  join 是一个同步方法 在这个同步方法中 他回去调用 wait(0)方法
        *  他会让当前的主线程等待 子线程继续执行 (程序在main线程中调用t1线程的join方法，则main线程放弃cpu的使用权，并返回t1线程继续执行，直到线程t1执行完毕)
        *  相当于在main线程中 同步t1线程 t1执行完了 main线程才有执行的机会
        *
        *  步骤:
        *  1、synchronized 锁关键字修饰 所以main线程必须先要获得锁  (当时synchronized锁定的是Thread这个对象)
      public final synchronized void join(long millis) throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            // 调用死循环 使得 main 线程一直处于阻塞(等待)状态 知道线程死掉
            while (isAlive()) {
            // 2、获得锁之后 调用wait(0)方法  也就是说main线程拿到Thread对象锁之后 main线程会阻塞 (A线程中调用了B线程的join方法，则相当于A线程调用了B线程的wait方法，在调用了B线程的wait方法之后，A线程进入阻塞状态)
            // 3、当Thread线程执行完，Thread线程会自动调用自身的notifyAll方法唤醒main线程 （jdk源码里面）

            // wait() 方法 解释:
            // 1、每一个对象除了有一个锁之外，还有一个等待队列(wait set)
            // 2、当一个对象刚创建的时候，它的等待队列是空的
            // 3、在某个线程获取对象的锁后(这里是main线程获取了Thread对象锁后)，在该对象锁(Thread对象锁)控制的同步代码块或者同步方法中，去调用该对象(Thread对象)的wait方法
                  将该线程(main线程)挂起放入该对象等待队列
            // 4、当调用该对象(Thread对象)的notify方法时，将从该对象的等待队列中随机唤醒一个线程，这个线程将再次成为可运行的线程。
                wait(0);
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }
        * */
    }
}
