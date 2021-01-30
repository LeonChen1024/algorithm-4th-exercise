package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.20 Write a recursive static method that computes the value of ln (N!)
 * 编写一个递归静态方法计算 ln(N!)的值
 */
class E1_1_20 {


    private static double callnN(int N) {
        if (N == 0) return 0;
        if (N == 1) return 0;
        return (callnN(N - 1) + Math.log(N));
    }

    /**
     *
     */
    public static void main(String[] args) {
        // ln a 是对数符号, log_e a 的缩写
        // N! 是N 的阶乘
        // log_e (mn) = log_e m + log_e n
        // f(3) = ln(3!) = ln(3*2*1) = ln3 + ln2 + ln1 = f(2) + ln3
        // f(n) = f(n-1) + lnn
        StdOut.println(callnN(3));
    }
}
