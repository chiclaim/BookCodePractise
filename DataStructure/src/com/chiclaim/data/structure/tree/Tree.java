package com.chiclaim.data.structure.tree;

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


    public void addNode(T data, Node parent) {

    }

    public boolean isEmpty() {
        return false;
    }

    public Node<T> parentOf(Node node) {
        return null;
    }

    public List<Node<T>> children(Node parent) {
        return null;
    }

    public int deep() {
        return 0;
    }

    public int indexOf(Node node) {
        return -1;
    }


}
