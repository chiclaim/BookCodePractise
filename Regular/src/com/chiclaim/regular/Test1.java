package com.chiclaim.regular;

import java.util.regex.Pattern;

/**
 * Created by Chiclaim on 2018/1/5.
 */
public class Test1 {

    public static void main2(String[] args) {
        String regex = "gt://(.+)/(.+)";
        boolean isOk = Pattern.compile(regex).matcher("gt://TEST-500/1").find();
        System.out.println(isOk);
    }


    public static void main(String[] args) {
        System.out.println(test());
        System.out.println(test2());
        System.out.println(test3());
    }

    public static int test() {
        int i = 0;
        try {
            return i++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return i+2;
        }
    }

    public static int test2() {
        int i = 0;
        try {
            return i++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            i = i+2;
        }
        return i;
    }


    public static int test3() {
        int i = 0;
        try {
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            i++;
        }
        return i;
    }

}
