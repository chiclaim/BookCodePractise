package com.chiclaim.data.structure.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 实现一个简易的线性表（双向链表）
 * Created by Chiclaim on 2018/3/12.
 */
public class DuplexLinkedList<T> implements List<T> {

    private int size;

    private Node head;
    private Node tail;

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

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
        T element;
        Node prev;
        Node next;

        Node(T element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return element + "";
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
                Node prevNode = getNodeFast(index - 1);
                prevNode.next = new Node(t, prevNode.next, prevNode);
                size++;
            }
        }
    }

    public void addLast(T t) {
        //空链表
        if (head == null) {
            //首尾都指向新的节点
            tail = head = new Node(t, null, null);
        } else {
            Node newNode = new Node(t, null, tail);
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
        head = new Node(element, head, null);
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
    private Node getNode(int index) {
        checkIndexOutOfBound(index, size - 1);
        Node current = head;
        for (int i = 0; i < size; i++, current = current.next) {
            if (index == i) {
                return current;
            }
        }
        return null;
    }


    /**
     * 如果需要查找的index节点在链表的后半部分，则从后往前遍历，否则按照顺序遍历
     *
     * @param index
     * @return
     */
    private Node getNodeFast(int index) {
        checkIndexOutOfBound(index, size - 1);
        if (index > size / 2) {
            Node current = tail;
            for (int i = size - 1; i >= 0; i--, current = current.prev) {
                if (index == i) {
                    return current;
                }
            }
        } else {
            //从头节点向尾节点方向遍历
            return getNode(index);
        }
        return null;
    }

    @Override
    public T get(int index) {
        Node node = getNodeFast(index);
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

    @Override
    public boolean remove(T t) {
        int index = indexOf(t);
        if (index == -1) {
            return false;
        }
        remove(index);
        return false;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBound(index, size - 1);
        Node delete;
        //如果删除的是头部
        if (index == 0) {
            return removeFirst();
        } else {
            delete = getNodeFast(index);
            Node pre = delete.prev;
            Node next = delete.next;
            pre.next = next;
            if (next != null) {
                next.prev = pre;
            } else {
                tail = pre;
            }
            delete.next = null;
            delete.prev = null;
        }
        size--;
        return delete.element;
    }

    /**
     * 删除头结点
     *
     * @return
     */
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node delete = head;
        if (head == tail) {
            head = tail = null;
        } else {
            Node next = delete.next;
            next.prev = null;
            delete.next = null;
            head = next;
        }
        size--;
        return delete.element;
    }


    /**
     * 删除尾节点
     *
     * @return
     */
    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        Node delete = tail;
        //如果只有一个元素
        if (head == tail) {
            head = tail = null;
        } else {
            Node pre = delete.prev;
            pre.next = null;
            delete.prev = null;
            tail = pre;
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

    @Override
    public int size() {
        return size;
    }


    public static void main(String[] args) {
        DuplexLinkedList<String> linkedList = new DuplexLinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i + "");
        }

        System.out.println("foreach方式遍历：");
        forEach(linkedList);

        System.out.println("for index 方式遍历：");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print(linkedList.get(i) + "->");
        }
        System.out.println();

        System.out.println("集合大小：" + linkedList.size());

        //删除元素1
        linkedList.remove("1");
        System.out.println("remove('1'):" + linkedList);

        //删除index=1的元素
        linkedList.remove(1);
        System.out.println("remove(1):" + linkedList);
        System.out.println("集合大小：" + linkedList.size());

        System.out.println("元素为9的索引：" + linkedList.indexOf("9"));

        linkedList.add(1, "1");
        linkedList.add(2, "2");
        System.out.println(linkedList);

        linkedList.addFirst("-1 ");
        System.out.println(linkedList);

        linkedList.clear();
        System.out.println("clear()后的集合大小：" + linkedList.size());

        DuplexLinkedList<String> linkedList2 = new DuplexLinkedList<>();
        linkedList2.add("A");
        linkedList2.add("B");
        System.out.println("linkedList2:" + linkedList2);
        linkedList2.removeFirst();
        System.out.println("removeFirst():" + linkedList2);

        linkedList2.addFirst("A");
        System.out.println("addFirst(A):" + linkedList2);
        linkedList2.removeFirst();
        linkedList2.removeLast();
        for (int i = 0; i < 10; i++) {
            linkedList2.add(i + "");
        }
        System.out.println(linkedList2);
        System.out.println("getNodeFast(8):" + linkedList2.getNodeFast(8));
    }

    private static void forEach(DuplexLinkedList<String> linkedList) {
        for (String value : linkedList) {
            System.out.print(value + "->");
        }
        System.out.println();
    }

}
