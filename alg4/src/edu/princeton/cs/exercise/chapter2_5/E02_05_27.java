package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 2.5.27 Sorting parallel arrays. When sorting parallel arrays, it is useful to have a version
 * of a sorting routine that returns a permutation, say index[], of the indices in sorted
 * order. Add a method indirectSort() to Insertion that takes an array of Comparable
 * objects a[] as argument, but instead of rearranging the entries of a[] returns an integer
 * array index[] so that a[index[0]] through a[index[N-1]] are the items in ascending
 * order.
 * <p>
 * 排序并行数组.当排序一个并行数组时,使用一个排序流程返回一个排列 index[]有序保存索引是非常有用的.添加一个方法
 * indirectSort()来插入,它接受一个 Comparable 数组对象 a[]作为参数,重新排序 a[] 返回一个整型数组 index[]
 * 使得 a[index[0]] 到 a[index[N-1]]是一个升序排列
 *
 * @author LeonChen
 * @since 1/8/20
 */
class E02_05_27 {

    /**
     * 使用一个 index[] 数组作为索引层,而不直接修改原始数组的元素位置,访问元素的时候通过 a[index[0]] 索引层
     * 做一次转换
     */
    public static void main(String[] args) {
        Integer[] a = new Integer[]{4, 15, 7, 32, 752, 1, 47, 8, 28};
        int[] ints = indirectSort(a);
        StdOut.println(Arrays.toString(ints));
        
    }

    private static int[] indirectSort(Comparable[] a) {
        int[] index = new int[a.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length - 1; j++) {
                if (a[index[i]].compareTo(a[index[j]]) > 0) {
                    int temp = index[i];
                    index[i] = index[j];
                    index[j] = temp;
                }
            }
        }
        return index;
    }


}
