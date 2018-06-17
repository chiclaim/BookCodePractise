package com.chiclaim.data.structure.tree.uf;

/**
 * 并查集，把每个元素当作一个节点，子节点指向父节点
 * <p>
 * 主要逻辑和UnionFind3类似，使用rank来替换size的优化
 */
public class UnionFind4 implements UF {

    private int[] parents;
    private int[] rank;//rank[i]表示i为根的集合所表示的树的层数

    public UnionFind4(int size) {
        parents = new int[size];
        rank = new int[size];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            rank[i] = 1;//每个根节点所在的树一开始都只有一个层
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
        //根据根节点所在树的层级来判断合并方向
        //层级矮的树往层级高的树合并不需要维护rank
        if (rank[pRoot] < rank[qRoot]) {
            parents[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parents[qRoot] = pRoot;
        } else {//只有rank相等的情况才需要维护rank
            parents[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

}
