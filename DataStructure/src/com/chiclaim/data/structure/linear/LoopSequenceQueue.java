package com.chiclaim.data.structure.linear;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 循环队列（顺序存储）
 * Created by Chiclaim on 2018/3/12.
 */
public class LoopSequenceQueue<T> implements Iterable<T> {

    private static final int DEFAULT_SIZE = 10;

    private int capacity;

    //记录队列前端元素的索引
    private int front;

    //记录队列尾端元素的索引
    private int rear;

    //用于存储队列里的元素
    private Object[] elementData;

    public LoopSequenceQueue() {
        this.capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public LoopSequenceQueue(T element) {
        this(element, DEFAULT_SIZE);
    }


    public LoopSequenceQueue(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[rear++] = element;
    }

    public int size() {
        return rear - front;
    }

    public boolean isEmpty() {
        //环形队列"假满"的时候rear = front，所以还需要判断数组的第一个元素是否为空
        //但是也有可能开发者放进去的第一个元素就是null，其他的位置不是null，这个时候就会有问题
        //可以在插入的时候,判断是否为null,为null则抛出异常, Java中的Queue队列如果插入null就会抛出空指针异常
        //所以我们也可以这样处理
        return rear == front && elementData[rear] == null;
    }


    public void add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (rear > capacity - 1) {
            throw new IndexOutOfBoundsException("队列已满：" + capacity);
        }
        elementData[rear++] = element;
        //如果rear已经到头，那就转头
        rear = rear == capacity ? 0 : rear;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        T removedElement = (T) elementData[front];
        elementData[front++] = null;
        //如果front已经到头，那就转头
        front = front == capacity ? 0 : front;
        return removedElement;
    }


    public T get() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        return (T) elementData[front];
    }

    public void clear() {
        Arrays.fill(elementData, null);
        front = 0;
        rear = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        if (front < rear) {
            for (int i = front; i < rear; i++) {
                builder.append(elementData[i]).append(',');
            }
            builder.deleteCharAt(builder.length() - 1);
        } else { //front>=rear
            //有效元素为front->capacity之间
            for (int i = front; i < capacity; i++) {
                builder.append(elementData[i]).append(',');
            }
            //环形队列可能，rear超过了capacity，可能rear>=1, 所以还有一部分有效元素是0->rear之间
            for (int i = 0; i < rear; i++) {
                builder.append(elementData[i]).append(',');
            }
            builder.deleteCharAt(builder.length() - 1);
        }


        builder.append(']');
        builder.append(" (rear=").append(rear).append(",front=").append(front).append(")");
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        int index = front;

        @Override
        public boolean hasNext() {
            return index < rear;
        }

        @Override
        public T next() {
            return (T) elementData[index++];
        }

    }

    public static void main(String[] args) {
        LoopSequenceQueue<String> queue = new LoopSequenceQueue<>("aaa", 3);
        queue.add("bbb");
        queue.add("ccc");
        System.out.println(queue);

        //模拟front>rear
        queue.remove();
        System.out.println(queue);

        //模拟rear=front
        queue.add("ddd");
        System.out.println(queue);
    }


}
