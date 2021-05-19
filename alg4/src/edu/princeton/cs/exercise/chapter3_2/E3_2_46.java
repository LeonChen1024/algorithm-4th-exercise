package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.*;

/**
 * 3.2.46 Crossover to binary search trees. Find the values of N for which using a binary
 * search tree to build a symbol table of N random double keys becomes 10, 100, and
 * 1,000 times faster than binary search. Predict the values with analysis and verify them
 * experimentally.
 * <p>
 * 二分搜索树的分频器.找到 N 的值使得一个使用二叉树构建的 N 个随机 double 键的符号表比二分搜索快 10,
 * 100,1000 倍.通过分析预测这个值并且通过实验证明他们
 *
 * @author LeonChen
 * @since 5/14/21
 */
class E3_2_46 {

    /**
     * @formatter:off
     * 二叉树查找平均需要 1.39lgN 次比较,二分查找则是 N/2 ,所以可以得到他们的消耗比较累积
     * 二叉树 1.39∑_{i=1}^{N-1} lgi = 1.39 lg(N-1)! = 1.39(N-1)lg(N-1)
     * 二分查找 1/2+2/2+...+(N-1)/2 = N(N-1)/4
     * 所以将 10,100,1000 代入方程中
     * 10 的时候:
     *   13.9(N-1)lg(N-1) = N(N-1)/4
     * 100 的时候:
     *   139(N-1)lg(N-1) = N(N-1)/4
     * 1000 的时候:
     *   1390(N-1)lg(N-1) = N(N-1)/4
     * 可以使用穷举来解
     * 预估
     * when N = 115 , bst is 10 times faster than binary search
     * when N = 1812 , bst is 100 times faster than binary search
     * when N = 24394 , bst is 1000 times faster than binary search
     *
     * 实验结果:
     * ten times faster N = 1320
     * hundred times faster N = 16352
     * 跑不动了,实在花太长时间了
     *
     * 由于实际方法不只只有单纯的查找对比次数,还涉及到其他程序部分,所以会有偏差
     * @formatter:on
     */
    public static void main(String[] args) {
        boolean tenTimesFound = false;
        boolean hundredTimesFound = false;
        boolean thousandTimesFound = false;
        for (int N = 10; true; N++) {
            if (!tenTimesFound && (13.9 * (N - 1) * Math.log10(N - 1) - N * (N - 1) / 4) < 0.0001) {
                tenTimesFound = true;
                StdOut.println("when N = " + N + " , bst is 10 times faster than binary " +
                        "search");
            }
            if (!hundredTimesFound && (139 * (N - 1) * Math.log10(N - 1) - N * (N - 1) / 4) < 0.0001) {
                hundredTimesFound = true;
                StdOut.println("when N = " + N + " , bst is 100 times faster than binary" +
                        " " +
                        "search");
            }

            if (!thousandTimesFound && (1390 * (N - 1) * Math.log10(N - 1) - N * (N - 1) / 4) < 0.0001) {
                thousandTimesFound = true;
                StdOut.println("when N = " + N + " , bst is 1000 times faster than " +
                        "binary" +
                        " search");
            }
            if (tenTimesFound && hundredTimesFound && thousandTimesFound) {
                break;
            }
        }


        tenTimesFound = false;
        hundredTimesFound = false;
        thousandTimesFound = false;
//        for (int i = 70000; true; i = (int) (i * 1.1)) {
        for (int i = 1; true; i++) {
            double[] doubles = new double[i];
            for (int j = 0; j < i; j++) {
                doubles[j] = StdRandom.uniform(Double.MIN_VALUE, Double.MAX_VALUE);
            }

            BST<Double, Boolean> bst = new BST<>();
            long start = System.nanoTime();
            for (double aDouble : doubles) {
                bst.put(aDouble, true);
            }
            double bstTime = System.nanoTime() - start;

            BinarySearchST<Double, Boolean> binarySearchST = new BinarySearchST<>();

            start = System.nanoTime();
            for (double aDouble : doubles) {
                binarySearchST.put(aDouble, true);
            }
            double binaryStTime = System.nanoTime() - start;

            if (!tenTimesFound && bstTime * 10 <= binaryStTime && bstTime != 0) {
                tenTimesFound = true;
                StdOut.println("ten times faster N = " + i);
            }
            if (!hundredTimesFound && bstTime * 100 <= binaryStTime && bstTime != 0) {
                hundredTimesFound = true;
                StdOut.println("hundred times faster N = " + i);
            }
            if (!thousandTimesFound && bstTime * 1000 <= binaryStTime && bstTime != 0) {
                thousandTimesFound = true;
                StdOut.println("thousand times faster N = " + i);
            }
            if (tenTimesFound && hundredTimesFound && thousandTimesFound) {
                break;
            }
        }

    }


    public static double[] countSequential(String[] a) {
        int distinct = 0, words = 0;
        double[] timeOpts = new double[a.length];
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        double totalTime = 0;
        for (String key : a) {
            Stopwatch stopwatch = new Stopwatch();
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
            totalTime += stopwatch.elapsedTime();
            timeOpts[words - 1] = totalTime;
        }

        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("last " + timeOpts[timeOpts.length - 1]);
        return timeOpts;
    }


    public static double[] countBST(String[] a) {
        int distinct = 0, words = 0;
        double[] timeOpts = new double[a.length];
        BST<String, Integer> st = new BST<>();

        double totalTime = 0;
        for (String key : a) {
            Stopwatch stopwatch = new Stopwatch();
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
            totalTime += stopwatch.elapsedTime();
            timeOpts[words - 1] = totalTime;
        }

        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("last " + timeOpts[timeOpts.length - 1]);
        return timeOpts;
    }
}
