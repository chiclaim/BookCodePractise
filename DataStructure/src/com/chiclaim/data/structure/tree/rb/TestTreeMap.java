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
        tree.put(3, 3);
        tree.put(2, 2);
        tree.put(7, 7);
//        tree.put(10,10);
//        tree.put(9,9);

        Class clazz = tree.getClass();
        try {
            Field fRoot = clazz.getDeclaredField("root");
            fRoot.setAccessible(true);
            Object root = fRoot.get(tree);
            System.out.println(root);
            System.out.println("=========层序遍历==============");
            levelorder(root);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
