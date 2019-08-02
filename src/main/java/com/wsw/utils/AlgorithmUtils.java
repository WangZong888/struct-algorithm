package com.wsw.utils;

import java.lang.reflect.Method;

/**
 * @program: struct-algorithm
 * @description:
 * @author: Mr.Wang
 * @create: 2019-08-02 08:34
 **/
public class AlgorithmUtils {
    public static void common(int[] arr){
        for (int i = 0; i <arr.length; i++) {
            arr[i] = (int)(Math.random()*arr.length);
        }
    }
}
