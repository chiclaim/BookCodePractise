package com.chiclaim.data.structure.linear;

import java.util.Iterator;

/**
 * 实现一个简易的线性表（链式存储）
 * <p>
 * 需要注意的是remove的时候，一定要把remove的节点的next引用解除掉
 * <p>
 * Created by Chiclaim on 2018/3/7.
 */
public class LinkedList<T> implements List<T> {

    private int size;

    private Node header;
    private Node tail;

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {

        private Node current = header;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T element = current.element;
            current = current.next;
            return element;
        }
    }


    /**
     * 用于保存每个节点数据
     */
    private class Node {
        private T element;
        private Node next;

        Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 检查是否越界
     *
     * @param index
     * @param size
     */
    private void checkIndexOutOfBound(int index, int size) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index large than size");
        }
    }

    /**
     * 采用尾插法  插入新节点
     *
     * @param t
     */

    @Override
    public void add(T t) {
        addLast(t);
    }

    @Override
    public void add(int index, T t) {
        checkIndexOutOfBound(index, size);
        if (header == null) {
            add(t);
        } else {
            if (index == 0) {
                addFirst(t);
            } else {
                Node prevNode = getNodeByIndex(index - 1);
                prevNode.next = new Node(t, prevNode.next);
                size++;
            }
        }
    }

    public void addLast(T t) {
        //空链表
        if (header == null) {
            //首尾都指向新的节点
            tail = header = new Node(t, null);
        } else {
            Node newNode = new Node(t, null);
            //让尾部的next指向新的节点
            tail.next = newNode;
            //把尾部设置为新的节点
            tail = newNode;
        }
        size++;

    }

    /**
     * 采用头插法 插入新节点
     *
     * @param element
     */
    public void addFirst(T element) {
        header = new Node(element, header);
        if (tail == null) {
            tail = header;
        }
        size++;
    }

    /**
     * 根据索引获取节点
     *
     * @param index
     * @return
     */
    private Node getNodeByIndex(int index) {
        checkIndexOutOfBound(index, size - 1);
        Node current = header;
        for (int i = 0; i < size; i++, current = current.next) {
            if (index == i) {
                return current;
            }
        }
        return null;
    }

    @Override
    public T get(int index) {
        Node node = getNodeByIndex(index);
        if (node != null) {
            return node.element;
        }
        return null;
    }

    @Override
    public int indexOf(T t) {
        Node current = header;
        for (int i = 0; i < size; i++, current = current.next) {
            if (t == null && current.element == null) {
                return i;
            }
            if (t != null && t.equals(current.element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(T t) {
        int index = indexOf(t);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBound(index, size - 1);
        Node delete;
        //如果删除的是头部
        if (index == 0) {
            delete = header;
            header = header.next;
        } else {
            Node pre = getNodeByIndex(index - 1);
            delete = pre.next;
            pre.next = delete.next;
            delete.next = null;
        }
        size--;
        return delete.element;
    }

    @Override
    public void clear() {
        header = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }


    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i + "");
        }

        System.out.println("forEach方式遍历：");
        forEach(linkedList);

        System.out.println("for index方式遍历：");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print(linkedList.get(i));
        }
        System.out.println();

        System.out.println("集合大小：" + linkedList.size());

        //删除元素1
        linkedList.remove("1");
        forEach(linkedList);

        //删除index=1的元素
        linkedList.remove(1);
        forEach(linkedList);
        System.out.println("集合大小：" + linkedList.size());

        System.out.println("元素为9的索引：" + linkedList.indexOf("9"));

        linkedList.add(1, "1");
        linkedList.add(2, "2");
        forEach(linkedList);
    }

    private static void forEach(List<String> linkedList) {
        for (String str : linkedList) {
            System.out.print(str);
        }
        System.out.println();
    }

}
