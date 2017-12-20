package com.chiclaim.quality151;

import java.util.Random;

/**
 * 不要随便设置随机种子
 * Created by Chiclaim on 2017/12/20.
 */
public class Case30 {
    public static void main(String[] args) {
        //Random r = new Random();//不设置随机种子，无论运行程序几次，生成的随机都不会相同
        Random r = new Random(1000);//设置随机种子，每次运行程序，生成的随机数和上一次运行的结果相同
        for (int i = 1; i <= 4; i++) {
            System.out.println("第" + i + "次:" + r.nextInt());
        }
    }
}
