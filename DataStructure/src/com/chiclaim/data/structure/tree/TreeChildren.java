package com.chiclaim.data.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 子节点链 表示法
 * <p>
 * 父节点"记住"它的所有子节点，在这种方式下，由于每个父节点需要记住多个节点，因此必须此采用"子节点链"表示
 * <p>
 * <p>
 * <p>
 * Created by Chiclaim on 2018/3/15.
 */
public class TreeChildren<T> {


    private static class Child {

        private int position;
        private Child next;

        public Child(int position, Child next) {
            this.position = position;
            this.next = next;
        }
    }

    public static class Node<T> {
        T data;
        Child firstChild;

        public Node(T data) {
            this.data = data;
        }

        public String toString() {
            if (firstChild != null) {
                return "Node[" + data + ",firstChildPosition=" + firstChild.position + "]";
            }
            return "Node[" + data + ",firstChildPosition=" + -1 + "]";
        }
    }

    private static final int DEFAULT_SIZE = 100;

    private Node<T>[] nodes;

    private int capacity;

    private int size;

    public TreeChildren(T data) {
        this(data, DEFAULT_SIZE);
    }

    public TreeChildren(T data, int size) {
        this.capacity = size;
        this.nodes = new Node[capacity];
        this.nodes[0] = new Node<>(data);
        this.size++;
    }

    public void addNode(T data, Node parent) {

        if (data == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < capacity; i++) {
            //找到数组中第一个为null的元素，用于保存新节点
            if (nodes[i] == null) {
                nodes[i] = new Node<>(data);
                //如果该节点没有子节点
                if (parent.firstChild == null) {
                    parent.firstChild = new Child(i, null);
                } else {
                    Child current = parent.firstChild;
                    //找到子节点链最后一个
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = new Child(i, null);
                }
                this.size++;
                return;
            }
        }
        throw new IllegalStateException("该树已满");
    }

    public boolean isEmpty() {
        return nodes[0] == null;
    }

    /**
     * 获取指定节点的所有直接子节点
     *
     * @param parent
     * @return
     */
    public List<Node<T>> children(Node parent) {
        if (parent.firstChild == null) {
            return null;
        }
        List<Node<T>> list = new ArrayList<>();
        Child current = parent.firstChild;
        while (current != null) {
            list.add(nodes[current.position]);
            current = current.next;
        }
        return list;
    }

    /**
     * 返回指定节点的第index个子节点
     *
     * @param parent
     * @param index
     * @return
     */
    public Node<T> getChildByIndex(Node parent, int index) {
        Child current = parent.firstChild;
        for (int i = 0; current != null; i++, current = current.next) {
            if (i == index) {
                return nodes[current.position];
            }
            //current = current.next
        }
        return null;
    }

    public Node getRoot() {
        return nodes[0];
    }

    public int deep() {
        return deep(getRoot());
    }

    //递归方法：每棵子树的深度为其所有子树最大深度+1
    public int deep(Node node) {
        if (node.firstChild == null) {
            return 1;
        }
        //保存所有子树的最大深度
        int deep = 0;
        Child current = node.firstChild;
        while (current != null) {
            int tmp = deep(nodes[current.position]);
            if (tmp > deep) {
                deep = tmp;
            }
            current = current.next;
        }
        return deep + 1;
    }


    /**
     * 获取某个节点的索引
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

    /**
     * 根据索引获取节点
     *
     * @param index
     * @return
     */
    public Node<T> get(int index) {
        return nodes[index];
    }

    public static void main(String[] args) {
        TreeChildren<String> tree = new TreeChildren<>("Root");
        tree.addNode("Second1", tree.getRoot());
        tree.addNode("Second2", tree.getRoot());
        tree.addNode("Second3", tree.getRoot());
        System.out.println("树深度：" + tree.deep());


        tree.addNode("Third1", tree.get(1));
        System.out.println("树深度：" + tree.deep());

        System.out.println(tree.get(1) + "-->" + tree.children(tree.get(1)));

        System.out.println("获取根节点的第二个子节点" + tree.getChildByIndex(tree.getRoot(), 1));


    }



    /*
             Root
        /     |      \
       /      |       \
   Second1 Second2   Second3
     /
    /
  Third1

     */
}
