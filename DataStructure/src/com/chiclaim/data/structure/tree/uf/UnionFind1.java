package com.chiclaim.data.structure.tree.uf;

/**
 * 并查集
 * <p>
 * 使用数组来实现，find操作为 O(1)，union操作为 O(n)
 * <p>
 * quick find
 */
public class UnionFind1 implements UF {

    private int[] array;

    public UnionFind1(int size) {
        array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }


    @Override
    public int size() {
        return array.length;
    }

    private int find(int p) {
        return array[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        //如果本身就是相连的
        if (qID == pID) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == pID) {
                array[i] = qID;
            }
        }
    }
}
