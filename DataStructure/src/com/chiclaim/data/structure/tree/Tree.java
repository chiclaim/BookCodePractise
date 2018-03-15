package com.chiclaim.data.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树
 * Created by Chiclaim on 2018/3/14.
 */
public class Tree<T> {

    static class Node<T> {
        T data;
        int parent;

        public Node(T data, int parent) {
            this.data = data;
            this.parent = parent;
        }

        public String toString() {
            return "Node[data=" + data + ",parent=" + parent + "]";
        }
    }


    private static final int DEFAULT_SIZE = 100;

    private int capacity;

    private int size;

    private Node<T>[] nodes;

    public Tree(T data) {
        this(data, DEFAULT_SIZE);
    }


    public Tree(T data, int capacity) {
        this.capacity = capacity;
        nodes = new Node[capacity];
        nodes[0] = new Node<T>(data, -1);
        size++;
    }


    public void addNode(T data, Node parent) {
        if (data == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < capacity; i++) {
            //首先找到数组的空位置
            if (nodes[i] == null) {
                //父节点的index作为新节点的parent
                nodes[i] = new Node<>(data, indexOf(parent));
                size++;
                return;
            }
        }
        throw new IllegalStateException("树已满");
    }

    public boolean isEmpty() {
        return nodes[0] == null;
    }

    public Node<T> parentOf(Node node) {
        return nodes[node.parent];
    }

    public Node<T> getRoot() {
        return nodes[0];
    }

    /**
     * 获取指定节点的所有直接子节点
     *
     * @param parent
     * @return
     */
    public List<Node<T>> children(Node parent) {
        List<Node<T>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (nodes[i].parent == indexOf(parent)) {
                list.add(nodes[i]);
            }
        }
        return list;
    }

    /**
     * 树的深度
     *
     * @return
     */
    public int deep() {
        //深度逻辑：先按照数组顺序遍历，然后对该节点 自下到上遍历，直到根节点
        int deep = 0;
        for (int i = 0; i < size; i++) {
            int parent = nodes[i].parent;
            int subDeep = 1;
            while (parent != -1) {
                subDeep++;
                parent = nodes[parent].parent;
            }
            if (subDeep > deep) {
                deep = subDeep;
            }
        }
        return deep;
    }

    /**
     * 获取某个节点的
     *
     * @param node
     * @return
     */
    public int indexOf(Node node) {
        for (int i = 0; i < size; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

    public Node<T>[] getAll() {
        return nodes;
    }


    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("Root");
        tree.addNode("Second1", tree.getRoot());
        tree.addNode("Second2", tree.getRoot());
        System.out.println("树深度：" + tree.deep());


        tree.addNode("Third1", tree.getAll()[1]);
        System.out.println("树深度：" + tree.deep());

        System.out.println(tree.getAll()[1] + "-->" + tree.children(tree.getAll()[1]));

    }


}
