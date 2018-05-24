package com.chiclaim.data.structure.linear;

/**
 * Created by Chiclaim on 2018/5/24.
 */
public interface Queue<T> {
    void enqueue(T element);
    T dequeue();
    T getFront();
    boolean isEmpty();
    int size();
    void clear();
}
