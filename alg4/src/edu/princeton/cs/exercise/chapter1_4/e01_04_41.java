package edu.princeton.cs.exercise.chapter1_4;

import java.util.HashSet;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.ThreeSumFast;

/**
 * 1.4.41 Running times. Estimate the amount of time it would take to run TwoSumFast, TwoSum,
 * ThreeSumFast and ThreeSum on your computer to solve the problems for a file of 1 million numbers.
 * Use DoublingRatio to do so.
 *
 * <p>运行时间.估算 TwoSumFast, TwoSum, ThreeSumFast 和 ThreeSum 在你电脑上对一个1百万数字的文件<br>
 * 执行的时间.使用DoublingRatio来做这个测试
 *
 * @author LeonChen
 * @since 4/3/20
 */
class e01_04_41 {
  private static final String TWOSUMFAST = "TwoSumFast";
  private static final String TWOSUM = "TwoSum";
  private static final String THREESUMFAST = "ThreeSumFast";
  private static final String THREESUM = "ThreeSum";

  /** */
  public static void main(String[] args) {
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
    for (int n = 250; n < 50000; n += n) {
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
