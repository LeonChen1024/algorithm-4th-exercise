package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.2.40 Height. Run empirical studies to estimate average BST height by running 100
 * trials of the experiment of inserting N random keys into an initially empty tree, for N =
 * 10^4, 10^5, and 10^6. Compare your results against the 2.99 lg N estimate that is
 * described in the text.
 * <p>
 * 高度.运行100 次试验来预估BST 的平均高度,每次插入 N 个随机键到一个初始为空的树,其中 N= 10^4, 10^5,
 * 和 10^6.将你的结果和正文中描述的 2.99 lgN 对比.
 *
 * @author LeonChen
 * @since 5/1/21
 */
class E3_2_40 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        int[] ns = new int[]{(int) Math.pow(10, 4), (int) Math.pow(10, 5),
                (int) Math.pow(10, 6)};
        for (int n : ns) {

            int totalHeight = 0;
            int trial = 100;
            for (int i = 0; i < trial; i++) {
                BST<Integer, Integer> bst = new BST<>();
                for (int j = 0; j < n; j++) {
                    bst.put(StdRandom.uniform(n), 1);
                }
                totalHeight += bst.height();
            }

            double avgHeight = totalHeight / trial;
            StdOut.println("when n = " + n + " , bst avgHeight = " + avgHeight
                    + " ,2.99lgN = " + 2.99 * Math.log(n) / Math.log(2));
        }
    }


}
