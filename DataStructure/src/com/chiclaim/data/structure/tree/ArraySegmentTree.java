package com.chiclaim.data.structure.tree;

/**
 * 线段树
 *
 * @param <T>
 */
public class ArraySegmentTree<T> {

    private T tree[];
    private T data[];

    private Merger<T> merger;

    public interface Merger<T> {
        T merge(T a, T b);
    }

    public ArraySegmentTree(T[] arr, Merger<T> merger) {
        this.merger = merger;
        data = (T[]) new Object[arr.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        this.tree = (T[]) new Object[data.length * 4];
        buildSegmentTree(0, 0, data.length - 1);

    }

    private void buildSegmentTree(int index, int left, int right) {
        if (left == right) {
            tree[index] = data[left];
            return;
        }

        int leftTreeIndex = getLeft(index);
        int rightTreeIndex = getRight(index);
        //int mid = (left+right)/2;
        int mid = left + (right - left) / 2;
        buildSegmentTree(leftTreeIndex, left, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, right);

        tree[index] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);

    }

    public T get(int index) {
        return data[0];
    }

    public int size() {
        return data.length;
    }

    public int getLeft(int index) {
        return index * 2 + 1;
    }

    public int getRight(int index) {
        return index * 2 + 2;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] == null) {
                continue;
            }
            builder.append(tree[i]).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(']');
        return builder.toString();
    }

    public static void main(String[] args) {
        Integer[] numbers = new Integer[]{2, 1, 4, 3};
        ArraySegmentTree<Integer> segmentTree = new ArraySegmentTree<>(numbers,
                (a, b) -> a + b);
        System.out.println(segmentTree.toString());
    }

}
