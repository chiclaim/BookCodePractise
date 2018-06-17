package com.chiclaim.data.structure.tree.uf;

import java.util.Random;

public class Main {

    private static double test(UF uf, int m) {

        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            int p = random.nextInt(uf.size());
            int q = random.nextInt(uf.size());
            uf.unionElements(p, q);
        }

        for (int i = 0; i < m; i++) {
            int p = random.nextInt(uf.size());
            int q = random.nextInt(uf.size());
            uf.isConnected(p, q);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int p = 10000000; //操作次数
        //double countTime1 = test(new UnionFind1(size), p);
        //double countTime2 = test(new UnionFind2(size), p);
        double countTime3 = test(new UnionFind3(size), p);
        double countTime4 = test(new UnionFind4(size), p);
        double countTime5 = test(new UnionFind5(size), p);
        //System.out.println("UnionFind1 = " + countTime1);
        //System.out.println("UnionFind2 = " + countTime2);
        System.out.println("UnionFind3 = " + countTime3);
        System.out.println("UnionFind4 = " + countTime4);
        System.out.println("UnionFind5 = " + countTime5);
    }

/*

int size = 100000;
int p = 10000;
UnionFind1 = 0.546094344
UnionFind2 = 0.00757277

----------------------------
//十万级别
int size = 100000;
int p = 100000;  //操作次数扩大10倍
UnionFind1 = 8.099477928
UnionFind2 = 13.21936231

----------------------------
//十万级别
int size = 100000;
int p = 100000;
UnionFind1 = 7.707820549
UnionFind2 = 12.475811439
UnionFind3 = 0.036647197    //基于size的优化

----------------------------
//十万级别
int size = 100000;
int p = 100000;
UnionFind1 = 7.573064321
UnionFind2 = 12.962463725
UnionFind3 = 0.029143278   //基于size的优化
UnionFind4 = 0.022382609   //基于rank的优化

----------------------------
//千万级别
int size = 10000000;
int p = 10000000;
UnionFind3 = 5.023308892   //基于size的优化
UnionFind4 = 4.741167168   //基于rank的优化

----------------------------
//千万级别
int size = 10000000;
int p = 10000000;
UnionFind3 = 5.16725354   //基于size的优化
UnionFind4 = 5.148308358  //基于rank的优化
UnionFind5 = 4.526459366  //基于路径压缩的优化
 */
}
