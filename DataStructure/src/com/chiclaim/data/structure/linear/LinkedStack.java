package com.chiclaim.data.structure.linear;

import java.util.Iterator;

/**
 * 链式存储的栈
 * Created by Chiclaim on 2018/3/12.
 */
public class LinkedStack<T> implements Iterable<T> {

    private Node top;

    private int size;

    class Node {
        private T element;
        private Node next;

        Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }


    public void push(T element) {
        top = new Node(element, top);
        size++;
    }

    public T pop() {
        Node oldTop = top;
        top = top.next;
        //释放原栈顶元素next引用
        oldTop.next = null;
        size--;
        return oldTop.element;
    }


    public T peek() {
        return top.element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    //注意：
    //1，每次forEach都会new一个Iterator
    //2，iterator方法执行顺序：hasNext()->next()
    private class MyIterator implements Iterator<T> {

        private Node current = top;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T element = current.element;
            current = current.next;
            return element;
        }
    }

    public static void main(String[] args) {

        LinkedStack<String> stack = new LinkedStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i + "");
        }

        System.out.println("遍历栈：");
        for (String str : stack) {
            System.out.println(str);
        }
        System.out.println("栈大小：" + stack.size());
        System.out.println("Peek栈顶元素：" + stack.peek());
        System.out.println("弹出栈顶元素：" + stack.pop());
        for (String str : stack) {
            System.out.println(str);
        }
        System.out.println("栈大小：" + stack.size());


        stack.clear();
        System.out.println("clear后，栈大小：" + stack.size());

    }


}
