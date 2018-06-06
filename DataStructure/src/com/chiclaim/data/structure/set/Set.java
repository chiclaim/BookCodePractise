package com.chiclaim.data.structure.set;

public interface Set<T> {

    void add(T e);

    boolean contains(T e);

    void remove(T e);

    boolean isEmpty();

    int size();

}
