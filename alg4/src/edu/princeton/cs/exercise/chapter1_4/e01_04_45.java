package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.4.45 Coupon collector problem. Generating random integers as in the previous exercise, run
 * experiments to validate the hypothesis that the number of integers generated before all possible
 * values are generated is ~NH_N.
 *
 * <p>收集优惠卷问题. 和上一个练习一样生成随机数字,运行试验来证明假说在所有的数字都生成了之前总共生成了 ~NH_N次.
 *
 * @author LeonChen
 * @since 4/7/20
 */
class e01_04_45 {

  /** ~NH_N是什么暂时还不清楚 */
  public static void main(String[] args) {

    doublelingRatio();
  }

  private static void doublelingRatio() {
    for (int n = 250; n < 1000000000; n += n) {
      int cnt = timeTrial(n);
      StdOut.printf("trail %7d numbers,there are %7d generated number \n ", n, cnt);
    }
  }

  public static int timeTrial(int n) {
    boolean[] a = new boolean[n];
    int uniqNums = 0;
    int cnt = 0;

    while (true) {
      int cur = StdRandom.uniform(0, n);
      cnt++;
      if (a[cur]) {
      } else {
        a[cur] = true;
        uniqNums++;
      }
      if (uniqNums == n) {
        return cnt;
      }
    }
  }
}
