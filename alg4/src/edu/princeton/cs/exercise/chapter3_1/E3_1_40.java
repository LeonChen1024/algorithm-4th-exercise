package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.40 Crossover to binary search. Find the values of N for which binary search in a
 * symbol table of size N becomes 10, 100, and 1,000 times faster than sequential search.
 * Predict the values with analysis and verify them experimentally.
 * <p>
 * 过渡到二进制搜索.找到 N 的值使得大小为 N 的二分搜索的符号表的效率是顺序搜索的 10,100,和 1000 倍.预测这个
 * 值并且分析和用实验证明它
 *
 * @author LeonChen
 * @since 3/10/21
 */
class E3_1_40 {

    /**
     * 顺序查询的时间复杂度是 O(n) 而二分搜索则是 O(lg^n)
     * 目前使用测试方法接近这个比例,可以得到
     * <p>
     * N = 58, 二分搜索是顺序搜索的 10 倍.
     * <p>
     * N = 996, 100 倍 .
     * <p>
     * N = 13746 , 1000 倍.
     * <p>
     * Experimental results:
     * <p>
     * Value of N for which binary search becomes 10 times faster than sequential search: 120
     * Value of N for which binary search becomes 100 times faster than sequential search: 1463
     * Value of N for which binary search becomes 1000 times faster than sequential search: 22461
     * <p>
     * 实验结果 会有一些偏差,因为实际的程序中不只有这个搜索的时间消耗
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        int N = 30000;
        boolean tenTimeFind = false;
        boolean hundredTimeFind = false;
        boolean thousandTimeFind = false;

        for (int i = 0; i < N; i++) {
            int[] arr = new int[i];
            for (int j = 0; j < i; j++) {
                arr[j] = j;
            }
            int cannotFind = i;
            SequentialSearchST<Integer, Integer> sequentialSearchST = new SequentialSearchST<>();
            BinarySearchST<Integer, Integer> binarySearchST = new BinarySearchST<>();
            for (int j = 0; j < arr.length; j++) {
                sequentialSearchST.put(j, j);
                binarySearchST.put(j, j);
            }

            long time = System.nanoTime();
            for (int j = 0; j < i; j++) {
                sequentialSearchST.get(j);
            }
            sequentialSearchST.get(cannotFind);
            long seqTime = System.nanoTime() - time;

            time = System.nanoTime();
            for (int j = 0; j < i; j++) {
                binarySearchST.get(j);
            }
            binarySearchST.get(cannotFind);
            long binTime = System.nanoTime() - time;

            if (binTime == 0) {
                continue;
            }

            if (seqTime / binTime >= 10 && !tenTimeFind) {
                StdOut.println("seqTime = " + seqTime + " , binTime = " + binTime);
                StdOut.println("when N = " + i + " , seq time is 10 times to bin time");
                tenTimeFind = true;
            }

            if (seqTime / binTime >= 100 && !hundredTimeFind) {
                StdOut.println("seqTime = " + seqTime + " , binTime = " + binTime);
                StdOut.println("when N = " + i + " , seq time is 100 times to bin time");
                hundredTimeFind = true;
            }


            if (seqTime / binTime >= 1000 && !thousandTimeFind) {
                StdOut.println("seqTime = " + seqTime + " , binTime = " + binTime);
                StdOut.println("when N = " + i + " , seq time is 1000 times to bin time");
                thousandTimeFind = true;
            }

        }
    }


}
