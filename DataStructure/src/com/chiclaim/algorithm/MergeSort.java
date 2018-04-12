package com.chiclaim.algorithm;

import java.util.Arrays;

/**
 * 归并排序
 * Created by Chiclaim on 2018/4/8.
 */
public class MergeSort {


    /**
     * 将两个数组进行归并，归并前两个数组已经有序，归并后依然有序
     *
     * @param data   数据
     * @param left   左数组的第一个元素的索引
     * @param center 左数组的最后一个元素的索引，center+1是右数组的第一个元素的索引
     * @param right  右数组的最后一个元素的索引
     */
    private static void merge(int[] data, int left, int center, int right) {

        //创建一个中间数组

        //不用每次都创建一个全量数组（浪费空间）
        //或者通过外面传入一个全量数组， 排序的时候公用这一个传入的中间数组
        //int[] tmpArr = new int[data.length];
        int[] tmpArr = new int[right + 1];

        int mid = center + 1;
        //third记录中间数组的索引
        int third = left;
        int tmp = left;

        while (left <= center && mid <= right) {
            //从两个数组中取出小的放进中间数组
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }
        //剩余部分依次放入中间数组
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }

        //将中间数组中的内容复制回原数组（原left至right范围的内容复制回原数组）
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
        System.out.println("tmp:" + Arrays.toString(tmpArr));

    }


    private static void sort(int[] data, int left, int right) {
        if (left < right) {
            //找到中间索引
            int center = (left + right) / 2;
            //对左边数组进行递归
            sort(data, left, center);
            //对右边数组进行排序
            sort(data, center + 1, right);

            System.out.println(left + "-" + right);

            //合并
            merge(data, left, center, right);

            System.out.println("\t   " + Arrays.toString(data));


        }
    }


    public static void mergeSort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    public static void main(String[] args) {
        /*
            [2, 1, 45, 2, 5, 10, 43, 12] 元素8个的时候
            0-1
            2-3
            0-3
            4-5
            6-7
            4-7
            0-7

            [2, 1, 45, 2, 5, 10, 43, 12, 11] 元素9个的时候
            0-1
            0-2
            3-4
            0-4
            5-6
            7-8
            5-8
            0-8

         */
        //int[] data = {2, 1, 45, 2, 5, 10, 14, 12, 11};
        int[] data = {2, 1, 45, 2, 5, 10, 43, 12};
        System.out.println("before:" + Arrays.toString(data));
        mergeSort(data);
        System.out.println("after:" + Arrays.toString(data));
    }


}
