package com.chiclaim.data.structure.linear;

import java.util.ArrayDeque;

/**
 * Created by Chiclaim on 2018/5/21.
 */
public class ArrayDequeTest {


    private static void testAsStack() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 17; i++) {
            deque.push(i);
        }
        deque.removeLast();
        while (!deque.isEmpty()) {
            System.out.println(deque.pop());
        }
    }

    private static void testAsQueue() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
    }

    public static void main(String[] args) {
        //as a stack
        System.out.println("as a stack");
        //testAsStack();

        //as a queue
        System.out.println("as a queue");
        //testAsQueue();

        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addFirst("First1");
        deque.addFirst("First2");
        deque.addFirst("First3");
        deque.addFirst("First4");
        deque.addLast("Last1");
        deque.addFirst("First5");
        System.out.println(deque);

    }


}
