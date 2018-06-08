package com.chiclaim.data.structure.set;

import java.util.ArrayList;

/**
 * 二分搜索树实现的Set和链表实现的Set性能对比
 * <p>
 * Created by Chiclaim on 2018/6/8.
 */
public class Main {


    private static double countTime(Set<String> set, String filename) {
        long start = System.nanoTime();
        System.out.println(filename);
        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile(filename, words1)) {
            System.out.println("\tTotal words: " + words1.size());

            for (String word : words1)
                set.add(word);
            System.out.println("\tTotal different words: " + set.size());
        }

        long end = System.nanoTime();

        return (end - start) / 1000000000.0;
    }


    public static void main(String[] args) {
        String filename = "pride-and-prejudice.txt";
        double bstTime = countTime(new BSTSet<>(), filename);

        System.out.println();

        double linkedListTime = countTime(new LinkedListSet<>(), filename);

        System.out.println();

        System.out.println("BSTSet        Time: " + bstTime);
        System.out.println("LinkedListSet Time: " + linkedListTime);

    }


}
