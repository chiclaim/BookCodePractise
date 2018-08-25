package com.chiclaim.data.structure.tree.rb;

import com.chiclaim.data.structure.set.FileOperation;
import com.chiclaim.data.structure.tree.AVLTree;
import com.chiclaim.data.structure.tree.map.BSTMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 只测试插入操作的效率（数据集是随机的）
 */
public class Main2 {

    public static void main(String[] args) {

        int n = 10000000;

        Random random = new Random(n);
        ArrayList<Integer> testData = new ArrayList<>(n);
        for(int i = 0 ; i < n ; i ++)
            testData.add(random.nextInt(Integer.MAX_VALUE));

        // Test BST
        long startTime = System.nanoTime();

        BSTMap<Integer, Integer> bst = new BSTMap<>();
        for (Integer x: testData)
            bst.add(x, null);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time + " s");


        // Test AVL
        startTime = System.nanoTime();

        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer x: testData)
            avl.add(x, null);

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");


        // Test RBTree
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (Integer x: testData)
            rbt.add(x, null);

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time + " s");

        //BST: 24.181656802 s
        //AVL: 24.024245311 s
        //RBTree: 20.860736357 s
        //
        //BST: 24.416861213 s
        //AVL: 24.410043582 s
        //RBTree: 23.885474752 s

    }
}
