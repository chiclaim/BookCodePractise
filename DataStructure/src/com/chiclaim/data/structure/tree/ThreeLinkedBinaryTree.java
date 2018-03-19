package com.chiclaim.data.structure.tree;

/**
 * 二叉树的三叉链表存储法
 * Created by Chiclaim on 2018/3/19.
 */
public class ThreeLinkedBinaryTree<T> {

    class Node {
        Object data;
        Node left;
        Node right;
        Node parent;

        public Node() {
        }

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node left, Node right, Node parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }


        @Override
        public String toString() {
            return data + "";
        }
    }

    private Node root;

    public ThreeLinkedBinaryTree() {
        this.root = new Node();
    }

    public ThreeLinkedBinaryTree(T data) {
        this.root = new Node(data);
    }

    public Node addNode(Node parent, T data, boolean left) {
        if (data == null) {
            throw new NullPointerException();
        }

        if (parent == null) {
            throw new IllegalStateException("节点为null，不能添加子节点");
        }

        if (left && parent.left != null) {
            throw new IllegalStateException(parent + "节点已经存在左节点");
        }

        if (!left && parent.right != null) {
            throw new IllegalStateException(parent + "节点已经存在右节点");
        }

        Node node = new Node(data);
        if (left) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        node.parent = parent;
        return node;
    }

    public boolean isEmpty() {
        return root.data == null;
    }

    public T getParent(Node node) {
        if (node == null) {
            throw new NullPointerException();
        }
        return (T) node.parent.data;
    }

    public T getLeft(Node parent) {
        if (parent == null) {
            throw new NullPointerException();
        }
        return (T) parent.left.data;
    }

    public T getRight(Node parent) {
        if (parent == null) {
            throw new NullPointerException();
        }
        return (T) parent.right.data;
    }

    public Node getRoot() {
        return root;
    }

    public int deep() {
        return deep(root);
    }


    public int deep(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int left = deep(node.left);
        int right = deep(node.right);
        //记录其所有左、右子树较大的深度
        int max = left > right ? left : right;
        //返回其左右子树中较大深度+1
        return max + 1;
    }

    public static void main(String[] args) {
        ThreeLinkedBinaryTree<String> binaryTree = new ThreeLinkedBinaryTree<>("0");

        ThreeLinkedBinaryTree<String>.Node left = binaryTree.addNode(binaryTree.getRoot(), "1", true);
        ThreeLinkedBinaryTree<String>.Node right = binaryTree.addNode(binaryTree.getRoot(), "2", false);

        binaryTree.addNode(left, "3", true);
        binaryTree.addNode(left, "4", false);


        binaryTree.addNode(right, "5", true);
        binaryTree.addNode(right, "6", false);

        System.out.println("tree deep = " + binaryTree.deep());

        System.out.println(left + "的左节点: " + binaryTree.getLeft(left));
        System.out.println(left + "的右节点: " + binaryTree.getRight(left));

        System.out.println(left + "的父节点: " + binaryTree.getParent(left));


    }


}
