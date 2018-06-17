package com.chiclaim.data.structure.tree.uf;

/**
 * 并查集，把每个元素当作一个节点，子节点指向父节点
 * <p>
 * 主要逻辑和UnionFind2类似，加上了基于size的优化
 */
public class UnionFind3 implements UF {

    private int[] parents;
    private int[] sz;//记录每棵树的节点个数

    public UnionFind3(int size) {
        parents = new int[size];
        sz = new int[size];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            sz[i] = 1;//每个根节点的一开始都只有一个节点
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
        if (pRoot == qRoot) {
            return;
        }
        //根据根节点的子节点个数来判断合并方向
        if (sz[pRoot] < sz[qRoot]) {
            parents[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parents[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }

}
