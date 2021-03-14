package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.exercise.util.ArrayGenUtil;

import java.util.HashMap;

/**
 * 3.1.34 Zipf’s law. Do the previous exercise for the probability distribution where
 * search hits the ith smallest key with probability 1/(iH_N) where H_N is a Harmonic number
 * (see page 185).This distribution is called Zipf’s law. compare the move-to-front heuristic
 * with the optimal arrangement for the distributions in the previous exercise, which
 * is to keep the keys in increasing order (decreasing order of their expected frequency).
 * <p>
 * 齐普夫定律.使用搜索命中第 i 小的key 的概率是 1/(iH_N) 的概率分布来做前面的练习,其中 H_N 是一个调和级数(见
 * 185 页). 这个分布模型叫做齐普夫定律.对比前移启发式和上个练习中的最佳分布,都使键保持递增顺序(也就是预期频率的
 * 降序)
 *
 * @author LeonChen
 * @since 3/1/20
 */
class E3_1_34 {

    private static HashMap<Integer, Double> cacheMap = new HashMap<>();

    private static double hn(int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    /**
     * 调和级数 H_N = 1+1/2+1/3+1/4+...1/N
     * <p>
     * n = 1000
     * self organized used time = 0.014
     * binarySearchST used time = 0.004
     * n = 10000
     * self organized used time = 0.055
     * binarySearchST used time = 0.008
     * n = 100000
     * self organized used time = 4.114
     * binarySearchST used time = 0.079
     * n = 1000000
     * self organized used time = 629.155
     * binarySearchST used time = 0.905
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] Ns = new int[]{(int) Math.pow(10, 3), (int) Math.pow(10, 4), (int) Math.pow(10, 5),
                (int) Math.pow(10, 6)};
        for (int n : Ns) {
            StdOut.println("n = " + n);
            Comparable[] arr = ArrayGenUtil.genOrderedArr(n);
            HashMap<Integer, Comparable> keyPossibleMap = new HashMap<>();
            int index = 0;
            double hn = hn(n);
            // 概率和 key 对应
            for (int i = 1; i <= arr.length; i++) {
                int possible = (int) ((1 / (i * hn)) * 1000);
                for (int j = 0; j < possible; j++) {
                    keyPossibleMap.put(index++, arr[i]);
                }
            }

            int searchs = 10 * n;
            Comparable[] searchKeys = new Comparable[searchs];
            for (int i = 0; i < searchs; i++) {
                int posIndex = StdRandom.uniform(index);
                searchKeys[i] = keyPossibleMap.get(posIndex);
            }


            E3_1_22.SelfOrganizeArrayST<Comparable, Integer> organizeArrayST =
                    new E3_1_22.SelfOrganizeArrayST<>();
            Stopwatch stopwatch = new Stopwatch();
            for (Comparable comparable : arr) {
                organizeArrayST.put(comparable, 1);
            }

            for (Comparable searchKey : searchKeys) {
                organizeArrayST.get(searchKey);
            }
            double time = stopwatch.elapsedTime();
            StdOut.println("self organized used time = " + time);


            stopwatch = new Stopwatch();
            BinarySearchST binarySearchST = new BinarySearchST<>();
            for (Comparable integer : arr) {
                binarySearchST.put(integer, 1);
            }
            for (Comparable searchKey : searchKeys) {
                binarySearchST.get(searchKey);
            }
            time = stopwatch.elapsedTime();
            StdOut.println("binarySearchST used time = " + time);


        }


    }


}
