package com.chiclaim.data.structure.linear;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 队列同样是一个被限制过的线性表（不支持扩容）
 * <p>
 * 队列只允许在队列的前端（head）删除元素，在队列的尾端（tail）插入元素
 * <p>
 * 顺序存储的队列 通过front和rear两个整型变量来分别记录队列front端的元素索引和rear端的元素索引
 * <p>
 * 简易顺序队列出现的"假满"：当往队列里插满元素后，删除所有元素，就会出现"假满"(head=tail)
 * <p>
 * 处理"假满"问题，主要有如下两种解决方案：
 * 1，每次将元素移除队列时都将队列中的所有元素向front端移动一位，这种方式下front值永远为0，有元素插入队列时rear+1，有元素移除队列时rear-1，
 * 但这种方式非常浪费时间，每次都将元素从队列移除都需要进行"整体搬家"
 * 2，将数组存储区看成一个首尾相接的环形区域，当存放数组的最大地址后，rear值再次变成0。采用这种技巧存储的队列称之为循环队列。
 * <p>
 * <p>
 * <p>
 * <p>
 * Created by Chiclaim on 2018/3/12.
 */
public class SequenceQueue<T> implements Iterable<T>, Queue<T> {

    private static final int DEFAULT_SIZE = 10;

    private int capacity;

    //记录队列前端元素的索引
    private int head;

    //记录队列尾端元素的索引
    private int tail;

    //用于存储队列里的元素
    private Object[] elementData;

    public SequenceQueue() {
        this.capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public SequenceQueue(T element) {
        this(element, DEFAULT_SIZE);
    }


    public SequenceQueue(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        tail++;
    }

    @Override
    public int size() {
        return tail - head;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public void enqueue(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (tail > capacity - 1) {
            throw new IndexOutOfBoundsException("队列已满：" + capacity);
        }
        elementData[tail++] = element;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        T removedElement = (T) elementData[head];
        elementData[head++] = null;
        return removedElement;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        return (T) elementData[head];
    }

    @Override
    public void clear() {
        Arrays.fill(elementData, null);
        head = tail = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = head; i < tail; i++) {
            builder.append(elementData[i]).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(']');
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        int index = head;

        @Override
        public boolean hasNext() {
            return index < tail;
        }

        @Override
        public T next() {
            return (T) elementData[index++];
        }

    }

    public static void main(String[] args) {
        SequenceQueue<String> queue = new SequenceQueue<>();
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


        System.out.println("queue.get：" + queue.getFront());

        queue.clear();

        System.out.println("clear后元素个数：" + queue.size());
    }


}
