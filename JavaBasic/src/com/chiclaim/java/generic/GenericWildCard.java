package com.chiclaim.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符
 * <p>
 * https://www.youtube.com/watch?v=V1vQf4qyMXg&feature=youtu.be&t=22m24s
 * <p>
 * https://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java
 * <p>
 * Created by Chiclaim on 2018/6/11.
 */
public class GenericWildCard {


    public static void main(String[] args) {
        testSuper();


        List<Integer> dest = new ArrayList<>();
        List<Integer> src = new ArrayList<>();

    }


    public static void testExtends() {
        //List<Number> list = new ArrayList<Number>();

        //Integer integer = 1;
        //list. add(integer);// illegal
        //You can't add any object to List<? extends T>

        //-----------------------------
        List<Integer> list2 = new ArrayList<>();
        inputExtends(list2);

        List<Double> list3 = new ArrayList<>();
        inputExtends(list3);

    }

    public static void testSuper() {

        List<Integer> list2 = new ArrayList<>();
        //inputSuper(list2);//illegal

        List<? super Number> list = new ArrayList<>();
        list.add(1);
        list.add(1.0f);
        int i = 0;
        list.add(i);
        //list.add("");
        for (Object num : list) {
            System.out.println(num);
        }
    }


    public static void inputSuper(List<? super Number> numbers) {

    }


    public static void inputExtends(List<? extends Number> numbers) {

    }

    //"Producer Extends, Consumer Super".   PECS
    private static void copy(List<? super Number> dest, List<? extends Number> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
            //dest.add(src.get(i));
        }
    }


}
