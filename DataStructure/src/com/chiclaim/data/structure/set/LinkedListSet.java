package com.chiclaim.data.structure.set;

import com.chiclaim.data.structure.linear.LinkedList;

public class LinkedListSet<T> implements Set<T> {

    private LinkedList<T> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(T e) {
        linkedList.addFirst(e);
    }

    @Override
    public boolean contains(T e) {
        return linkedList.contains(e);
    }

    @Override
    public void remove(T e) {
        linkedList.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int size() {
        return linkedList.size();
    }
}
