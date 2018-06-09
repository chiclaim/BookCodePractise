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


    /**
     * 构建线段树
     * @param treeIndex 当前需要添加节点的索引
     * @param treeLeft  treeIndex左边界
     * @param treeRight treeIndex右边界
     */
    private void buildSegmentTree(int treeIndex, int treeLeft, int treeRight) {
        if (treeLeft == treeRight) {
            tree[treeIndex] = data[treeLeft];
            return;
        }

        int leftTreeIndex = getLeft(treeIndex);
        int rightTreeIndex = getRight(treeIndex);
        //int mid = (left+right)/2;
        int mid = treeLeft + (treeRight - treeLeft) / 2;
        buildSegmentTree(leftTreeIndex, treeLeft, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, treeRight);

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

        //1, 需要查找的范围完刚好在这个treeIndex节点的区间
        if (treeLeft == queryL && treeRight == queryR) {
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

        //需要查找的范围一部分在左子树里，一部分在右子树中
        T left = query(leftTreeIndex, treeLeft, mid, queryL, mid);
        T right = query(rightTreeIndex, mid + 1, treeRight, mid + 1, queryR);
        return merger.merge(left, right);
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

        System.out.println(segmentTree.query(1, 3));
    }

}
