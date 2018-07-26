package com.chiclaim.java.generic;

/**
 * 主要用于从byteCode的角度分析非静态对外部类的引用情况
 * Created by Chiclaim on 2018/7/18.
 */
public class OuterClass {

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {

        }
    };

    class NonStaticInnerClass {

        public NonStaticInnerClass(String nonStaticClass) {
            System.out.println("---");
        }

        private void test() {

        }
    }


    private static class StaticInnerClass {

        public StaticInnerClass(String staticClass) {

        }

        private void test() {

        }
    }


}
