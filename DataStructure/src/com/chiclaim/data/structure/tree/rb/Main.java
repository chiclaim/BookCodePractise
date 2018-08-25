package com.chiclaim.data.structure.tree.rb;

import com.chiclaim.data.structure.set.FileOperation;
import com.chiclaim.data.structure.tree.AVLTree;
import com.chiclaim.data.structure.tree.map.BSTMap;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            long startTime = System.nanoTime();
            RBTree<String, Integer> rbTree = new RBTree<>();
            for (String word : words) {
                if (rbTree.contains(word))
                    rbTree.add(word, rbTree.get(word) + 1);
                else
                    rbTree.add(word, 1);
            }

            long endTime = System.nanoTime();
            System.out.println("RB  Tree: " + (endTime - startTime) / 1000000000.0);

            startTime = System.nanoTime();
            AVLTree<String, Integer> avlTree = new AVLTree<>();
            for (String word : words) {
                if (avlTree.contains(word))
                    avlTree.add(word, rbTree.get(word) + 1);
                else
                    avlTree.add(word, 1);
            }

            endTime = System.nanoTime();
            System.out.println("AVL Tree: " + (endTime - startTime) / 1000000000.0);

            startTime = System.nanoTime();
            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.add(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            endTime = System.nanoTime();
            System.out.println("BST  Map: " + (endTime - startTime) / 1000000000.0);

//红黑树的查询并不比AVL效率高，红黑树的效率在于插入和删除。总和插入、删除和查询总体比AVL高一些
//            Total words: 125901
//            RB  Tree: 0.171678822
//            AVL Tree: 0.160687733
//            BST  Map: 0.092961743

        }
    }
}
