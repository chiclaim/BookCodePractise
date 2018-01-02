package com.chiclaim.quality151;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock与synchronized是不一样的
 * Created by Chiclaim on 2018/1/2.
 */
public class Case127 {

    // 显示锁是同时运行的，很显然pool-1-thread-1线程执行到sleep时，其它两个线程也会运行到这里，一起等待，然后一起输出，这还具有线程互斥的概念吗？
    //而内部锁的输出则是我们预期的结果，pool-2-thread-1线程在运行时其它线程处于等待状态，pool-2-threda-1执行完毕后，
    // JVM从等待线程池中随机获的一个线程pool-2-thread-3执行，最后执行pool-2-thread-2，这正是我们希望的。

    //现在问题来了：Lock锁为什么不出现互斥情况呢？

    //示例中的代码，提交任务（es.submit）的时候，是每次都new一个任务，而Lock是一个全局变量，这样一个任务一个锁，显示锁是对象级别的锁，而内部锁是类级别的锁，也就说说Lock锁是跟随对象的，synchronized锁是跟随类的，所以没有起到互斥的作用。

    //我们也可以把内部锁改成使用this的方式，如 synchronized ("A")，这样synchronized也起不到互斥的作用

    //我们可以在提交任务的时候（es.submit(runnable)），提交相同的对象，这样就会公用一个锁(Lock)。

    //所以不管是Lock还是synchronized 的方式，能否互斥，取决于锁。


    public static void main(String args[]) throws Exception {
        // 运行显示任务
        runTasks(TaskWithLock.class);
        // 运行内部锁任务
        runTasks(TaskWithSync.class);
    }

    public static void runTasks(Class<? extends Runnable> clz) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        System.out.println("***开始执行 " + clz.getSimpleName() + " 任务***");
        // 启动3个线程
        Runnable runnable = clz.newInstance();
        for (int i = 0; i < 3; i++) {
            es.submit(runnable);
        }
        // 等待足够长的时间，然后关闭执行器
        TimeUnit.SECONDS.sleep(10);
        System.out.println("---" + clz.getSimpleName() + "  任务执行完毕---\n");
        // 关闭执行器
        es.shutdown();
    }

    static class Task {
        public void doSomething() {
            try {
                // 每个线程等待2秒钟，注意此时线程的状态转变为Warning状态
                Thread.sleep(2000);
            } catch (Exception e) {
                // 异常处理
                e.printStackTrace();
            }
            StringBuffer sb = new StringBuffer();
            // 线程名称
            sb.append("线程名称：" + Thread.currentThread().getName());
            // 运行时间戳
            sb.append(",执行时间： " + Calendar.getInstance().get(Calendar.SECOND) + "s");
            System.out.println(sb);
        }
    }

    //内部锁任务
    static class TaskWithSync extends Task implements Runnable {

        public TaskWithSync() {

        }

        @Override
        public void run() {
            //内部锁
            synchronized ("A") {
                doSomething();
            }
        }

    }

    //显示锁
    static class TaskWithLock extends Task implements Runnable {
        // 声明显示锁
        private final Lock lock = new ReentrantLock();

        public TaskWithLock() {
        }

        @Override
        public void run() {
            try {
                // 开始锁定
                lock.lock();
                doSomething();
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }
}
