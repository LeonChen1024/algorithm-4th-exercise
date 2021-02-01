package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.37 Bad shuffling. Suppose that you choose a random integer between 0 and N-1 in our
 * shuffling code instead of one between i and N-1. Show that the resulting order is not equally
 * likely to be one of the N! possibilities. Run the test of the previous exercise for this
 * version.
 *
 * <p>差的乱序,假设你在shuffling 代码中选择一个随机的 0到 N-1 的数字来取代 i 到 N-1.展示导致的结果顺序不会与N! 的可能性类似. 使用前一个联系的代码来测试这个版本
 */
class E1_1_37 {


    private static void test1_1_37(int M, int N) {
        // 使用 badShuffling 替换shuffling 即可
        int[] a = new int[M];

        int[][] result = new int[M][M];

        // 打乱N次测试
        for (int i = 0; i < N; i++) {
            // 初始化
            for (int k = 0; k < M; k++) {
                a[k] = k;
            }

            StdRandom.badShuffle(a);
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
        test1_1_37(8, 800);

    }
}
