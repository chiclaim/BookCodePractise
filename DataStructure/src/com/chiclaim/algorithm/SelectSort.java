package com.chiclaim.algorithm;

import java.util.Arrays;

/**
 * 选择排序
 * <p>
 * 选择排序需要n-1趟比较
 *
 * 时间效率 O(n^2)
 * <p>
 * Created by Chiclaim on 2018/3/28.
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {4, 1, 4, 7, 3, 8, 0};
        //selectSort(array);
        selectSort2(array);
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

    /*
        上面实现的方式，每一趟比较，程序一旦发现某个数据比第1位的数据小，就立即交换他们，这样的增加了数据交换的次数，导致算法效率降低

        所以下面为改进方案：每趟比较只记录最小数据的索引，只交换一次
     */
    public static void selectSort2(int[] data) {
        int size = data.length;
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (data[i] > data[j]) {
                    minIndex = j;
                }
            }
            //如果最小值是当前的值，则不需要交换
            if (minIndex != i) {
                int tmp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = tmp;
            }
        }
    }


}
