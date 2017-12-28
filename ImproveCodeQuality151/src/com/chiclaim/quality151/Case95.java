package com.chiclaim.quality151;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 强制声明泛型的实际类型
 * Created by Chiclaim on 2017/12/28.
 */
public class Case95 {

    public static void main(String[] args) {
        // 正常用法
        List<String> list1 = ArrayUtils.asList("A", "B");

        // 参数为空
        List list2 = ArrayUtils.asList();

        // 参数为空
        List<Integer> list4 = ArrayUtils.asList();
        list4.add(1);

        //强制声明泛型类型
        List<String> intList = ArrayUtils.<String>asList();

        // 参数为整型和浮点型的混合
        List<Number> list3 = ArrayUtils.asList(1, 2, 3.1);


        //一句话：无法从代码中推断出泛型的情况下，即可强制声明泛型类型。
    }

}

class ArrayUtils {
    // 把一个变长参数转化为列表，并且长度可变
    public static <T> List<T> asList(T... t) {
        List<T> list = new ArrayList<T>();
        Collections.addAll(list, t);
        return list;
    }
}
