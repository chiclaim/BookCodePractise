package com.chiclaim.quality151;

/**
 * 谨慎包装类型的大小比较
 * Created by Chiclaim on 2017/12/20.
 */
public class Case27 {

    public static void main(String[] args) {
        Integer i = new Integer(100);
        Integer j = new Integer(999);
        compare(i, j);


        int tmp = 99999;
        compare2(tmp, new Integer(tmp));
    }

    /**
     * 两个包装类型比较
     *
     * @param i
     * @param j
     */
    private static void compare(Integer i, Integer j) {
        //在java中"=="是用来判断两个操作数是否有相等关系的，如果是基本类型则判断值是否相等，如果是对象则判断是否是一个对象的两个引用，也就是地址是否相等
        System.out.println(i == j);//false
        //在Java中，">" 和 "<" 用来判断两个数字类型的大小关系，注意只能是数字类型的判断，
        // 对于Integer包装类型，是根据其intValue()方法的返回值(也就是其相应的基本类型)进行比较的(其它包装类型是根据相应的value值比较的，
        // 如doubleValue,floatValue等)
        System.out.println(i > j);
        System.out.println(i < j);
    }

    /**
     * 基本类型和包装类型比较
     *
     * @param i
     * @param j
     */
    private static void compare2(int i, Integer j) {
        System.out.println(i == j);//true   说明会进行拆箱操作
        System.out.println(i > j);
        System.out.println(i < j);
    }
}
