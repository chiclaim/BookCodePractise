package com.chiclaim.quality151;

import java.lang.reflect.Method;

/**
 * 适时选择getDeclaredXXX和getXXX
 * Created by Chiclaim on 2017/12/29.
 */
public class Case102 {


    public static void main(String[] args) throws NoSuchMethodException,
            SecurityException {
        // 方法名称
        String methodName = "doStuff";

        //getDeclaredMethod获得的是自身类的方法，包括公用的(public)方法、私有(private)方法，而且不受限于访问权限
        //Method m1 = Foo.class.getDeclaredMethod(methodName);

        //getMethod方法获得的是所有public访问级别的方法，包括从父类继承的方法
        Method m2 = Foo.class.getMethod(methodName);


        //其它的getDeclaredConstructors和getConstructors、getDeclaredFileds和getFields等于此相似
    }

    static class Parent {
        /*public*/ void doStuff() {
        }
    }

    //静态内部类
    static class Foo extends Parent {

    }

}
