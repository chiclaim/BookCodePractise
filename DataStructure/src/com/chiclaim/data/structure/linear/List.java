package com.chiclaim.data.structure.linear;

/**
 * Created by Chiclaim on 2018/3/7.
 */
public interface List<T> extends Iterable<T>{
    void add(T t);

    void add(T t, int index);

    T get(int index);

    int indexOf(T t);

    boolean remove(T t);

    T remove(int index);

    void clear();

    int size();
}
