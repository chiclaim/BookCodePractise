package com.chiclaim.data.structure.tree.rb;


import com.chiclaim.data.structure.set.FileOperation;
import com.chiclaim.data.structure.tree.map.BSTMap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 红黑树（2-3-4树）
 */
public class RB2_3_4Tree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left, right;
        private boolean color = RED;//默认红色

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "";
        }
    }

    private Node<K, V> root;
    private int size;

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(K key, V value) {
        root = _add(root, key, value);
        //根节点保持黑色
        root.color = BLACK;
    }

    private Node<K, V> _add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }

        //是否需要颜色的翻转
        if (isRed(node.left) && isRed(node.right)) {
            flipColor(node);
        }

        if (key.compareTo(node.key) < 0)
            node.left = _add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = _add(node.right, key, value);
        else //如果已经存在，修改对应value的值
            node.value = value;

        //是否需要左旋转
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        //是否需要右旋转
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> x = node.right;
        node.right = x.left;

        x.left = node;
        x.color = node.color;

        node.color = RED;
        return x;


    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }


    private void flipColor(Node<K, V> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        }
        return node;
    }


    public Node<K, V> getMax() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return getMax(root);
    }

    private Node<K, V> getMax(Node<K, V> node) {
        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }

    public Node<K, V> getMin() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return getMin(root);
    }

    private Node<K, V> getMin(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    public Node removeMin() {
        Node delete = getMin();
        //因为可能只有一个节点，所以需要root接收removeMin的返回值null
        root = removeMin(root);
        return delete;
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null) {
            Node<K, V> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        //把要删除节点的右节点赋值给 父节点的左节点
        node.left = removeMin(node.left);
        return node;

    }

    public Node<K, V> removeMax() {
        Node<K, V> delete = getMax();
        //因为可能只有一个节点，所以需要root接收removeMin的返回值null
        root = removeMax(root);
        return delete;
    }

    public Node<K, V> removeMax(Node<K, V> node) {
        if (node.right == null) {
            Node<K, V> leftNode = node.left;
            size--;
            node.left = null;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除任意节点
     */
    public void remove(K key) {
        root = remove(root, key);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        //如果要删除的节点小于当前节点，继续查询其左子树
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        }
        //如果要删除的节点大于当前节点，继续查询其右子树
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        }

        //=======要删除的节点就是当前的节点

        //如果要删除节点的左子树为空
        if (node.left == null) {
            Node<K, V> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        //如果要删除节点的右子树为空
        if (node.right == null) {
            Node<K, V> leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        //=======如果要删除的节点左右子树都不为空

        //找到要删除节点的后继，也就是右子树的最小值
        Node<K, V> successor = getMin(node.right);
        successor.right = removeMin(node.right);
        successor.left = node.left;
        node.left = node.right = null;
        return successor;
    }

    public void levelorder(Node<K, V> root) {
        if (root == null) {
            return;
        }
        ArrayDeque<Node<K, V>> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            Node<K, V> node = deque.removeFirst();
            System.out.println(node.key + ",color=" + (node.color == BLACK ? "Black" : "Red") + " left=" + node.left + ", right=" + node.right);
            if (node.left != null)
                deque.add(node.left);
            if (node.right != null)
                deque.add(node.right);
        }
    }

    public static void main(String[] args) {
        //char[] data = new char[]{'E','C','G','B','D','F','J','A'};
        char[] data = new char[]{'E', 'D', 'R', 'O', 'S', 'X'};
        test2(data);
    }

    private static void test2(char[] data) {
        RB2_3_4Tree<Character, Character> tree = new RB2_3_4Tree<>();
        for (char c : data) {
            tree.add(c, c);
        }
        tree.levelorder(tree.root);
    }
}
