package com.chiclaim.regular;

import java.util.regex.Pattern;

/**
 * Created by Chiclaim on 2018/1/5.
 */
public class Test1 {

    public static void main(String[] args) {
        String regex = "gt://(.+)/(.+)";
        boolean isOk = Pattern.compile(regex).matcher("gt://TEST-500/1").find();
        System.out.println(isOk);
    }
}
