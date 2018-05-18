package com.chiclaim.data.structure.linear;

import java.util.Iterator;

/**
 * 链式存储的栈
 * Created by Chiclaim on 2018/3/12.
 */
public class LinkedStack<T> implements Iterable<T> {

    //使用stackTop来记录当前栈顶的元素
    private Node stackTop;
    private int size;

    private class Node {
        T element;
        Node next;

        Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }


    public void push(T element) {
        stackTop = new Node(element, stackTop);
        size++;
    }

    public T pop() {
        Node oldTop = stackTop;
        stackTop = stackTop.next;
        //释放原栈顶元素next引用
        oldTop.next = null;
        size--;
        return oldTop.element;
    }


    public T peek() {
        return stackTop.element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        stackTop = null;
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

        private Node current = stackTop;

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
