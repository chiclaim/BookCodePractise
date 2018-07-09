package com.chiclaim.data.structure.set;

import com.chiclaim.data.structure.tree.Trie;

public class TrieSet implements Set<String> {

    private Trie trie;

    public TrieSet() {
        this.trie = new Trie();
    }

    @Override
    public void add(String e) {
        trie.add(e);
    }

    @Override
    public boolean contains(String e) {
        return trie.contains(e);
    }

    @Override
    public boolean remove(String e) {
        return trie.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return trie.isEmpty();
    }

    @Override
    public int size() {
        return trie.size();
    }
}
