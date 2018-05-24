package com.chiclaim.data.structure.linear;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 简易版队列
 * Created by Chiclaim on 2018/5/23.
 */
public class SimpleArrayQueue<T> implements Queue<T> {

    private T[] data;
    private int size;

    public SimpleArrayQueue(int initialSize) {
        data = (T[]) new Object[initialSize];
    }

    @Override
    public void enqueue(T element) {
        data[size++] = element;
        if (size >= data.length) {
            doubleCapacity();
        }
    }

    private void doubleCapacity() {
        data = Arrays.copyOf(data, data.length << 1);
    }

    @Override
    public T dequeue() {
        T d = data[0];
        data[0] = null;
        System.arraycopy(data, 1, data, 0, --size);
        return d;
    }

    @Override
    public T getFront() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        Arrays.fill(data, null);
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("head[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]).append('，');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        SimpleArrayQueue<Integer> queue = new SimpleArrayQueue<>(5);
        int count = 9;
        for (int i = 0; i < count; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue + ". queue size=" + queue.size());

        for (int i = 0; i < count; i++) {
            queue.dequeue();
            System.out.println(queue + ". queue size=" + queue.size());
        }
    }
}
