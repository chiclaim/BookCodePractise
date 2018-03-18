package com.chiclaim.data.structure.tree;

import java.util.Arrays;

/**
 * 对于普通的树来说，它遵循的规律太少，程序控制起来反而更加复杂，因此限制了它在实际应用中的使用。
 * <p>
 * 如果对普通树增加一些限制，让一棵树中每个节点最多只能包含两个子节点，而且严格区分左子节点、右子节点（左右位置不能交换），
 * 这棵树就变成了二叉树
 * <p>
 * 通过数组来保存二叉树节点，顺序存储二叉树的思想就是将树中不同的节点存入数组的不同位置。
 * <p>
 * 二叉树有如下几个特性：
 * 1，添加/获取索引为index的左节点：index * 2 + 1
 * <p>
 * 2，添加/获取索引为index的右节点：index * 2 + 2
 * <p>
 * 3，深度为deep的二叉树，总结点数 = 2的deep方-1
 * <p>
 * 4，第n层总点数 = 2的(n-1)方，如第3层的总结点数为4
 * <p>
 * 5，获取索引为index的父节点：parentIndex = (index - 1) / 2
 * <p>
 *  对于普通的二叉树（不是满二叉树），哪些空出来的节点对应的数组元素留空就可以了，
 *  由此，二叉树采用顺序存储会造成一定的内存空间浪费，如果该二叉树是完全二叉树，就不会有任何空间浪费了；
 *  如果该二叉树的所有节点都只有右子节点，那么就会产生相当大的空间浪费。
 * <p>
 */
public class ArrayBinaryTree<T> {


    private static final int DEFAULT_DEEP = 5;

    private int deep;

    private int capacity;

    private Object[] nodes;

    public ArrayBinaryTree() {
        this(DEFAULT_DEEP);
    }

    public ArrayBinaryTree(int deep) {
        this.deep = deep;
        nodes = new Object[capacity = (int) Math.pow(2, deep) - 1];
    }

    public ArrayBinaryTree(int deep, T rootData) {
        this(deep);
        nodes[0] = rootData;
    }

    public void add(int parentIndex, T data, boolean left) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (nodes[parentIndex] == null) {
            throw new IllegalStateException("不存在索引为" + parentIndex + "的节点");
        }

        if (left) {
            nodes[parentIndex * 2 + 1] = data;
        } else {
            nodes[parentIndex * 2 + 2] = data;
        }
    }

    public boolean isEmpty() {
        return nodes[0] == null;
    }

    /**
     * 获取索引为index节点的父节点
     *
     * @param index
     * @return
     */
    public T getParent(int index) {
        if (index == 0) {
            return null;
        }

        return (T) nodes[(index - 1) / 2];
    }

    /**
     * 获取索引为index的右节点
     *
     * @param index
     * @return
     */
    public T getRight(int index) {
        if (2 * index + 1 >= capacity) {
            return null;
        }
        return (T) nodes[index * 2 + 2];
    }

    /**
     * 获取索引为index的左节点
     *
     * @param index
     * @return
     */
    public T getLeft(int index) {
        if (2 * index + 1 >= capacity) {
            return null;
        }
        return (T) nodes[2 * index + 1];
    }

    public T getRoot() {
        return (T) nodes[0];
    }

    public int getDeep() {
        return deep;
    }

    public int indexOf(T data) {
        for (int i = 0; i < capacity; i++) {
            if (nodes[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return Arrays.toString(nodes);
    }

    public static void main(String[] args) {

            /*
             0
            / \
           1   2
          /\   /\
         3 4   5 6
         */
        ArrayBinaryTree<Integer> arrayBinaryTree = new ArrayBinaryTree<>(3, 0);
        arrayBinaryTree.add(0, 1, true);
        arrayBinaryTree.add(0, 2, false);

        arrayBinaryTree.add(1, 3, true);
        arrayBinaryTree.add(1, 4, false);

        arrayBinaryTree.add(2, 5, true);
        arrayBinaryTree.add(2, 6, false);

        System.out.println(arrayBinaryTree);

        System.out.println("索引为1的左节点："+arrayBinaryTree.getLeft(1));
        System.out.println("索引为1的右节点："+arrayBinaryTree.getRight(1));
        System.out.println("索引为1的父节点："+arrayBinaryTree.getParent(1));
        System.out.println("数据为1的索引："+arrayBinaryTree.indexOf(1));

    }

}
