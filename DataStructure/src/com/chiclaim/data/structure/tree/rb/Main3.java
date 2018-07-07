package com.chiclaim.data.structure.tree.rb;

import com.chiclaim.data.structure.tree.AVLTree;
import com.chiclaim.data.structure.tree.map.BSTMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 只测试插入操作的效率（数据集是有序的）
 */
public class Main3 {

    public static void main(String[] args) {
        int count = 10000000;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            data.add(i);
        }

//        long startTime = System.nanoTime();
//        BSTMap<Integer, Void> bstMap = new BSTMap<>();
//        for (int value : data) {
//            bstMap.add(value, null);
//        }
//        long endTime = System.nanoTime();
//        System.out.println("BST : " + (endTime - startTime) / 100000000.0);

        long startTime = System.nanoTime();
        AVLTree<Integer, Void> avlTree = new AVLTree<>();
        for (int value : data) {
            avlTree.add(value, null);
        }
        long endTime = System.nanoTime();
        System.out.println("AVL : " + (endTime - startTime) / 1000000000.0);


        startTime = System.nanoTime();
        RBTree<Integer, Void> rbTree = new RBTree<>();
        for (int value : data) {
            rbTree.add(value, null);
        }
        endTime = System.nanoTime();
        System.out.println("RB  : " + (endTime - startTime) / 1000000000.0);


        //AVL : 25.245994076
        //RB  : 20.945959735
        //有序数据集插入操作红黑树性能高于AVL树
    }
}
