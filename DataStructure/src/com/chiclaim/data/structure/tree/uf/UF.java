package com.chiclaim.data.structure.tree.uf;

public interface UF {

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);

    int size();
}
