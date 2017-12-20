package com.chiclaim.quality151;

/**
 * 静态变量一定要先声明后赋值
 * Created by Chiclaim on 2017/12/20.
 */
public class Case32 {
    public static void main(String[] args) {
        System.out.println(A.i);
        System.out.println(B.i);
    }


    /**
     * 先声明后赋值
     */
    public static class A {
        public static int i = 1;

        static {
            i = 100;
        }
    }

    /**
     * 先赋值后声明
     */
    public static class B {
        static {
            i = 100;
        }

        public static int i = 1;
    }


}
