package com.chiclaim.data.structure;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 顺序存储的栈
 * Created by Chiclaim on 2018/3/12.
 */
public class SequenceStack<T> implements Iterable<T> {

    private static final int DEFAULT_SIZE = 10;
    //数组的长度
    private int capacity;
    //定义当底层数据容量不够时，每次增加的数组长度
    private int capacityIncrement;
    //用于保存顺序栈的元素
    private Object[] elementData;
    //元素的个数
    private int size;


    public SequenceStack() {
        this.capacity = DEFAULT_SIZE;
        elementData = new Object[this.capacity];
    }

    public SequenceStack(T element) {
        this();
        elementData[0] = element;
        size++;
    }


    public SequenceStack(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[this.capacity];
        elementData[0] = element;
        size++;
    }

    public SequenceStack(T element, int initSize, int capacityIncrement) {
        this(element, initSize);
        this.capacityIncrement = capacityIncrement;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            if (capacityIncrement > 0) {
                while (capacity < minCapacity) {
                    capacity += capacityIncrement;
                }
            } else {
                //不断地降capacity * 2,知道capacity大于minCapacity
                while (capacity < minCapacity) {
                    capacity <<= 1;
                }
            }
        }
    }


    public int size() {
        return size;
    }

    public void push(T element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }

    /**
     * 返回栈顶元素，并且删除该元素
     *
     * @return
     */
    public T pop() {
        T element = (T) elementData[size - 1];
        elementData[--size] = null;
        return element;
    }

    /**
     * 只返回栈顶元素，但不删除该元素
     *
     * @return
     */
    public T peek() {
        return (T) elementData[size - 1];
    }

    /**
     * 是否是空栈
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空栈内元素
     */
    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {

        private int index = size - 1;

        public boolean hasNext() {
            return index >= 0;
        }

        public T next() {
            return (T) elementData[index--];
        }
    }

    public static void main(String[] args) {

        SequenceStack<String> stack = new SequenceStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i + "");
        }

        System.out.println("遍历栈：");
        for (String str : stack) {
            System.out.println(str);
        }
        System.out.println("栈大小：" + stack.size());
        System.out.println("弹出栈顶元素：" + stack.pop());
        for (String str : stack) {
            System.out.println(str);
        }
        System.out.println("栈大小：" + stack.size());

        System.out.println("Peek栈顶元素：" + stack.peek());

        stack.clear();
        System.out.println("栈大小：" + stack.size());

    }


}
