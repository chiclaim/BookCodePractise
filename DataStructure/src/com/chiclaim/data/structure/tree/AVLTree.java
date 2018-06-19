package com.chiclaim.data.structure.tree;

import com.chiclaim.data.structure.set.FileOperation;
import com.chiclaim.data.structure.tree.map.BSTMap;

import java.util.*;

/**
 * 二分搜索树实现的Map映射
 */
public class AVLTree<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        private K key;
        private V value;
        private int height;
        private Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node<K, V> root;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(K key, V value) {
        root = _add(root, key, value);
    }

    private Node<K, V> _add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = _add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = _add(node.right, key, value);
        else //如果已经存在，修改对应value的值
            node.value = value;

        //维护node的高度
        //左右子树最高的高度+1
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //获取节点的平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 右旋转
        // 左子树比右子树要高超过了1，说明当前节点的平衡被打破
        // 且新添加的节点是在左子树的左子树的左侧

        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rotateRight(node);

        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return rotateLeft(node);

        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);//转化LL形式
            return rotateRight(node);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);//转化成RR
            return rotateLeft(node);
        }

        return node;
    }


    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> nodeLeft = node.left;
        Node<K, V> lRight = nodeLeft.right;
        //右旋转
        nodeLeft.right = node;
        node.left = lRight;

        //维护节点高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        nodeLeft.height = 1 + Math.max(getHeight(nodeLeft.left), getHeight(nodeLeft.right));

        return nodeLeft;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> nodeRight = node.right;
        Node<K, V> rLeft = nodeRight.left;
        //左旋转
        nodeRight.left = node;
        node.right = rLeft;

        //维护节点高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        nodeRight.height = 1 + Math.max(getHeight(nodeRight.left), getHeight(nodeRight.right));

        return nodeRight;
    }
    

    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
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

        Node<K, V> retNode = null;

        //如果要删除的节点小于当前节点，继续查询其左子树
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        }
        //如果要删除的节点大于当前节点，继续查询其右子树
        else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        }

        //要删除的节点就是当前的节点
        else {
            //如果要删除节点的左子树为空
            if (node.left == null) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }

            //如果要删除节点的右子树为空
            else if (node.right == null) {
                Node<K, V> leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }
            //=======如果要删除的节点左右子树都不为空
            else {
                //找到要删除节点的后继，也就是右子树的最小值
                Node<K, V> successor = getMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;

                retNode = successor;
            }
        }

        //如果删除的节点是叶子节点
        if (retNode == null) {
            return null;
        }


        //得到retNode之后，维护平衡性

        //维护node的高度
        //左右子树最高的高度+1
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //获取节点的平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 右旋转
        // 左子树比右子树要高超过了1，说明当前节点的平衡被打破
        // 且新添加的节点是在左子树的左子树的左侧

        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rotateRight(retNode);

        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return rotateLeft(retNode);

        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = rotateLeft(retNode.left);//转化LL形式
            return rotateRight(retNode);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rotateRight(retNode.right);//转化成RR
            return rotateLeft(retNode);
        }

        return retNode;
    }

    /**
     * 是否是二分搜索树
     *
     * @return
     */
    public boolean isBST() {
        List<K> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node<K, V> node) {
        if (node == null)
            return true;
        //当前的节点的平衡因子
        if (Math.abs(getBalanceFactor(node)) > 1)
            return false;
        //判断节点的左右子树
        return isBalance(node.left) && isBalance(node.right);
    }

    private void inorder(Node<K, V> node, List<K> keys) {
        if (node == null)
            return;
        inorder(node.left, keys);
        keys.add(node.key);
        inorder(node.right, keys);
    }


    private static void bstVsAVL() {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {

            //just for test linked
            //Collections.sort(words);

            long startTime = System.nanoTime();
            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.add(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for (String word : words) {
                avl.contains(word);
            }

            System.out.println("Total different words: " + avl.size());
            System.out.println("Frequency of PRIDE: " + avl.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avl.get("prejudice"));
            System.out.println("isBalance: " + avl.isBalance());
            System.out.println("isBST: " + avl.isBST());

            long endTime = System.nanoTime();
            System.out.println("AVL: " + (endTime - startTime) / 1000000000.0);

            System.out.println();


            startTime = System.nanoTime();
            BSTMap<String, Integer> bstMap = new BSTMap<>();
            for (String word : words) {
                if (bstMap.contains(word))
                    bstMap.add(word, bstMap.get(word) + 1);
                else
                    bstMap.add(word, 1);
            }

            for (String word : words) {
                bstMap.contains(word);
            }
            System.out.println("Total different words: " + avl.size());
            System.out.println("Frequency of PRIDE: " + avl.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avl.get("prejudice"));
            endTime = System.nanoTime();
            System.out.println("BSTMap: " + (endTime - startTime) / 1000000000.0);
        }

    }

    private static void testBalance() {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();
            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.add(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            System.out.println("isBalance:" + avl.isBalance());
            System.out.println("isBST:" + avl.isBST());
            System.out.println("Total different words: " + avl.size());
            System.out.println("Frequency of PRIDE: " + avl.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avl.get("prejudice"));

        }

    }

    private static void testRemove() {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();
            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.add(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            System.out.println("Total different words: " + avl.size());
            System.out.println("Frequency of PRIDE: " + avl.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avl.get("prejudice"));

            for (String word : words) {
                avl.remove(word);
                if (!avl.isBST() || !avl.isBalance()) {
                    throw new IllegalStateException("broke balance");
                }
            }
            System.out.println("Test remove success! ");

        }
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        bstVsAVL();
//        testBalance();
//        testRemove();

    }
}
