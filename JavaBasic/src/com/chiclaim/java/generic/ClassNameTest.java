package com.chiclaim.java.generic;

/**
 * Class getSimpleName、getName、getCanonicalName
 * Created by Chiclaim on 2018/7/26.
 */
public class ClassNameTest {

    public static void main(String[] args) {
        System.out.println("\nPrinting OuterClass ----------------");
        System.out.println("simple name:    "+OuterClass.class.getSimpleName());
        System.out.println("name:           "+OuterClass.class.getName());
        System.out.println("canonical name: "+OuterClass.class.getCanonicalName());

        System.out.println("\nPrinting OuterClass.NonStaticInnerClass ----------------");
        System.out.println("simple name:    "+OuterClass.NonStaticInnerClass.class.getSimpleName());
        System.out.println("name:           "+OuterClass.NonStaticInnerClass.class.getName());
        System.out.println("canonical name: "+OuterClass.NonStaticInnerClass.class.getCanonicalName());

        System.out.println("\nPrinting OuterClass.StaticInnerClass ----------------");
        System.out.println("simple name:    "+OuterClass.StaticInnerClass.class.getSimpleName());
        System.out.println("name:           "+OuterClass.StaticInnerClass.class.getName());
        System.out.println("canonical name: "+OuterClass.StaticInnerClass.class.getCanonicalName());


        System.out.println("\nPrinting Anonymous Class ----------------");
        System.out.println("simple name:    "+new OuterClass.StaticInnerClass(){}.getClass().getSimpleName());
        System.out.println("name:           "+new OuterClass.StaticInnerClass(){}.getClass().getName());
        System.out.println("canonical name: "+new OuterClass.StaticInnerClass(){}.getClass().getCanonicalName());

        System.out.println("\nPrinting String[]'s Class----------------");
        System.out.println("simple name:    "+String[].class.getSimpleName());
        System.out.println("name:           "+String[].class.getName());
        System.out.println("canonical name: "+String[].class.getCanonicalName());



/*
        Printing OuterClass ----------------
        simple name:    OuterClass
        name:           com.chiclaim.java.generic.OuterClass
        canonical name: com.chiclaim.java.generic.OuterClass

        Printing OuterClass.NonStaticInnerClass ----------------
        simple name:    NonStaticInnerClass
        name:           com.chiclaim.java.generic.OuterClass$NonStaticInnerClass
        canonical name: com.chiclaim.java.generic.OuterClass.NonStaticInnerClass

        Printing OuterClass.StaticInnerClass ----------------
        simple name:    StaticInnerClass
        name:           com.chiclaim.java.generic.OuterClass$StaticInnerClass
        canonical name: com.chiclaim.java.generic.OuterClass.StaticInnerClass

        Printing Anonymous Class ----------------
        simple name:
        name:           com.chiclaim.java.generic.ClassNameTest$2
        canonical name: null

        Printing String[]'s Class----------------
        simple name:    String[]
        name:           [Ljava.lang.String;
        canonical name: java.lang.String[]

        小结：
            getName 和 getCanonicalName 在一般情况下是一样的，如果是内部类、匿名内部类、数组的情况下就会有差别，如上输出所示。
            1）getName 的值 和 动态加载类的时候输入的参数值一样，例如 Class.forName(...)
            2）getCanonicalName 和 import package 值一样，如果是匿名内部类则是 null，getCanonicalName 的值和 反编译后的 class bytecode 类似
            3）getSimpleName 就是简单的类名，不包含包名，如果是内部类不包含外部类的名字，如果是匿名内部类则是空字符串

*/

    }


}
