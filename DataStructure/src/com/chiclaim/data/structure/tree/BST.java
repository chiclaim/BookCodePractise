package com.chiclaim.data.structure.tree;

/**
 * 二分搜索树（Binary Search Tree）
 */
public class BST<T extends Comparable<T>> {

    private static class Node<T> {
        private T value;
        private Node<T> left, right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add2(T e) {
        if (root == null) {
            root = new Node<>(e);
            size++;
            return;
        }
        add(root, e);
    }

    public void add(T e) {
        root = _add(root, e);
    }

    private void add(Node<T> node, T e) {

        //递归的终止条件
        if (node.value.compareTo(e) == 0) {
            return;
        } else if (e.compareTo(node.value) < 0 && node.left == null) {
            node.left = new Node<>(e);
            size++;
            return;
        } else if (e.compareTo(node.value) > 0 && node.right == null) {
            node.right = new Node<>(e);
            size++;
            return;
        }

        //如果添加的值小于节点的值，且节点的左孩子不为空
        if (e.compareTo(node.value) < 0) {
            add(node.left, e);
        }
        //如果添加的值大于节点的值，且节点的右孩子不为空
        else if (e.compareTo(node.value) > 0) {
            add(node.right, e);
        }

    }

    /**
     * 对add递归方法优化
     *
     * @param node
     * @param e
     * @return
     */
    private Node<T> _add(Node<T> node, T e) {
        if (node == null) {
            size++;
            return new Node<>(e);
        }

        if (e.compareTo(node.value) < 0)
            node.left = _add(node.left, e);
        else if (e.compareTo(node.value) > 0)
            node.right = _add(node.right, e);

        return node;
    }


    public boolean contains(T e) {
        return contains(root, e);
    }

    private boolean contains(Node<T> node, T e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.value) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.value) > 0) {
            return contains(node.right, e);
        }
        return true;
    }


    /**
     * 前序遍历
     */
    public void preorder() {
        preorder(root);
    }

    private void preorder(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node<T> node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(node.value);
        inorder(node.right);
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(Node<T> node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.value);
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = new int[]{5, 3, 6, 8, 4, 2};
        for (int value : nums) {
            bst.add(value);
        }

        bst.preorder();
        System.out.println();
        bst.inorder();
        System.out.println();
        bst.postorder();
    }
}
