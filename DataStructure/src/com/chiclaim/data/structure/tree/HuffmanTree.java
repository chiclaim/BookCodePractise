package com.chiclaim.data.structure.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 哈夫曼树（又译为霍夫曼树）
 * Created by Chiclaim on 2018/3/19.
 */
public class HuffmanTree {


    public static class Node<T> {
        T data;
        double weight;
        Node<T> left;
        Node<T> right;

        public Node(T data, double weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return data + "(" + weight + ")";
        }
    }

    public static <T> Node<T> createTree(List<Node<T>> nodes) {
        //只要nodes数组中海油两个以上节点
        while (nodes.size() > 1) {
            //快速排序
            quickSort(nodes);

            //获取权值最小的两个节点
            Node<T> left = nodes.get(nodes.size() - 1);
            Node<T> right = nodes.get(nodes.size() - 2);
            //生成新节点，新节点的权值为两个子节点的权值之和
            Node<T> parent = new Node<>(null, left.weight + right.weight);
            //让新节点作为权值最小的两个节点的父节点
            parent.left = left;
            parent.right = right;
            //删除权值最小的两个节点
            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
            //将新生成的父节点添加到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static <T> void quickSort(List<Node<T>> nodes) {
        subSort(nodes, 0, nodes.size() - 1);
    }

    private static <T> void subSort(List<Node<T>> nodes, int start, int end) {
        if (start < end) {
            //以第一个元素作为分界值
            Node base = nodes.get(start);
            //i从左边搜索，搜索大于分界值的元素索引
            int i = start;
            //j从右边开始搜索，搜索小于分界值的元素索引
            int j = end + 1;
            while (true) {
                //找到大于分界值的元素的索引，或i已经到了end处
                while (i < end && nodes.get(++i).weight >= base.weight) ;
                //找到小于分界值的元素的索引，或j已经到了start处
                while (j > start && nodes.get(--j).weight <= base.weight) ;
                if (i < j) {
                    swap(nodes, i, j);
                } else {
                    break;
                }
            }
            swap(nodes, start, j);
            //递归左子序列
            subSort(nodes, start, j - 1);
            //递归右子序列
            subSort(nodes, j + 1, end);
        }
    }

    private static <T> void swap(List<Node<T>> nodes, int i, int j) {
        Node<T> tmp;
        tmp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, tmp);
    }

    public static List<Node> breadthFirstLoop(Node root) {

        if (root == null) {
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            nodes.add(queue.peek());
            Node node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return nodes;
    }


    public static void main(String[] args) {
        List<Node<String>> nodes = new ArrayList<>();
        nodes.add(new Node<>("A", 40));
        nodes.add(new Node<>("B", 7));
        nodes.add(new Node<>("C", 10));
        nodes.add(new Node<>("D", 30));
        nodes.add(new Node<>("E", 12));
        nodes.add(new Node<>("F", 2));
        Node<String> root = HuffmanTree.createTree(nodes);
        System.out.println(breadthFirstLoop(root));
    }

}
