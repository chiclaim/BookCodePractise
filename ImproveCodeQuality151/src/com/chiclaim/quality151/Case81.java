package com.chiclaim.quality151;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Chiclaim on 2017/12/28.
 */
public class Case81 {

    public static void main(String[] args) {
        SortedSet<Person> set = new TreeSet<Person>();
        // 身高180CM
        set.add(new Person(180));
        // 身高175CM
        set.add(new Person(175));

        //修改身高
        set.first().setHeight(185);//我测试运行是生效的，书上上说不生效，可能JDK版本不一致的原因


        for (Person p : set) {
            System.out.println("身高：" + p.getHeight());
        }
    }

    static class Person implements Comparable<Person> {
        // 身高
        private int height;

        public Person(int _height) {
            height = _height;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        // 按照身高排序
        @Override
        public int compareTo(Person o) {
            return height - o.height;
        }
    }
}
