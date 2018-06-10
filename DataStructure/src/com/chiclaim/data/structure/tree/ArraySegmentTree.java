package com.chiclaim.data.structure.tree;

import java.util.ArrayList;
import java.util.Random;

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
        if (arr == null || arr.length == 0) {
            return;
        }
        this.merger = merger;
        data = (T[]) new Object[arr.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        this.tree = (T[]) new Object[data.length * 4];
        buildSegmentTree(0, 0, data.length - 1);

    }


    /**
     * 构建线段树
     *
     * @param treeIndex 当前需要添加节点的索引
     * @param treeLeft  treeIndex左边界
     * @param treeRight treeIndex右边界
     */
    private void buildSegmentTree(int treeIndex, int treeLeft, int treeRight) {
        if (treeLeft == treeRight) {
            tree[treeIndex] = data[treeLeft];
            return;
        }
        //当前节点左子树索引
        int leftTreeIndex = getLeft(treeIndex);
        //当前节点右子树索引
        int rightTreeIndex = getRight(treeIndex);
        //int mid = (left+right)/2;
        int mid = treeLeft + (treeRight - treeLeft) / 2;
        //构建左子树
        buildSegmentTree(leftTreeIndex, treeLeft, mid);
        //构建右子树
        buildSegmentTree(rightTreeIndex, mid + 1, treeRight);
        //当前节点存放的值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);

    }

    public T query(int start, int end) {
        return query(0, 0, data.length - 1, start, end);
    }

    /**
     * @param treeIndex 当前查找的节点
     * @param treeLeft  treeIndex的左边界
     * @param treeRight treeIndex的右边界
     * @param queryL    用户需要查找的左边界
     * @param queryR    用户需要查找的右边界
     * @return
     */
    private T query(int treeIndex, int treeLeft, int treeRight, int queryL, int queryR) {

        System.out.println(treeLeft + "," + treeRight + "----------" + queryL + "," + queryR);
        //1, 需要查找的范围完刚好在这个treeIndex节点的区间
        if (treeLeft == queryL && treeRight == queryR) {
            System.out.println(tree[treeIndex]);
            return tree[treeIndex];
        }

        //当前节点的区间的中间点
        int mid = treeLeft + (treeRight - treeLeft) / 2;
        //左子树索引
        int leftTreeIndex = getLeft(treeIndex);
        //右子树索引
        int rightTreeIndex = getRight(treeIndex);


        //2, 需要查找的范围完全在左子树的区间里
        if (queryR <= mid) {
            return query(leftTreeIndex, treeLeft, mid, queryL, queryR);
        }
        //3, 需要查找的范围完全在右子树区间里
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, treeRight, queryL, queryR);
        }

        //4，需要查找的范围一部分在左子树里，一部分在右子树中
        T left = query(leftTreeIndex, treeLeft, mid, queryL, mid);
        T right = query(rightTreeIndex, mid + 1, treeRight, mid + 1, queryR);
        System.out.println("merge:" + merger.merge(left, right));
        return merger.merge(left, right);
    }

    public void update(int index, T e) {
        data[index] = e;
        update(0, 0, data.length - 1, index, e);
    }


    private void update(int treeIndex, int treeLeft, int treeRight, int index, T e) {
        if (treeLeft == treeRight) {
            tree[treeIndex] = e;
            return;
        }

        int mid = treeLeft + (treeRight - treeLeft) / 2;
        int leftChildIndex = getLeft(treeIndex);
        int rightChildIndex = getRight(treeIndex);

        if (index <= mid) {
            update(leftChildIndex, treeLeft, mid, index, e);
        } else if (index >= mid + 1) {
            update(rightChildIndex, mid + 1, treeRight, index, e);
        }

        //更改完叶子节点后，还需要对他的所有祖辈节点更新
        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
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
        Integer[] numbers = new Integer[]{2, 6, 1, 5, 8, 3, 4, 1, 2, 5};
        ArraySegmentTree<Integer> segmentTree = new ArraySegmentTree<>(numbers,
                (a, b) -> a + b);
        System.out.println(segmentTree.toString());

        System.out.println(segmentTree.query(3, 5));

//        System.out.println("index为1的值改成3");
//        segmentTree.update(1, 3);

        System.out.println(segmentTree.toString());

        //System.out.println(segmentTree.query(1, 3));


    }
}
