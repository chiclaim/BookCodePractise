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

        System.out.println(left + "-" + center + "-" + right);

        int[] tmpArr = new int[data.length];

        int mid = center + 1;
        //third记录中间数组的索引
        int third = left;
        int tmp = left;

        while (left <= center && mid <= right) {
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


    }


    private static void sort(int[] data, int left, int right) {
        if (left < right) {
            //找到中间索引
            int center = (left + right) / 2;
            //对左边数组进行递归
            sort(data, left, center);
            //对右边数组进行排序
            sort(data, center + 1, right);
            //合并
            merge(data, left, center, right);
        }
    }


    public static void mergeSort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    public static void main(String[] args) {
        int[] data = {2, 3, 45, 2, 5, 10, 143, 12};
        System.out.println(Arrays.toString(data));
        mergeSort(data);
        System.out.println(Arrays.toString(data));
    }


}
