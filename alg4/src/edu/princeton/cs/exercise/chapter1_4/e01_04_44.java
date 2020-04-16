package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.4.44 Birthday problem. Write a program that takes an integer N from the command line and uses
 * StdRandom.uniform() to generate a random sequence of integers between 0 and N – 1. Run
 * experiments to validate the hypothesis that the number of integers generated before the first
 * repeated value is found is ~√piN/2.
 *
 * <p>生日问题.编写一个程序从命令行接收一个参数 N 并且使用 StdRandom.uniform() 来生成一个0 到 N-1 的整数的随机序列.<br>
 * 执行试验来证明假说生成的整数在第一次重复之前的数量是 ~√piN/2.
 *
 * @author LeonChen
 * @since 4/6/20
 */
class e01_04_44 {

  /** */
  public static void main(String[] args) {

    doublelingRatio();
  }

  private static void doublelingRatio() {
    for (int n = 250; n < 1000000000; n += n) {
      int uniqNums = timeTrial(n);
      StdOut.printf(
          "trail %7d numbers,there are %7d  unique number , ~√piN/2 is %5.1f , ratio is %7.3f\n",
          n, uniqNums, Math.sqrt(Math.PI * n / 2), uniqNums / Math.sqrt(Math.PI * n / 2));
    }
  }

  public static int timeTrial(int n) {
    boolean[] a = new boolean[n];
    int uniqNums = 0;

    while (true) {
      int cur = StdRandom.uniform(0, n - 1);
      if (a[cur]) {
        return uniqNums;
      } else {
        a[cur] = true;
        uniqNums++;
      }
    }
  }
}
