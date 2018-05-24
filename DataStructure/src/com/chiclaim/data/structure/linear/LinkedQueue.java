package com.chiclaim.data.structure.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 链式队列
 * Created by Chiclaim on 2018/3/13.
 */
public class LinkedQueue<T> implements Iterable<T> ,Queue<T>{


    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T element = current.element;
            current = current.next;
            return element;
        }
    }

    private class Node {

        private T element;
        private Node next;

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedQueue() {

    }

    public LinkedQueue(T element) {
        tail = head = new Node(element, null);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (head == null) {
            tail = head = new Node(element, null);
        } else {
            Node newNode = new Node(element, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    @Override
    public T dequeue() {
        Node oldFront = head;
        head = oldFront.next;
        oldFront.next = null;
        size--;
        return oldFront.element;
    }

    @Override
    public T getFront() {
        if(head==null)
            throw new NoSuchElementException();
        return head.element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        tail = head = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Node current = head; current != null; current = current.next) {
            builder.append(current.element).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i + "");
        }
        System.out.println(queue);

        System.out.println("删除元素：" + queue.dequeue());

        System.out.println("forEach遍历：");
        for (String str : queue) {
            System.out.print(str + ",");
        }
        System.out.println();
        System.out.println("元素个数：" + queue.size());


        queue.clear();

        System.out.println("clear后元素个数：" + queue.size());
    }


}
