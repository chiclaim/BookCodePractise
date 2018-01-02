package com.chiclaim.quality151;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 显示锁和内部锁还有什么区别
 * Created by Chiclaim on 2018/1/2.
 */
public class Case127_2 {

    //1，Lock支持更细精度的锁控制：假设读写锁分离，写操作时不允许有读写操作存在，而读操作时读写可以并发执行，这一点内部锁就很难实现。
    // 可重入的读写锁
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    // 读锁
    private final Lock r = rwl.readLock();
    // 写锁
    private final Lock w = rwl.writeLock();

    // 多操作，可并发执行
    public void read() {
        try {
            r.lock();
            Thread.sleep(1000);
            System.out.println("read......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
        }
    }

    // 写操作，同时只允许一个写操作
    public void write() {
        try {
            w.lock();
            Thread.sleep(1000);
            System.out.println("write.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }
    }

    //2.Lock锁是无阻塞锁，synchronized是阻塞锁
        //当线程A持有锁时，线程B也期望获得锁，此时，如果程序中使用的显示锁，则B线程为等待状态(在通常的描述中，也认为此线程被阻塞了)，若使用的是内部锁则为阻塞状态。
    //3.Lock可实现公平锁，synchronized只能是非公平锁
        //什么叫非公平锁呢？当一个线程A持有锁，而线程B、C处于阻塞(或等待)状态时，若线程A释放锁，JVM将从线程B、C中随机选择一个持有锁并使其获得执行权，这叫非公平锁(因为它抛弃了先来后到的顺序)；若JVM选择了等待时间最长的一个线程持有锁，则为公平锁(保证每个线程的等待时间均衡)。需要注意的是，即使是公平锁，JVM也无法准确做到" 公平 "，在程序中不能以此作为精确计算。
        //显示锁默认是非公平锁，但可以在构造函数中加入参数为true来声明出公平锁，而synchronized实现的是非公平锁，他不能实现公平锁。
    //4.Lock是代码级的，synchronized是JVM级的
        //Lock是通过编码实现的，synchronized是在运行期由JVM释放的，相对来说synchronized的优化可能性高，毕竟是在最核心的部分支持的，Lock的优化需要用户自行考虑。
        //显示锁和内部锁的功能各不相同，在性能上也稍有差别，但随着JDK的不断推进，相对来说，显示锁使用起来更加便利和强大，
        // 在实际开发中选择哪种类型的锁就需要根据实际情况考虑了：灵活、强大选择lock；快捷、安全选择synchronized.



}
