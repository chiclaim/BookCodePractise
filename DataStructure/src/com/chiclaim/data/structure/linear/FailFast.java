package com.chiclaim.data.structure.linear;

import java.util.Iterator;

/**
 * Created by Chiclaim on 2018/4/25.
 */
public class FailFast {


    public static void main(String[] args) {
//        testJdkArrayListFailFast();
//        iteratorRemove();
//        iteratorMultipleThread();
        fixIteratorMultipleThread();
    }


    private static void testJdkArrayListFailFast() {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (int i : list) {
            list.remove(i);
        }
    }

    private static void iteratorRemove() {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
            iterator.remove();
            System.out.println(i);
        }

        System.out.println("list size:" + list.size());
    }

    private static void iteratorMultipleThread() {
        //java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        java.util.Vector<Integer> list = new java.util.Vector<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator<Integer> iterator = list.iterator();
                while (iterator.hasNext()) {
                    System.out.println("迭代：" + iterator.next());
                    try {
                        Thread.sleep(10);//出让cpu资源
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator<Integer> iterator = list.iterator();
                while (iterator.hasNext()) {
                    int i = iterator.next();
                    if (i % 2 == 0) {
                        iterator.remove();
                        System.out.println("移除：" + i);
                    }

                }
            }
        }).start();

    }


    private static void fixIteratorMultipleThread() {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        String flag = "flag";

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (flag) {
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        System.out.println("迭代：" + iterator.next());
                        try {
                            Thread.sleep(10);//出让cpu资源
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (flag) {
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        int i = iterator.next();
                        if (i % 2 == 0) {
                            iterator.remove();
                            System.out.println("移除：" + i);
                        }

                    }
                }
            }
        }).start();

    }


}
