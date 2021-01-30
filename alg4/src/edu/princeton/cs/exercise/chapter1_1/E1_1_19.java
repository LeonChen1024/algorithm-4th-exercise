package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.exercise.Fibonacci;

/**
 * 1.1.19 Run the following program on your computer: <code>
 * public class Fibonacci {
 * public static long F(int N) {
 * if (N == 0) return 0;
 * if (N == 1) return 1;
 * return F(N - 1) + F(N - 2);
 * }
 * <p>
 * public static void main(String[] args) {
 * for (int N = 0; N < 100; N++) {
 * StdOut.println(N + " " + F(N));
 * }
 * }
 * }
 * </code> What is the largest value of N for which this program takes less 1 hour to compute the
 * value of F(N)? Develop a better implementation of F(N) that saves computed values in an array.
 * <p>
 * 在你的电脑上运行下列程序:
 * <p>
 * 这个电脑上一小时内可以运行的F(N)的最大值 N 是多少?开发一个比 F(N) 更好的实现可以节省数组中计算的值
 */
class E1_1_19 {


    /**
     *
     */
    public static void main(String[] args) {
        Fibonacci.main(null);
    }
}
