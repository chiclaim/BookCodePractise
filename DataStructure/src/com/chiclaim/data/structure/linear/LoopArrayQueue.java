package com.chiclaim.data.structure.linear;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 循环队列，如果队列满了扩容2倍
 * Created by Chiclaim on 2018/5/24.
 */
public class LoopArrayQueue<T> implements Queue<T> {

    private int head, tail;
    private int size;
    private T[] data;

    public LoopArrayQueue() {
        this(16);
    }

    public LoopArrayQueue(int initialSize) {
        data = (T[]) new Object[initialSize];
    }


    @Override
    public void enqueue(T element) {
        data[tail] = element;
        tail = (tail + 1) % data.length;
        size++;
        if (tail == head) {
            doubleCapacity();
        }
    }

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        //把队列头部置为null
        data[head] = null;
        T v = data[head];
        head = (head + 1) % data.length;
        size--;
        return v;
    }

    private void doubleCapacity() {
        T[] newData = (T[]) new Object[data.length << 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(head + i) % data.length];
        }
        data = newData;
        //重新设置head和tail
        head = 0;
        tail = size;
    }

    @Override
    public T getFront() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);
        tail = head = 0;
        size = 0;
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public String toString() {
        if (size == 0)
            return "[]";
        StringBuilder builder = new StringBuilder();
        builder.append("head [");
        for (int i = 0; i < size; i++) {
            builder.append(data[(i + head) % data.length]).append(" ,");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("] tail");
        return builder.toString();
    }


    public static void main(String[] args) {
        LoopArrayQueue<Integer> loopArrayQueue = new LoopArrayQueue<>(6);
        for (int i = 0; i < 7; i++) {
            loopArrayQueue.enqueue(i);
        }
        System.out.println(loopArrayQueue);
        System.out.println("capacity: " + loopArrayQueue.getCapacity());

        for (int i = 0; i < 7; i++) {
            loopArrayQueue.dequeue();
            System.out.println(loopArrayQueue);
        }
    }
}
