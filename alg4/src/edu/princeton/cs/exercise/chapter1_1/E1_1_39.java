package edu.princeton.cs.exercise.chapter1_1;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.39 Random matches. Write a BinarySearch client that takes an int value T as command-line
 * argument and runs T trials of the following experiment for N = 10^3, 10^4, 10^5, and 10^6:
 * generate two arrays of N randomly generated positive six-digit int values, and find the number
 * of values that appear in both arrays. Print a table giving the average value of this quantity
 * over the T trials for each value of N.
 *
 * <p>随机匹配.编写一个二分搜索来接收一个整数T ,并运行 T 次试验 ,其中 N = 10^3, 10^4, 10^5, and 10^6 .
 * 生成两个大小为N大数组,其中填充六位数为上限大随机数,并且找到在两个数组中都出现的值.打印出这个数量的平均值对应 T次试验和每一个N的值.
 */
class E1_1_39 {


    private static void test1_1_39(int T) {
        int[] Ns =
                new int[]{
                        (int) Math.pow(10, 3), (int) Math.pow(10, 4), (int) Math.pow(10, 5), (int) Math.pow(10, 6)
                };

        for (int N : Ns) {
            int[] as = new int[N];
            int[] bs = new int[N];
            int count = 0;
            for (int j = 0; j < T; j++) {
                //

                // 随机生成两个N数量的数组
                for (int i = 0; i < N; i++) {
                    as[i] = StdRandom.uniform(1000000);
                    bs[i] = StdRandom.uniform(1000000);
                }

                for (int a : as) {

                    if (BinarySearch.indexOf(bs, a) != -1) {
                        count++;
                    }
                }
            }
            StdOut.println("T = " + T + " , N = " + N + " , sameCount = " + count);
        }
    }

    public static void main(String[] args) {
        test1_1_39(100);
    }
}
