package com.chiclaim.data.structure.tree;

/**
 * 二叉树的二叉链表存储
 * Created by Chiclaim on 2018/3/19.
 */
public class TwoLinkedBinaryTree<T> {


    class Node {
        Object data;
        Node left;
        Node right;

        public Node() {
        }

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }


    private Node root;

    public TwoLinkedBinaryTree() {
        this.root = new Node();
    }

    public TwoLinkedBinaryTree(T data) {
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
        return node;
    }

    public boolean isEmpty() {
        return root.data == null;
    }

    public Node getRoot() {
        return root;
    }

    public T getParent(Node node) {
        //需要遍历二叉树
        return null;
    }

    public T getLeft(Node parent) {
        return parent == null ? null : (T) parent.left.data;
    }

    public T getRight(Node parent) {
        return parent == null ? null : (T) parent.right.data;
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
        TwoLinkedBinaryTree<String> binaryTree = new TwoLinkedBinaryTree<>("0");

        TwoLinkedBinaryTree<String>.Node left = binaryTree.addNode(binaryTree.getRoot(), "1", true);
        TwoLinkedBinaryTree<String>.Node right = binaryTree.addNode(binaryTree.getRoot(), "2", false);

        binaryTree.addNode(left, "3", true);
        binaryTree.addNode(left, "4", false);


        binaryTree.addNode(right, "5", true);
        binaryTree.addNode(right, "6", false);

        System.out.println("tree deep = " + binaryTree.deep());

        System.out.println(left + "的左节点: " + binaryTree.getLeft(left));
        System.out.println(left + "的右节点: " + binaryTree.getRight(left));

    }
}
