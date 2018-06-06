package com.chiclaim.data.structure.set;

import com.chiclaim.data.structure.tree.BST;

public class BSTSet<T extends Comparable<T>> implements Set<T> {

    private BST<T> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(T e) {
        bst.add(e);
    }

    @Override
    public boolean contains(T e) {
        return bst.contains(e);
    }

    @Override
    public void remove(T e) {
        bst.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public int size() {
        return bst.size();
    }
}
