package com.chiclaim.algorithm;

import java.util.Arrays;

/**
 * 堆排序
 * Created by Chiclaim on 2018/4/4.
 */
public class HeapSort {


    public static void main(String[] args) {
        Integer[] array = {4, 1, 4, 7, 3, 8, 0};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }


    public static void heapSort(Integer[] data) {
        int len = data.length;
        for (int i = 0; i < len; i++) {
            buildMaxHeap(data, len - 1 - i);
            swap(data, 0, len - 1 - i);
        }
    }


    private static void buildMaxHeap(Integer[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点的）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存当前正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1
                //代表k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //如果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大子节点的值
                if (data[k] < data[biggerIndex]) {
                    //数据交换
                    swap(data, k, biggerIndex);
                    //交换后，将biggerIndex赋值给k，开始while的下一次循环，保证k节点的值大于其左右节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(Integer[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


}
