package com.chiclaim.data.structure.tree.rb;


import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * Created by Chiclaim on 2018/7/9.
 */
public class TestTreeMap {


    @Test
    public void test() {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        tree.put(14, 14);
        tree.put(5, 5);
        tree.put(20, 20);
        tree.put(2, 2);
        tree.put(8, 8);
        tree.put(25, 25);
        tree.put(24, 24);
        /*
        [14,5,20,2,8,25]
            14, color:Black, left=5=5 , right=20=20
            5, color:Black, left=2=2 , right=8=8
            20, color:Black, left=null , right=25=25
            2, color:RED, left=null , right=null
            8, color:RED, left=null , right=null
            25, color:RED, left=null , right=null

        [14,5,20,2,8,25,24]
            14, color:Black, left=5=5 , right=24=24
            5, color:Black, left=2=2 , right=8=8
            24, color:Black, left=20=20 , right=25=25
            2, color:RED, left=null , right=null
            8, color:RED, left=null , right=null
            20, color:RED, left=null , right=null
            25, color:RED, left=null , right=null

         */

        Class clazz = tree.getClass();
        try {
            Field fRoot = clazz.getDeclaredField("root");
            fRoot.setAccessible(true);
            Object root = fRoot.get(tree);
            System.out.println("=========层序遍历==============");
            levelorder(root);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void levelorder(Object root) throws NoSuchFieldException, IllegalAccessException {
        if (root == null)
            return;
        Deque<Object> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            Object node = queue.removeFirst();
            Class clazz = node.getClass();
            Field fValue = clazz.getDeclaredField("value");
            Field fColor = clazz.getDeclaredField("color");
            Field lf = clazz.getDeclaredField("left");
            Field rf = clazz.getDeclaredField("right");
            fValue.setAccessible(true);
            fColor.setAccessible(true);
            lf.setAccessible(true);
            rf.setAccessible(true);


            System.out.println(fValue.get(node) + ", color:" + ((boolean) (fColor.get(node)) ? "Black" : "RED") +
                    ", left=" + lf.get(node) + " , right=" + rf.get(node));


            Class clazzLeft = node.getClass();
            Field fLeft = clazzLeft.getDeclaredField("left");
            fLeft.setAccessible(true);

            if (fLeft.get(node) != null) {
                queue.addLast(fLeft.get(node));
            }


            Class clazzRight = node.getClass();
            Field fRight = clazzRight.getDeclaredField("right");
            fRight.setAccessible(true);
            if (fRight.get(node) != null) {
                queue.addLast(fRight.get(node));
            }
        }
    }


}
