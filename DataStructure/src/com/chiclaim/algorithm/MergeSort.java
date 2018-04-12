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
     * @param start   左数组的第一个元素的索引
     * @param center 左数组的最后一个元素的索引，center+1是右数组的第一个元素的索引
     * @param end  右数组的最后一个元素的索引
     */
    private static void merge(int[] data, int start, int center, int end) {

        //创建一个中间数组
        //不用每次都创建一个全量数组（浪费空间）
        //或者通过外面传入一个全量数组， 后面排序的时候共用这一个传入的中间数组
        //int[] tmpArr = new int[data.length];
        int[] tmpArr = new int[end + 1];

        //third记录中间数组的索引
        int tmpArrCursor = start;

        //保存一开始的左边界，用于把中间数组的数据复制到原数组中
        int tmpLeft = start;

        int j = center + 1;
        int i = start;

        while (i <= center && j <= end) {
            //从两个数组中取出小的放进中间数组
            if (data[i] <= data[j]) {
                tmpArr[tmpArrCursor++] = data[i++];
            } else {
                tmpArr[tmpArrCursor++] = data[j++];
            }
        }
        //剩余的右边部分(mid~right)依次放入中间数组
        while (j <= end) {
            tmpArr[tmpArrCursor++] = data[j++];
        }
        //剩余的左边部分(left~center)依次放入中间数组
        while (i <= center) {
            tmpArr[tmpArrCursor++] = data[i++];
        }

        //将中间数组中的内容复制回原数组（原left至right范围的内容复制回原数组）
        while (tmpLeft <= end) {
            data[tmpLeft] = tmpArr[tmpLeft++];
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

            System.out.println(left + "-" + center + "-" + right);

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
