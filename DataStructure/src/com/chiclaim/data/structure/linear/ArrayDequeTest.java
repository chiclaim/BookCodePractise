package com.chiclaim.data.structure.linear;

import java.util.ArrayDeque;

/**
 * Created by Chiclaim on 2018/5/21.
 */
public class ArrayDequeTest {


    private static void testAsStack() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 17; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private static void testAsQueue() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            queue.addLast(i);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.removeFirst());
        }
    }


    private static void testModAndBitWise() {
        int count = Integer.MAX_VALUE;
        int result = 0;
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            result = i % 2;
        }
        long end = System.nanoTime();
        System.out.println(" % time:" + (end - start) / 1000000000.0 + " sec");

        long start2 = System.nanoTime();
        for (int i = 0; i < count; i++) {
            result = i & 2;
        }
        long end2 = System.nanoTime();
        System.out.println(" & time:" + (end2 - start2) / 1000000000.0 + " sec");
        System.out.println(result);

    }

    public static void main(String[] args) {
        //as a stack
        System.out.println("as a stack");
        //testAsStack();

        //as a queue
        System.out.println("as a queue");
        //testAsQueue();

        ArrayDeque<String> deque = new ArrayDeque<>();
//        deque.addFirst("First1");
//        deque.addFirst("First2");
//        deque.addFirst("First3");
//        deque.addFirst("First4");
//        deque.addLast("Last1");
//        deque.addFirst("First5");

//        deque.addLast("Last1");
//        deque.addLast("Last2");
//        deque.addLast("Last3");
//        deque.addLast("Last4");
//        deque.addFirst("First1");
//        deque.addFirst("First2");
//        deque.addLast("Last5");

        deque.addFirst("f1");
        deque.removeLast();


        System.out.println(deque);

        testModAndBitWise();

    }


}
