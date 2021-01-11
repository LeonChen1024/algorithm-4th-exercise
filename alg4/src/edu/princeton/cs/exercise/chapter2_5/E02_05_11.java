package edu.princeton.cs.exercise.chapter2_5;


import java.util.Arrays;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.11 One way to describe the result of a sorting algorithm is to specify a permutation p[]
 * of the numbers 0 to a.length-1, such that p[i] specifies where the key originally in a[i] ends
 * up. Give the permutations that describe the results of insertion sort, selection sort,
 * shellsort, mergesort, quicksort, and heapsort for an array of seven equal keys.
 *
 * 描述排序算法结果的一种方式是指定一个排列了数字 0 到 a.length-1 的数组 p[],使得 p[i] 指定了原始的 a[i] 最后位置
 * 变成哪里.给出插入排序,选择排序,希尔排序,合并排序,快速排序,还有堆排序在七个相同键的时候的结果
 *
 * @author LeonChen
 * @since 12/21/20
 */
class E02_05_11 {


    private static final String QUICK = "quickSort";
    private static final String INSERT = "insertSort";
    private static final String SELECT = "selectSort";
    private static final String SHELL = "shellSort";
    private static final String MERGE = "mergeSort";
    private static final String HEAP = "heapSort";

    private static class Pnum implements Comparable<Pnum> {
        private Integer value;
        private int originIndex;

        public Pnum(Integer value, int originIndex) {
            this.value = value;
            this.originIndex = originIndex;
        }

        @Override
        public int compareTo(Pnum o) {
            return o.value.compareTo(o.value);
        }

        @Override
        public String toString() {
            return originIndex + "";
        }
    }


    /**
     *
     */
    public static void main(String[] args) {

        sortTest(INSERT);
        sortTest(SELECT);
        sortTest(SHELL);
        sortTest(MERGE);
        sortTest(QUICK);
        sortTest(HEAP);

    }

    private static void sortTest(String sortMethod) {
        Pnum[] arr = genArr();
        switch (sortMethod) {
            case INSERT:
                Insertion.sort(arr);
                StdOut.println(INSERT);
                break;
            case SELECT:
                Selection.sort(arr);
                StdOut.println(SELECT);
                break;
            case SHELL:
                Shell.sort(arr);
                StdOut.println(SHELL);
                break;
            case MERGE:
                Merge.sort(arr);
                StdOut.println(MERGE);
                break;
            case QUICK:
                Quick.sort(arr);
                StdOut.println(QUICK);
                break;
            case HEAP:
                Heap.sort(arr);
                StdOut.println(HEAP);
                break;
            default:
                break;

        }
        StdOut.println(Arrays.toString(arr));

    }

    private static Pnum[] genArr() {
        int numSize = 7;
        Pnum[] arr = new Pnum[numSize];
        for (int i = 0; i < numSize; i++) {
            Pnum pnum = new Pnum(1, i);
            arr[i] = pnum;
        }
        return arr;
    }

}
