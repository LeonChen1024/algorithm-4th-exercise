package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.41 Crossover to interpolation search. Find the values of N for which interpolation
 * search in a symbol table of size N becomes 1, 2, and 10 times faster than binary search,
 * assuming the keys to be random 32-bit integers (see Exercise 3.1.24). Predict the values
 * with analysis, and verify them experimentally.
 * <p>
 * 和差值搜索对比.找到 N 的值使用在一个大小为 N的符号表中差值搜索是二分搜索的 1,2 和 10 倍,假设键是随机的 32 位
 * 整数(见 3.1.24).预测并分析和验证结果.
 *
 * @author LeonChen
 * @since 3/15/21
 */
class E3_1_41 {

    /**
     * 差值查询的时间复杂度是 O(lg(lgn)) 而二分搜索则是 O(lgn)
     * 目前使用测试方法接近这个比例,可以得到
     * <p>
     * N = 1, 二分搜索是顺序搜索的 1 倍.
     * <p>
     * N = 4, 2 倍 .
     * <p>
     * N = 49*10^16 , 10 倍.
     * <p>
     * Experimental results:
     * <p>
     * interpolation = 7413 , binTime = 8974
     * when N = 7 , interpolation time is 1 times to bin time
     * interpolation = 18876 , binTime = 48629
     * when N = 34 , interpolation time is 2 times to bin time
     * interpolation = 15893 , binTime = 1135003
     * when N = 1402 , interpolation time is 10 times to bin time
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
            E3_1_24.InterpolationST interpolationSt = new E3_1_24.InterpolationST();
            BinarySearchST<Integer, Integer> binarySearchST = new BinarySearchST<>();
            for (int j = 0; j < arr.length; j++) {
                interpolationSt.put(j, j);
                binarySearchST.put(j, j);
            }

            long time = System.nanoTime();
            for (int j = 0; j < i; j++) {
                interpolationSt.get(j);
            }
            interpolationSt.get(cannotFind);
            long interpolationTime = System.nanoTime() - time;

            time = System.nanoTime();
            for (int j = 0; j < i; j++) {
                binarySearchST.get(j);
            }
            binarySearchST.get(cannotFind);
            long binTime = System.nanoTime() - time;

            if (binTime == 0) {
                continue;
            }
            if (binTime / interpolationTime >= 1 && !tenTimeFind) {
                StdOut.println("interpolation = " + interpolationTime + " , binTime = " + binTime);
                StdOut.println("when N = " + i + " , interpolation time is 1 times to bin time");
                tenTimeFind = true;
            }


            if (binTime / interpolationTime >= 2 && !hundredTimeFind) {
                StdOut.println("interpolation = " + interpolationTime + " , binTime = " + binTime);
                StdOut.println("when N = " + i + " , interpolation time is 2 times to bin time");
                hundredTimeFind = true;
            }

            if (binTime / interpolationTime >= 10 && !thousandTimeFind) {
                StdOut.println("interpolation = " + interpolationTime + " , binTime = " + binTime);
                StdOut.println("when N = " + i + " , interpolation time is 10 times to bin time");
                thousandTimeFind = true;
            }

        }
    }


}
