package com.chiclaim.data.structure.tree;

import com.chiclaim.data.structure.linear.Queue;

/**
 * 基于大顶堆的优先队列
 *
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {

    private MaxHeap<T> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }


    @Override
    public void enqueue(T element) {
        maxHeap.add(element);
    }

    @Override
    public T dequeue() {
        return maxHeap.removeMax();
    }

    @Override
    public T getFront() {
        return maxHeap.getMax();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public int size() {
        return maxHeap.size();
    }

    @Override
    public void clear() {
        maxHeap.clear();
    }
}
