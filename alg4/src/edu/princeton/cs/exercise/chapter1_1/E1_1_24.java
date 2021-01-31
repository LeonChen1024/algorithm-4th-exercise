package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.24 Give the sequence of values of p and q that are computed when Euclid’s algorithm is used
 * to compute the greatest common divisor of 105 and 24. Extend the code given on page 4 to
 * develop a program Euclid that takes two integers from the command line and computes their
 * greatest common divisor, printing out the two arguments for each call on the recursive method.
 * Use your program to compute the greatest common divisor or 1111111 and 1234567.
 * <p>
 * 给出欧几里德算法计算105 和 24 的最大公约数时 p和 q 的值序列.扩展第 4 页给定代码来开发一个程序 Euclid 从命令行接收两个整数
 * 并计算出他们的最大公约数,打印出递归方法每次调用的参数.使用你的程序来计算1111111和1234567的最大公约数
 */
class E1_1_24 {


    /**
     * 最大公约数(greatest common divisor,缩写为gcd)指两个或多个整数共有约数中最大的一个.
     * 欧几里德算法又称辗转相除法，是用于计算两个正整数a，b的最大公约数。计算公式gcd(a,b) = gcd(b,a mod b).当 a mod b 没有余数的时候 b 就是最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        int rem;
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        rem = a % b;
        if (0 == rem) return b;
        //    System.out.println("m:" + b + ";n:" + rem);
        return gcd(b, rem);
    }

    /**
     *
     */
    public static void main(String[] args) {
        int result = gcd(1111111, 1234567);
        StdOut.println(result);
    }
}
