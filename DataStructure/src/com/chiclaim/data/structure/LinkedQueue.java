package com.chiclaim.data.structure;

import java.util.Iterator;

/**
 * 链式队列
 * Created by Chiclaim on 2018/3/13.
 */
public class LinkedQueue<T> implements Iterable<T> {


    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        Node current = front;

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

    //用于保存队列的头部节点
    private Node front;
    //用于保存队列的尾部节点
    private Node rear;
    //队列大小
    private int size;

    public LinkedQueue() {

    }

    public LinkedQueue(T element) {
        rear = front = new Node(element, null);
        size++;
    }

    public int size() {
        return size;
    }

    public void add(T element) {

        //判断是否是空队列
        if (front == null) {
            rear = front = new Node(element, null);
        } else {
            //创建新节点
            Node newNode = new Node(element, null);
            //让尾节点的next指向新节点
            rear.next = newNode;
            //把新节点作为尾节点
            rear = newNode;
        }
        size++;
    }

    public T remove() {
        Node oldFront = front;
        front = oldFront.next;
        oldFront.next = null;
        size--;
        return oldFront.element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        rear = front = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Node current = front; current != null; current = current.next) {
            builder.append(current.element).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(i + "");
        }
        System.out.println(queue);

        System.out.println("删除元素：" + queue.remove());

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
