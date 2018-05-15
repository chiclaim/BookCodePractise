package com.chiclaim.data.structure.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 实现一个简易的线性表（链式存储）
 * <p>
 * 需要注意的是remove的时候，一定要把remove的节点的next引用解除掉
 * <p>
 * Created by Chiclaim on 2018/3/7.
 */
public class LinkedList<T> implements List<T> {

    private int size;

    private Node head;
    private Node tail;

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {

        private Node current = head;

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
        if (head == null) {
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
        if (head == null) {
            //首尾都指向新的节点
            tail = head = new Node(t, null);
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
        head = new Node(element, head);
        if (tail == null) {
            tail = head;
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
        Node current = head;
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
        Node current = head;
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

    /**
     * 删除尾节点
     *
     * @return element
     */
    public T removeLast() {
        Node delete = tail;
        if (delete == null) {
            throw new NoSuchElementException();
        }
        //如果当前只有一个节点
        if (delete == head) {
            head = tail = null;
        } else {
            //因为是单向链表，无法直接获取最后节点的上一个节点
            Node pre = getNodeByIndex(size - 2);
            //解除引用
            pre.next = null;
            //重新设置tail节点
            tail = pre;
        }
        size--;
        return delete.element;
    }

    /**
     * 删除头节点
     *
     * @return element
     */
    public T removeFirst() {
        Node delete = head;
        if (delete == null) {
            throw new NoSuchElementException();
        }
        //如果当前只有一个节点
        if (delete == tail) {
            head = tail = null;
        } else {
            Node next = delete.next;
            //解除引用
            delete.next = null;
            //重新设置header节点
            head = next;
        }
        size--;
        return delete.element;
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
            delete = head;
            head = head.next;
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
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("head [");
        Node current = head;
        while (current != null) {
            builder.append(current.element).append("->");
            current = current.next;
        }
        builder.append("null] tail");
        return builder.toString();
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

        System.out.println();

        LinkedList<String> linkedList2 = new LinkedList<>();
        linkedList2.add("A");//只有一个元素的情况，移除尾节点元素
        linkedList2.removeLast();
        System.out.println(linkedList2);

        linkedList2.add("A");//只有一个元素的情况，移除头结点元素
        linkedList2.removeFirst();
        System.out.println(linkedList2);

    }

    private static void forEach(List<String> linkedList) {
        for (String str : linkedList) {
            System.out.print(str);
        }
        System.out.println();
    }

}
