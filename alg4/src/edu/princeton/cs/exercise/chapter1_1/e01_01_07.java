package edu.princeton.cs.exercise.chapter1_1;

import edu.princeton.cs.algs4.StdOut;


/**
 * 1.1.7 Give the value printed by each of the following code fragments: <br>
 * a. <code>
 *     double t = 9.0;
 *     while (Math.abs(t - 9.0 / t) > .001) t = (9.0 / t + t) / 2.0;
 *     StdOut.printf("%.5f\n", t);
 * </code> b.<code>
 *     int sum = 0;
 *     for (int i = 1; i < 1000; i++) for (int j = 0; j < i; j++) sum++;
 *     StdOut.println(sum);
 * </code> c.<code>
 *      int N = 3;
 *     int sum1 = 0;
 *     for (int i = 1; i < 1000; i *= 2) for (int j = 0; j < N; j++) sum1++;
 *     StdOut.println(sum1);
 * </code>
 *
 * 给出下面代码片段打印的值
 */
class e01_01_07 {


  /**
   */
  public static void main(String[] args) {
// "%.5f\n" 保留5位小数
    double t = 9.0;
    while (Math.abs(t - 9.0 / t) > .001) t = (9.0 / t + t) / 2.0;
    StdOut.printf("%.5f\n", t);

    int sum = 0;
    for (int i = 1; i < 1000; i++) for (int j = 0; j < i; j++) sum++;
    StdOut.println(sum);

    int N = 3;
    int sum1 = 0;
    for (int i = 1; i < 1000; i *= 2) for (int j = 0; j < N; j++) sum1++;
    StdOut.println(sum1);
  }
}
