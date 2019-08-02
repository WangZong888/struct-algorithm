package com.wsw.algorithm;

import com.wsw.utils.AlgorithmUtils;


/**
 * @program: struct-algorithm
 * @description:
 * @author: Mr.Wang
 * @create: 2019-08-02 07:43
 * 计数排序的规则是：
 * 分别对个位、十位、百位....进行比较
 * 依次将个位对应的数值放到对应的bucket中，然后从桶中取出来放入原数组中，
 * 再次分别对十位、百位依次这样的操作，没有的位数补0
 **/
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[100];
        AlgorithmUtils.common(arr);

        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("总耗时"+(end -start)+"ms");

    }

    public static void radixSort(int[] arr) {

        //TODO 1.先求出数组的最大位数
        int max = arr[0];//先假定第一位是最大值
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //求出最大位数
        int maxLength = (max + "").length();

        //TODO 2.声明出10个桶，每一个桶就是一个一维数组,bucket的深度为arr.length
        int[][] bucket = new int[10][arr.length];

        //TODO 3.还需要用一个数组来存放每个桶的个数，列如：bucketCount[0] 表示就是bucket[0]桶中的个数
        int[] bucketCount = new int[10];

        //TODO 4.循环几次，需要看maxLength的大小,n作为辅助取模
        for (int i = 0,n=1; i < maxLength; i++,n*=10) {
            //开始循环，取数放入桶中
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / n % 10;//分别拿到个位、十位、百位...依次进行处理
                //放入桶中
                bucket[digit][bucketCount[digit]] = arr[j];
                bucketCount[digit]++;
            }

            //TODO 5.循环结束，开始取出桶中的数据放入原数组中
            int index = 0;//原数组的索引
            for (int k = 0; k < bucketCount.length; k++) {
                //先判断桶中是否有数据,有数据才取
                if (bucketCount[k] != 0) {
                    for (int l = 0; l < bucketCount[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //桶中数组取完了，记得将桶的个数重置为0,不然数据会出错
                bucketCount[k] = 0;
            }
        }


    }
}
