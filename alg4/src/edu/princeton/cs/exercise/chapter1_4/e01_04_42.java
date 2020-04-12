package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.*;

import java.util.HashSet;

/**
 * 1.4.42 Problem sizes. Estimate the size of the largest value of P for which you can run
 * TwoSumFast, TwoSum, ThreeSumFast, and ThreeSum on your computer to solve the problems for a file
 * of 2^P thousand numbers. Use DoublingRatio to do so.
 *
 * <p>问题大小.估算你在你的电脑上使用包含 2^p 千个数字可以运行TwoSumFast, TwoSum, ThreeSumFast, 和 ThreeSum 中 P 的最大值<br>
 * 使用倍率来解决.
 *
 * @author LeonChen
 * @since 4/4/20
 */
class e01_04_42 {
  private static final String TWOSUMFAST = "TwoSumFast";
  private static final String TWOSUM = "TwoSum";
  private static final String THREESUMFAST = "ThreeSumFast";
  private static final String THREESUM = "ThreeSum";

  /**
   * 假设最长的运行时间是 1 小时. 也就是3600s. 总用时 = 初始值 * ratio^x<br>
   * <code>
   *     ======= start TWOSUMFAST =======
   * trail     250 numbers,use time   0.001 , ratio is   1.0
   * trail     500 numbers,use time   0.000 , ratio is   0.0
   * trail    1000 numbers,use time   0.000 , ratio is   NaN
   * trail    2000 numbers,use time   0.001 , ratio is Infinity
   * trail    4000 numbers,use time   0.001 , ratio is   1.0
   * trail    8000 numbers,use time   0.002 , ratio is   2.0
   * trail   16000 numbers,use time   0.003 , ratio is   1.5
   * trail   32000 numbers,use time   0.006 , ratio is   2.0
   * trail   64000 numbers,use time   0.012 , ratio is   2.0
   * </code> <code>
   *     可以得出 TWOSUMFAST 当 n *2 时,ratio 是 2 .
   *     3600 = 0.012 * 2^x
   *     x ~ 12.6
   *     64000 * 2^12.6 ~ 397336004
   *     p = logn
   *     p ~ 19.8
   * </code>
   *
   * <p><br>
   * <code>
   * ======= start TWOSUM =======
   * trail     250 numbers,use time   0.000 , ratio is   0.0
   * trail     500 numbers,use time   0.001 , ratio is Infinity
   * trail    1000 numbers,use time   0.002 , ratio is   2.0
   * trail    2000 numbers,use time   0.006 , ratio is   3.0
   * trail    4000 numbers,use time   0.007 , ratio is   1.2
   * trail    8000 numbers,use time   0.039 , ratio is   5.6
   * trail   16000 numbers,use time   0.159 , ratio is   4.1
   * trail   32000 numbers,use time   0.601 , ratio is   3.8
   * trail   64000 numbers,use time   2.442 , ratio is   4.1
   *
   *  可以得出 TWOSUM 当 n *2 时,ratio 是 4 .
   *    *     3600 = 2.442 * 4^x
   *    *     x ~ 5.3
   *    *     64000 * 2^5.3 ~ 2521384
   *    *     p = logn
   *    *     p ~ 14.7
   *
   *
   *
   * </code> <code>
   *      ======= start THREESUMFAST =======
   *    * trail     250 numbers,use time   0.003 , ratio is   1.5
   *    * trail     500 numbers,use time   0.005 , ratio is   1.7
   *    * trail    1000 numbers,use time   0.018 , ratio is   3.6
   *    * trail    2000 numbers,use time   0.097 , ratio is   5.4
   *    * trail    4000 numbers,use time   0.427 , ratio is   4.4
   *    * trail    8000 numbers,use time   1.734 , ratio is   4.1
   *    * trail   16000 numbers,use time   6.815 , ratio is   3.9
   *    * trail   32000 numbers,use time  28.101 , ratio is   4.1
   *    * trail   64000 numbers,use time 115.694 , ratio is   4.1
   *
   *       *     可以得出 THREESUMFAST 当 n *2 时,ratio 是 4 .
   *    *     3600 = 115.694 * 2^x
   *          x ~ 2.5
   *    *     64000 * 2^2.5 ~ 362038
   *    *     p = logn
   *    *     p ~ 12.8
   *
   *
   *
   * </code> <code>
   *       * ======= start THREESUM =======
   *    *    * trail     250 numbers,use time   0.004 , ratio is   1.0
   *    *    * trail     500 numbers,use time   0.027 , ratio is   6.8
   *    *    * trail    1000 numbers,use time   0.053 , ratio is   2.0
   *    *    * trail    2000 numbers,use time   0.400 , ratio is   7.5
   *    *    * trail    4000 numbers,use time   3.033 , ratio is   7.6
   *    *    * trail    8000 numbers,use time  24.105 , ratio is   7.9
   *    *    * trail   16000 numbers,use time 199.346 , ratio is   8.3
   *
   *       *     可以得出 THREESUM 当 n *2 时,ratio 是 8 .
   *    *     3600 = 199.346 * 8^x
   *    *     x ~ 1.4
   *    *     16000 * 2^1.4 ~ 42224
   *    *     p = logn
   *    *     p ~ 10.7
   * </code>
   */
  public static void main(String[] args) {
    //    StdOut.println(Math.log(42224));
    //
    //    double p = 1;
    //    while (Math.pow(8, p) - 18.059053104 < 0.01) {
    //      p = p + 0.01;
    //    }
    //    StdOut.println(p);
    StdOut.println("======= start TWOSUMFAST =======");
    doublelingRatio(TWOSUMFAST);
    StdOut.println("======= start TWOSUM =======");
    doublelingRatio(TWOSUM);
    StdOut.println("======= start THREESUMFAST =======");
    doublelingRatio(THREESUMFAST);
    StdOut.println("======= start THREESUM =======");
    doublelingRatio(THREESUM);
  }

  private static void doublelingRatio(String method) {
    double prev = timeTrial(125, method);
    for (int n = 250; n < 100000; n += n) {
      double time = timeTrial(n, method);
      StdOut.printf("trail %7d numbers,use time %7.3f , ratio is %5.1f\n", n, time, time / prev);
      prev = time;
    }
  }

  public static double timeTrial(int n, String method) {
    int MAXIMUM_INTEGER = 1000000;
    int[] a = new int[n];

    if (method.equals(THREESUMFAST)) {
      // ThreeSumFast 原来要求不重复数字
      HashSet<Integer> set = new HashSet<>();
      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        if (set.contains(a[i])) {
          i--;
        } else {
          set.add(a[i]);
        }
      }
    } else {
      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
      }
    }

    Stopwatch timer = new Stopwatch();
    switch (method) {
      case TWOSUMFAST:
        e01_04_04.TwoSumFast.count(a);
        break;
      case TWOSUM:
        e01_04_04.TwoSum.count(a);
        break;
      case THREESUMFAST:
        ThreeSumFast.count(a);
        break;
      case THREESUM:
        ThreeSum.count(a);
        break;
      default:
        break;
    }
    return timer.elapsedTime();
  }
}
