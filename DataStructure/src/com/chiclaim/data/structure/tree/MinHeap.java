package com.chiclaim.data.structure.tree;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 小顶堆
 */
public class MinHeap<T extends Comparable<T>> {

    private T[] data;
    private int size;

    public MinHeap() {
        this(16);
    }

    public MinHeap(int capacity) {
        data = (T[]) new Comparable[capacity];
    }

    //Heapify
    public MinHeap(T[] arr) {
        data = arr;
        size = arr.length;
        //对所有非叶子节点进行siftDown操作，从倒数第一个非叶子节点开始
        for (int i = getParent(data.length - 1); i >= 0; i--) {
            siftDown(i);
        }
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
        //如果当前的节点比父节点要小
        while (index > 0 && data[index].compareTo(data[parentIndex = getParent(index)]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * 移除堆顶元素
     */
    public T removeTop() {
        if (size == 0)
            throw new NoSuchElementException();

        T delete = getTop();
        swap(0, size - 1);
        data[--size] = null;

        siftDown(0);

        return delete;
    }

    private void siftDown(int index) {
        int left;
        while ((left = getLeft(index)) < size) {
            int min = left;
            //有右节点并且右节点小于左节点
            if (left + 1 < size && data[left + 1].compareTo(data[left]) < 0) {
                min = left + 1;
            }
            if (data[min].compareTo(data[index]) >= 0) {
                break;
            }
            swap(index, min);
            index = min;
        }
    }

    /**
     * 删除堆顶元素，插入新元素
     *
     * @param element max
     * @return
     */
    public T replace(T element) {
        T max = getTop();
        data[0] = element;
        siftDown(0);
        return max;
    }

    public T getTop(){
        if(size==0)
            throw new NoSuchElementException();
        return data[0];
    }

    private static void testHeap() {
        MinHeap<Integer> heap = new MinHeap<>();
        int count = 100;
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            heap.add(random.nextInt(1000));
        }
        int[] values = checkMinHeap(heap);
        System.out.println(Arrays.toString(values));
    }


    private static int[] checkMinHeap(MinHeap<Integer> heap) {
        int[] values = new int[heap.size];
        for (int i = 0; i < values.length; i++) {
            values[i] = heap.removeTop();
        }

        //check
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i])
                throw new IllegalStateException();
        }

        return values;

    }

    private static void heapifyVsNoHeapify() {
            /*
                10万个数据
                without heapify: 0.20134547 sec
                with    heapify: 0.28973186 sec

                100万个数据
                without heapify: 2.54230192 sec
                with    heapify: 0.78025752 sec

                1000万个数据
                without heapify: 46.96635561 sec
                with    heapify: 2.81621934 sec
             */
        int count = 1000000;
        //随机生成数组
        Integer[] data = new Integer[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            data[i] = random.nextInt(Integer.MAX_VALUE);
        }

        long start = System.nanoTime();
        MinHeap<Integer> heap = new MinHeap<>();
        for (int i = 0; i < data.length; i++) {
            heap.add(i);
        }
        long end = System.nanoTime();
        System.out.println("without heapify: " + (end - start) / 100000000.0 + " sec");

        //heapify
        long start2 = System.nanoTime();
        MinHeap<Integer> heap2 = new MinHeap(data);
        long end2 = System.nanoTime();
        System.out.println("with    heapify: " + (end2 - start2) / 100000000.0 + " sec");


        checkMinHeap(heap);
        checkMinHeap(heap2);

    }


    public static void main(String[] args) {
        testHeap();
        heapifyVsNoHeapify();
    }


}
