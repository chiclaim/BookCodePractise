package com.chiclaim.data.structure.linear;

import java.util.Arrays;

/**
 * 循环队列、双端队列，使用&位操作实现环形，参考java.util.ArrayDeque类
 * <p>
 * 双端队列代表一种特殊的队列，它可以在两端同时插入、删除操作。
 * <p>
 * 由于他可以从两端分别进行插入、删除操作，如果程序所有的插入、删除操作固定在一段进行，那么这个双端队列就是一个栈。
 * 所以双端队列既可以作为队列，也可以当做栈使用
 * <p>
 * 在Java中提供了Deque双端队列接口，LinkedList、ArrayDeque等都实现了该接口。
 * <p>
 * 如果ArrayDeque当做栈使用比Stack更快；
 * 如果ArrayDeque当做队列使用比LinkedList要快
 * 所以在Java中如果要使用栈或者队列，建议使用ArrayDeque。ArrayDeque是非线程安全的。
 * ArrayDeque常用方法：
 * <p>
 * 1，pop和poll区别，都是获取并移除队列的头部元素，但是元素如果为null，pop会抛出异常
 * 2，add和offer 都是往双端队列的的尾部添加指定元素
 * ...
 * <p>
 * <p>
 * <p>
 * Created by Chiclaim on 2018/3/13.
 */
public class LoopSequenceDeque<T> implements Queue<T> {

    private static final int DEFAULT_SIZE = 16;

    private Object[] elementData;

    private int head, tail;

    public LoopSequenceDeque() {
        elementData = new Object[DEFAULT_SIZE];
    }


    @Override
    public void enqueue(T element) {
        addLast(element);
    }

    /**
     * Inserts the specified element at the head of this deque.
     * 往队列的头部(head)添加元素
     *
     * @param element
     */
    public void addFirst(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        elementData[head = (head - 1) & (elementData.length - 1)] = element;
    }

    /**
     * Inserts the specified element at the end of this deque.
     * 往队列的尾部添加元素
     *
     * @param element
     */
    public void addLast(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        elementData[tail] = element;
        tail = (tail + 1) & (elementData.length - 1);
    }


    /**
     * 返回队列头部(head)元素
     *
     * @return
     */
    public T peek() {
        return peekFirst();
    }

    /**
     * 返回队列头部(head)元素
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public T peekFirst() {
        return (T) elementData[head];
    }

    @SuppressWarnings("unchecked")
    public T peekLast() {
        return (T) elementData[(tail - 1) & (elementData.length - 1)];
    }

    public T pop() {
        return removeFirst();
    }

    /**
     * 移除双端队列的头部元素
     *
     * @return
     */
    public T dequeue() {
        return removeFirst();
    }

    @SuppressWarnings("unchecked")
    public T removeFirst() {
        int h = head;
        T result = (T) elementData[h];
        // Element is null if deque empty
        if (result == null)
            return null;
        elementData[h] = null;
        head = (h + 1) & (elementData.length - 1);
        return result;
    }

    @SuppressWarnings("unchecked")
    public T removeLast() {
        int t = (tail - 1) & (elementData.length - 1);
        T result = (T) elementData[t];
        if (result == null)
            return null;
        elementData[t] = null;
        tail = t;
        return result;
    }

    @Override
    public int size() {
        return (tail - head) & (elementData.length - 1);
    }

    @Override
    public void clear() {
        tail = head = 0;
        Arrays.fill(elementData, null);
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public T getFront() {
        return peekFirst();
    }


    public static void main(String[] args) {
//        ArrayDeque deque = new ArrayDeque();
        //处理head和tail的时候通过&符号，数组大小size为2的n次幂
        LoopSequenceDeque deque = new LoopSequenceDeque();
        deque.addLast("L"); //index = 0 开始
        deque.addFirst("1");//index = size-1 开始
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addFirst("5");
        deque.addFirst("6");
        deque.addFirst("7");
        deque.addFirst("8");
        deque.addFirst("9");


        System.out.println("size:" + deque.size());

        System.out.println("peekLast:" + deque.peekLast());
        System.out.println("peekFirst:" + deque.peekFirst());

        System.out.println("pop:" + deque.pop());

        System.out.println("size:" + deque.size());

        System.out.println("removeFirst:" + deque.removeFirst());
        System.out.println("removeLast:" + deque.removeLast());

        System.out.println("size:" + deque.size());
    }

}
