package com.chiclaim.data.structure.set;

import com.chiclaim.data.structure.tree.trie.Trie;

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
    public void remove(String e) {
        trie.remove(e);
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
