package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 2.5.31 Duplicates. Write a client that takes integers M, N, and T as command-line arguments,
 * then uses the code given in the text to perform T trials of the following experiment:
 * Generate N random int values between 0 and M – 1 and count the number of
 * duplicates.Run your program for T=10 and N=10^3,10^4,10^5,and10^6,with M=N/2,
 * and N, and 2N. Probability theory says that the number of duplicates should be about
 * (1–e^{-α}) where α= N/M —--print a table to help you confirm that your experiments
 * validate that formula.
 * <p>
 * 重复项.编写一个客户端接收命令行参数整数 M,N,和 T ,然后使用正文中的代码来执行 T 次下面的实验:生成 N个0到 M-1
 * 的随机整数并且计算重复项的数量.使用 T=10 还有 N=10^3,10^4,10^5,和10^6,以及 M=N/2,以及N 和 2N来运行程序,
 * 概率论认为重复项的数量应该是 (1–e^{-α}) 其中 α= N/M --- 打印一个表来帮助你确认你的实验是否能证明这个公式
 *
 * @author LeonChen
 * @since 1/11/20
 */
class E02_05_31 {


    /**
     * 疑似题目有误,应该是非重复数,并且公式应该乘以 M,否则是概率
     */
    public static void main(String[] args) {
        int[] Ns = new int[]{(int) Math.pow(10, 3), (int) Math.pow(10, 4), (int) Math.pow(10, 5)};
        int T = 10;
        for (int N : Ns) {
            int[] Ms = new int[]{N / 2, N, 2 * N};
            for (int M : Ms) {
                float totalDisNum = 0;
                for (int j = 0; j < T; j++) {
                    int[] a = new int[N];
                    for (int i = 0; i < N; i++) {
                        a[i] = StdRandom.uniform(M - 1);
                    }
                    Arrays.sort(a);
                    int distinct = 1;
                    for (int i = 0; i < a.length - 1; i++) {
                        if (a[i] != a[i + 1]) {
                            distinct++;
                        }
                    }
                    totalDisNum += distinct;
                }

                StdOut.println(String.format("when N = %d ,M = %d , duplication = %f ,formula = " +
                        "%f", N, M, totalDisNum / T, M * (1 - Math.exp(-((double) N / M)))));

            }
        }
    }


}
