package com.chiclaim.data.structure.linear;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 实现一个简易的线性表（顺序存储）
 * Created by Chiclaim on 2018/3/5.
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 16;

    private Object[] array;

    private int capacity;

    private int size;


    public ArrayList() {
        capacity = DEFAULT_SIZE;
        array = new Object[capacity];
    }

    public ArrayList(int size) {
        capacity = 1;
        //把capacity设置为大于size的最小的2的n次方
        while (capacity < size) {
            capacity <<= 1;
        }
        array = new Object[capacity];
    }

    /**
     * 扩容
     *
     * @param expectCapacity
     */
    private void ensureSize(int expectCapacity) {
        //如果当前的容量小于期望的容量
        if (expectCapacity > capacity) {
            //不断的将capacity * 2，直到capacity大于expectCapacity
            while (capacity < expectCapacity) {
                capacity <<= 1;
            }
            array = Arrays.copyOf(array, capacity);
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
     * 向最后一个位置添加元素
     *
     * @param t
     */
    @Override
    public void add(T t) {
        add(size, t);
    }

    /**
     * 在某个特定的位置添加元素
     *
     * @param t
     * @param index
     */
    @Override
    public void add(int index, T t) {
        checkIndexOutOfBound(index, size);
        ensureSize(capacity + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = t;
        size++;
    }

    /**
     * 获取某个位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        checkIndexOutOfBound(index, size - 1);
        return (T) array[index];
    }

    /**
     * 获取某个元素的位置
     *
     * @param t
     * @return
     */
    @Override
    public int indexOf(T t) {
        for (int i = 0; i < size; i++) {
            Object obj = array[i];
            if (obj == null && t == null) {
                return i;
            }
            if (obj != null && obj.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除某个元素
     *
     * @param t
     * @return
     */
    @Override
    public boolean remove(T t) {
        int index = indexOf(t);
        if (index != -1) {
            remove(index);
        }
        return index != -1;
    }

    /**
     * 删除某个位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        checkIndexOutOfBound(index, size - 1);
        T oldValue = (T) array[index];
        int copySize = size - index - 1;
        //如果是删除最后一个元素则不需要copy
        //或者 index = size - 1
        if (copySize > 0) {
            System.arraycopy(array, index + 1, array, index, copySize);
        }
        //index位置的元素向左移动，则需要把最后一个元素置为null，避免内存泄漏
        array[--size] = null;
        return oldValue;
    }

    /**
     * 清空所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(T t) {
        return indexOf(t) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        private int index = 0;

        public boolean hasNext() {
            return index < size();
        }

        public T next() {
            return get(index++);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(7);
        //添加元素
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        System.out.println("遍历元素");
        //默认不能使用foreach，会遍历整个数组的容量。应该是遍历到size为止
        //需要实现iterable
        for (Object t : list) {
            System.out.print(t + ",");
        }
        System.out.println();
        list.remove(0);
        list.remove("1");
        print(list);

        list.add(0, "0");
        list.add(1, "1");
        print(list);
        list.add(10, "10");
        print(list);

        System.out.println(list.get(9));

        list.clear();

        print(list);

    }

    private static void print(List<String> list) {
        System.out.println(list);
    }
}
