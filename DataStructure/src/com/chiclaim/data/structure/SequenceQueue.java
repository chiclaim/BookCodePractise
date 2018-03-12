package com.chiclaim.data.structure;

import java.util.Iterator;

/**
 * 队列同样是一个被限制过的线性表
 * <p>
 * 队列只允许在队列的前端（front）删除元素，在队列的尾端（rear）插入元素
 * <p>
 * 顺序存储的队列 通过front和rear两个整型变量来分别记录队列front端的元素索引和rear端的元素索引
 * <p>
 * Created by Chiclaim on 2018/3/12.
 */
public class SequenceQueue<T> implements Iterable<T> {

    private static final int DEFAULT_SIZE = 10;

    private int capacity;

    //记录队列前端元素的索引
    private int front;

    //记录队列尾端元素的索引
    private int rear;

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
        rear++;
    }

    public int size() {
        return rear - front;
    }

    public boolean isEmpty() {
        return rear == front;
    }


    public void add(T element) {
        if (rear > capacity - 1) {
            throw new IndexOutOfBoundsException("队列已满：" + capacity);
        }
        elementData[rear++] = element;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        T removedElement = (T) elementData[front];
        elementData[front++] = null;
        return removedElement;
    }


    public T get() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        return (T) elementData[front];
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = front; i < rear; i++) {
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

    class MyIterator implements Iterator<T> {
        int index = front;

        @Override
        public boolean hasNext() {
            return index < rear;
        }

        @Override
        public T next() {
            return (T) elementData[index++];
        }

    }

    public static void main(String[] args) {
        SequenceQueue<String> queue = new SequenceQueue<>();
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
        System.out.println("元素大小：" + queue.size());


        System.out.println("queue.get：" + queue.get());
    }


}
