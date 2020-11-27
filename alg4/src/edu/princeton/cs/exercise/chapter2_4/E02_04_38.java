package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.4.38 Exercise driver. Write an exercise driver client program that uses the methods in our
 * priority-queue interface of Algorithm 2.6 on difficult or pathological cases that might turn up
 * in practical applications. Simple examples include keys that are already in order, keys in
 * reverse order, all keys the same, and sequences of keys having only two distinct values.
 *
 * <p>练习驱动.编写一个练习驱动客户端程序在可能出现在实际应用中的困难和病态的情况中使用算法2.6中的优先队列接口方法.<br>
 * 简单的例子中包含有序键的情况,倒序键的情况,所有键相同的情况,还有键只有两种不同值的情况
 *
 * @author LeonChen
 * @since 11/18/20
 */
class E02_04_38 {
  static boolean run = true;

  private static final String MODE_ORDER = "order";
  private static final String MODE_REVERSE_ORDER = "reverseOrder";
  private static final String MODE_SAME_KEY = "sameKey";
  private static final String MODE_TWO_DISTINCT_KEY = "twoDistinctKey";

  /** @param args */
  public static void main(String[] args) {
    int n = 50000;
    int t = 5;

    String[] modes =
        new String[] {MODE_ORDER, MODE_REVERSE_ORDER, MODE_SAME_KEY, MODE_TWO_DISTINCT_KEY};

    for (String mode : modes) {
      for (int i = 1000; i < n; i *= 2) {
        double totalTime = 0;
        double avgTime = 0;
        for (int j = 0; j < t; j++) {
          Integer[] a = genArr(mode, i);
          Stopwatch stopwatch = new Stopwatch();
          testN(i, a);
          totalTime += stopwatch.elapsedTime();
        }
        avgTime = totalTime / t;
        StdOut.println("when n = " + i + ", mode = " + mode + " average time = " + avgTime);
      }
    }
  }

  private static Integer[] genArr(String mode, int n) {
    Integer[] a = new Integer[n];
    switch (mode) {
      case MODE_ORDER:
        for (int i = 0; i < n; i++) {
          a[i] = i;
        }
        break;
      case MODE_REVERSE_ORDER:
        for (int i = n - 1; i >= 0; i--) {
          a[i] = i;
        }
        break;
      case MODE_SAME_KEY:
        for (int i = 0; i < n; i++) {
          a[i] = 1;
        }
        break;
      case MODE_TWO_DISTINCT_KEY:
        for (int i = 0; i < n; i++) {
          if (StdRandom.uniform() > 0.5) {
            a[i] = 1;
          } else {
            a[i] = 0;
          }
        }
        break;
    }
    return a;
  }

  private static void testN(int n, Integer[] a) {
    MaxPQ<Integer> maxPQ = new MaxPQ<>();
    for (int i = 0; i < a.length; i++) {
      maxPQ.insert(a[i]);
    }
    for (int i = 0; i < a.length; i++) {
      maxPQ.delMax();
    }
  }
}
