package com.chiclaim.quality151;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 非稳定排序推荐使用List
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
        set.first().setHeight(185);//书上运行的结果是[175,、180]，我运行的结果是[185、180]，不管怎么样都没从新排序

        //1，set重排序
        //set=new TreeSet<>(new ArrayList<Person>(set));

        //2，或者List的compare排序
        //ignore code

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
