package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.exercise.util.ArrayGenUtil;

import java.util.HashMap;

/**
 * 3.1.33 Driver for self-organizing search. Write a driver program for self-organizing
 * search implementations (see Exercise 3.1.22) that uses get() to fill a symbol table
 * with N keys, then does 10 N successful searches according to a predefined probability
 * distribution. Use this driver to compare the running time of your implementation from
 * Exercise 3.1.22 with BinarySearchST for N = 10^3, 10^4, 10^5, and 10^6 using the probability
 * distribution where search hits the ith smallest key with probability 1/2^i .
 * <p>
 * 自组织搜索驱动.编写一个驱动针对自组织搜索实现(见练习 3.1.22),它填充N 个键到一个符号表,然后通过
 * 预定义的分布做 10N 次成功的查询. 使用这个驱动来对比你实现的练习 3.1.22 和 BinarySearchST, N = 10^3, 10^4,
 * 10^5, 和 10^6 使用搜索命中第 i 小的 key 的概率是 1/2^i 的概率分布
 *
 * @author LeonChen
 * @since 2/28/20
 */
class E3_1_33 {

    /**
     * 概率保留 3 位, 乘以 1000,从 0 开始按照添加到映射中(例如 key 0,1,2,3,4 值为 0,key 5,6 值为 1....)
     * 然后随机 1000 内的数字,通过 map 取到该随机位置对应的 key
     * n = 1000
     * self organized used time = 0.012
     * binarySearchST used time = 0.004
     * n = 10000
     * self organized used time = 0.055
     * binarySearchST used time = 0.008
     * n = 100000
     * self organized used time = 4.212
     * binarySearchST used time = 0.075
     * n = 1000000
     * self organized used time = 605.248
     * binarySearchST used time = 0.82
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
            // 概率和 key 对应
            for (int i = 1; i <= arr.length; i++) {
                int possible = (int) ((1 / Math.pow(2, i + 1)) * 1000);
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
