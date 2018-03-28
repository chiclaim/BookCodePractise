package com.chiclaim.algorithm;

import java.util.Arrays;

/**
 * 选择排序
 * <p>
 * 选择排序需要n-1趟比较
 * <p>
 * Created by Chiclaim on 2018/3/28.
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {4, 1, 4, 7, 3, 8, 0};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }


    public static void selectSort(int[] data) {
        int size = data.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (data[i] > data[j]) {
                    int tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
            }
        }
    }

    public static void selectSort2(int[] data){

    }


}
