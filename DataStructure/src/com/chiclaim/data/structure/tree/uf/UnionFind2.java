package com.chiclaim.data.structure.tree.uf;

/**
 * 并查集，把每个元素当作一个节点，子节点指向父节点
 */
public class UnionFind2 implements UF {

    private int[] parents;

    public UnionFind2(int size) {
        parents = new int[size];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    @Override
    public int size() {
        return parents.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 查询某个节点的根节点
     * 时间复杂度为O(h)
     *
     * @param p
     * @return
     */
    private int find(int p) {
        while (p != parents[p]) {
            p = parents[p];
        }
        return p;
    }

    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)  {
            return;
        }
        parents[pRoot] = qRoot;
    }

}
