package com.chiclaim.algorithm.recurse;

public class Recurse {


    public int factorial(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }

    public int factorial2(int num, int result) {
        if (num == 1) {
            return result;
        }
        return factorial2(num - 1, num * result);
    }

    public int sum(int n) {
        if (n == 1)
            return 1;
        return n + sum(n - 1);
    }

    public int sum2(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += i;
        }
        return count;
    }


    public static void main(String[] args) {
        Recurse recurse = new Recurse();
        int num = 7;
        System.out.println(recurse.factorial2(num, 1));
        System.out.println(recurse.factorial(num));
        System.out.println(recurse.sum(10));
        System.out.println(recurse.sum2(10));
    }


}
