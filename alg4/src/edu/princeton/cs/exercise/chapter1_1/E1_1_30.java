package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

import static edu.princeton.cs.exercise.chapter1_1.E1_1_24.gcd;

/**
 * 1.1.30 Array exercise. Write a code fragment that creates an N-by-N boolean array a[][] such
 * that a[i][j] is true if i and j are relatively prime (have no common factors), and false
 * otherwise.
 *
 * <p>没有公因子为true,也就是互质,否则为false
 */
class E1_1_30 {


    private static void test1_1_30(int[] ints) {
        // 可以复用之前的求最大公约数的方法来计算,当最大公约数是1的时候,就是互质
        int N = ints.length;
        boolean[][] result = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StdOut.println("");
            for (int j = 0; j < N; j++) {
                if (gcd(ints[i], ints[j]) == 1) {
                    result[i][j] = true;
                } else {
                    result[i][j] = false;
                }
                StdOut.print(result[i][j] + "  ,  ");
            }
        }
    }


    public static void main(String[] args) {
        test1_1_30(new int[]{1, 3, 5, 6, 9});
    }
}
