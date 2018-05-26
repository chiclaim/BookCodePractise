package com.chiclaim.data.structure.tree;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 堆
 */
public class Heap<T extends Comparable<T>> {

    private T[] data;
    private int size;

    public Heap() {
        this(16);
    }

    public Heap(int capacity) {
        data = (T[]) new Comparable[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        Arrays.fill(data, null);
    }

    public int getParent(int index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }

    public int getLeft(int index) {
        return index * 2 + 1;
    }

    public int getRight(int index) {
        return index * 2 + 2;
    }

    public void add(T element) {
        data[size++] = element;
        if (size == data.length) {
            doubleCapacity();
        }
        siftUp(size - 1);
    }

    private void doubleCapacity() {
        data = Arrays.copyOf(data, data.length << 1);
    }

    private void swap(int i, int j) {
        T tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private void siftUp(int index) {
        int parentIndex;
        //如果当前的节点比父节点要大
        while (index > 0 && data[index].compareTo(data[parentIndex = getParent(index)]) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * 移除堆中最大元素
     */
    public T removeMax() {
        if (size == 0)
            throw new NoSuchElementException();

        T delete = data[0];
        swap(0, size - 1);
        data[--size] = null;

        siftDown(0);

        return delete;
    }

    private void siftDown(int index) {
        int left;
        while ((left = getLeft(index)) < size) {
            int max = left;
            if (left + 1 < size && data[left + 1].compareTo(data[left]) > 0) {
                max = left + 1;
            }
            if (data[max].compareTo(data[index]) <= 0) {
                break;
            }
            swap(index, max);
            index = max;
        }
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        int count = 100;
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            heap.add(random.nextInt(1000));
        }

        int[] values = new int[count];
        for (int i = 0; i < count; i++) {
            values[i] = heap.removeMax();
        }

        //check
        for (int i = 1; i < count; i++) {
            if (values[i - 1] < values[i])
                throw new IllegalStateException();
        }

        System.out.println(Arrays.toString(values));
    }


}
