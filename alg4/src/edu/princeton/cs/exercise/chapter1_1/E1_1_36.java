package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.36 Empirical shuffle check. Run computational experiments to check that our shuffling code
 * on page 32 works as advertised. Write a program ShuffleTest that takes command-line arguments M
 * and N, d oes N shuffles of an array of size M that is initialized with a[i] = i before each
 * shuffle, and prints an M-by-M table such that row i gives the number of times i wound up in
 * position j for all j. All entries in the array should be close to N/M.
 *
 * <p>乱序检查。通过实验检查32页中的乱序代码是否能够产生预期的效果。编写一个程序ShuffleTest, 接受命令行参数 M 和 N，将大小为M 的数组打乱 N
 * 次且在每次打乱之前都将数组重新初始化为a[i] = i。打印一个 M×M 的表格，对于所有的列 j，行 i 表示的是 i 在打乱后落到 j 的位置的次数。数组中的所有元素的值都应该接近于
 * N/M。
 */
class E1_1_36 {

    private static void test1_1_36(int M, int N) {
        int[] a = new int[M];

        int[][] result = new int[M][M];

        // 打乱N次测试
        for (int i = 0; i < N; i++) {
            // 初始化
            for (int k = 0; k < M; k++) {
                a[k] = k;
            }

            StdRandom.shuffle(a);
            for (int j = 0; j < a.length; j++) {
                result[a[j]][j]++;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                StdOut.print(result[i][j] + "|");
            }
            StdOut.println("");
        }
    }

    public static void main(String[] args) {
        test1_1_36(8, 800);

    }
}
