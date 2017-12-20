package com.chiclaim.quality151;

/**
 * 优先使用整型池
 * Created by Chiclaim on 2017/12/20.
 */
public class Case28 {

    public static void main(String[] args) {

        test(127);
        test(128);


    }

    public static void test(int tempInt) {
        System.out.println("======测试值为：" + tempInt + "======");
        Integer i = new Integer(tempInt);
        Integer j = new Integer(tempInt);

        System.out.println(" new 产生的对象：" + (i == j));

        i = tempInt;
        j = tempInt; // 会执行装箱操作，是否会产生新的对象原则和valueOf方法一致
        System.out.println(" 基本类型转换的对象：" + (i == j));


        // 通过静态方法生成一个实例
        //通过valueOf产生包装对象时，如果int参数在-128到127之间，则直接从整型池中获得对象，不在该范围内的int类型则通过new生成包装对象。
        i = Integer.valueOf(tempInt);
        j = Integer.valueOf(tempInt);
        System.out.println(" valueOf产生的对象：" + (i == j));
        System.out.println();
    }
}
