package com.chiclaim.quality151;

import java.lang.reflect.Array;

/**
 * 动态加载不适合数组，可以通过反射工具类Array来创建
 * Created by Chiclaim on 2017/12/29.
 */
public class Case105 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String[] strs = new String[10];
        //Class.forName("java.lang.String[]");//java.lang.ClassNotFoundException

        //加载一个String数组
        Class clazz = Class.forName("[Ljava.lang.String;");
        //虽然以上代码可以加载一个数组类，但这是没有任何意义的，因为它不能产生一个数组对象
        //String[] str = (String[]) clazz.newInstance(); java.lang.InstantiationException: [Ljava.lang.String


        // 动态创建数组
        String[] strs2 = (String[]) Array.newInstance(String.class, 8);
        // 创建一个多维数组
        int[][] ints = (int[][]) Array.newInstance(int.class, 2, 3);

        System.out.println("array size = " + ints.length);

    }
}
