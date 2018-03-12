package com.chiclaim.data.structure;

import java.util.Iterator;

/**
 * 实现一个简易的线性表（双向链表）
 * Created by Chiclaim on 2018/3/12.
 */
public class DuplexLinkedList<T> implements List<T> {

    private int size;

    private Node header;
    private Node tail;

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {

        private int index = 0;

        public boolean hasNext() {
            return index < size();
        }

        public T next() {
            return get(index++);
        }
    }


    /**
     * 用于保存每个节点数据
     */
    private class Node {
        private T element;
        private Node prev;
        private Node next;

        Node(T element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public T getElement() {
            return element;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
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
        //空链表
        if (header == null) {
            //首尾都指向新的节点
            tail = header = new Node(t, null, null);
        } else {
            Node newNode = new Node(t, null, tail);
            //让尾部的next指向新的节点
            tail.next = newNode;
            //把尾部设置为新的节点
            tail = newNode;
        }
        size++;

    }

    @Override
    public void add(T t, int index) {
        checkIndexOutOfBound(index, size);
        if (header == null) {
            add(t);
        } else {
            if (index == 0) {
                addToHeader(t);
            } else {
                Node prevNode = getNodeByIndex(index - 1);
                prevNode.next = new Node(t, prevNode.next, prevNode);
                size++;
            }
        }
    }

    /**
     * 采用头插法 插入新节点
     *
     * @param element
     */
    public void addToHeader(T element) {
        header = new Node(element, header, null);
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
        remove(index);
        return index != -1;
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
        DuplexLinkedList<String> linkedList = new DuplexLinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i + "");
        }

        System.out.println("for index方式遍历：");
        forEach(linkedList);

        System.out.println("forEach方式遍历：");
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

        linkedList.add("1", 1);
        linkedList.add("2", 2);
        forEach(linkedList);

        linkedList.addToHeader("-1 ");
        forEach(linkedList);

        linkedList.clear();
        System.out.println("clear()后的集合大小：" + linkedList.size());
    }

    private static void forEach(List<String> linkedList) {
        for (String str : linkedList) {
            System.out.print(str);
        }
        System.out.println();
    }

}
