package com.chiclaim.quality151;

import java.util.List;

/**
 * 不同的场景使用不同的泛型通配符
 * <p>
 * 泛型结构只参与“写” 操作则限定下界(使用super关键字)
 * <p>
 * 泛型结构只参与 “读” 操作则限定上界(extends关键字)
 * <p>
 * 如果一个泛型结构既用作 “读” 操作又用作“写操作”，那该如何进行限定呢？不限定，使用确定的泛型类型即可，如List<E>
 * <p>
 * Created by Chiclaim on 2017/12/28.
 */
public class Case96 {



    //JDK的Collections.copy方法是一个非常好的例子...


    static <E> void read(List<? super E> list) {
        for (Object obj : list) {
            // 业务逻辑操作
            //业务无法继续写下去
            //需要使用extends关键字界定泛型的上界

        }
    }

    //使用extends关键字界定泛型的上界
    static <E> void read2(List<? extends E> list) {
        for (E e : list) {
            // 业务逻辑操作
        }
    }


    static <E> void write(List<? extends Number> list) {
        //加入一个元素
        //list.add(123);
        //编译失败，失败的原因是list中的元素类型不确定，
        // 也就是编译器无法推断出泛型类型到底是什么，是Integer类型？是Double？还是Byte？这些都符合extends关键字的定义，
        // 由于无法确定实际的泛型类型，所以编译器拒绝了此类操作

    }

    static <E> void write2(List<? super Number> list) {
        //加入元素
        list.add(123);
        list.add(3.14);
        //在这种“写”的操作的情况下，使用super关键字限定泛型的下界
    }
}
