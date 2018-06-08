package com.chiclaim.data.structure.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * https://stackoverflow.com/questions/18648521/how-is-this-hashset-producing-sorted-output
 * Created by Chiclaim on 2018/6/8.
 */
public class TestJavaSet {

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(0);
        treeSet.add(100);
        treeSet.add(-10);
        System.out.println("last:" + treeSet.last());
        System.out.println("first:" + treeSet.first());
        System.out.println("treeSet--------traversal--------");
        for (int i : treeSet) {
            System.out.println(i);
        }
        testHahSet();
        testHahSet2();

        testCopySet();
    }

    private static void testCopySet() {
        System.out.println("build hashSet");
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(10);
        hashSet.add(9);
        hashSet.add(11);
        hashSet.add(-19);
        printSet(hashSet);
        LinkedHashSet linkedHashSet = new LinkedHashSet<>(hashSet);
        System.out.println("after construct");
        printSet(linkedHashSet);
    }

    private static void testHahSet() {
        // TODO: 2018/6/8 为什么是有序的
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(10);
        hashSet.add(9);
        hashSet.add(-19);
        hashSet.add(11);

        System.out.println("hashSet--------traversal--------");
        printSet(hashSet);
    }

    private static void testHahSet2() {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(10);
        hashSet.add(9);
        hashSet.add(1);
        hashSet.add(-19);
        hashSet.add(13);
        System.out.println("hashSet--------traversal--------");
        printSet(hashSet);
    }

    private static void printSet(java.util.Set set) {
        for (Object obj : set) {
            System.out.println(obj);
        }
    }
}
