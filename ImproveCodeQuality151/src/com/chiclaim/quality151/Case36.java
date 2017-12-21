package com.chiclaim.quality151;

/**
 * Created by Chiclaim on 2017/12/20.
 */
public class Case36 {

    public static void main(String[] args) {
        new Case36();
        new Case36("name");
    }


    {
        // 构造代码块
        System.out.println("执行构造代码块");
    }

    public Case36() {
        System.out.println("执行无参构造");
    }

    public Case36(String name) {
        System.out.println("执行有参构造");
    }
}
