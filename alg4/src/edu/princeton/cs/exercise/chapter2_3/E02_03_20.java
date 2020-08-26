package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of quicksort based on a main loop
 * where a subarray is popped from a stack to be partitioned, and the resulting subarrays are pushed
 * onto the stack. Note : Push the larger of the subarrays onto the stack first, which guarantees
 * that the stack will have at most lg N entries.
 *
 * <p>无递归的快排. 实现一个无递归版本的快排,使用一个主循环将要分割的子数组从栈里弹出,得到的结果子数组推入栈.注意:<br>
 * 先将更大的子数组推进栈,确保栈内最多有 lgN 个项.
 *
 * @author LeonChen
 * @since 8/19/20
 */
class E02_03_20 {

  /**
   * <code>
   * n is 1000 , original 0.004 seconds , Median-of-3 0.002 seconds , Without Recursive 0.005 seconds
   * n is 2000 , original 0.001 seconds , Median-of-3 0.001 seconds , Without Recursive 0.002 seconds
   * n is 4000 , original 0.002 seconds , Median-of-3 0.001 seconds , Without Recursive 0.003 seconds
   * n is 8000 , original 0.003 seconds , Median-of-3 0.003 seconds , Without Recursive 0.007 seconds
   * n is 16000 , original 0.003 seconds , Median-of-3 0.003 seconds , Without Recursive 0.009 seconds
   * n is 32000 , original 0.005 seconds , Median-of-3 0.006 seconds , Without Recursive 0.023 seconds
   * n is 64000 , original 0.028 seconds , Median-of-3 0.018 seconds , Without Recursive 0.029 seconds
   * n is 128000 , original 0.053 seconds , Median-of-3 0.046 seconds , Without Recursive 0.052 seconds
   * n is 256000 , original 0.097 seconds , Median-of-3 0.096 seconds , Without Recursive 0.126 seconds
   * n is 512000 , original 0.319 seconds , Median-of-3 0.264 seconds , Without Recursive 0.225 seconds
   * n is 1024000 , original 0.485 seconds , Median-of-3 0.489 seconds , Without Recursive 0.459 seconds
   * </code>
   */
  public static void main(String[] args) {

    for (int n = 1000; n < 2000000; n = n * 2) {
      Comparable[] a = new Comparable[n];

      for (int i = 0; i < n; i++) {
        a[i] = StdRandom.uniform(1000);
      }
      Comparable[] b = new Comparable[n];
      Comparable[] c = new Comparable[n];
      System.arraycopy(a, 0, b, 0, a.length);
      System.arraycopy(a, 0, c, 0, a.length);

      Stopwatch timer = new Stopwatch();
      Quick.sort(a);
      double originTime = timer.elapsedTime();

      timer = new Stopwatch();

      E02_03_18.quicksortMedianof3(b);
      double threeTime = timer.elapsedTime();

      timer = new Stopwatch();
      quicksortWithoutRecursive(c);
      double withoutRecTime = timer.elapsedTime();

      StdOut.println(
          "n is "
              + n
              + " , original "
              + originTime
              + " seconds , Median-of-3 "
              + threeTime
              + " seconds , Without Recursive "
              + withoutRecTime
              + " seconds ");
    }
  }

  private static void quicksortWithoutRecursive(Comparable[] b) {
    StdRandom.shuffle(b);
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(0);
    stack.push(b.length - 1);

    while (stack.size() != 0) {
      // 压入顺序是先 lo，再 hi，故弹出顺序是先 hi 再 lo
      int hi = stack.pop();
      int lo = stack.pop();

      if (hi <= lo) continue;

      int j = partition(b, lo, hi);

      // 让较大的子数组先入栈（先排序较小的子数组）
      if (j - lo > hi - j) {
        stack.push(lo);
        stack.push(j - 1);

        stack.push(j + 1);
        stack.push(hi);
      } else {
        stack.push(j + 1);
        stack.push(hi);

        stack.push(lo);
        stack.push(j - 1);
      }
    }
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    Comparable v = a[lo];
    while (true) {

      // find item on lo to swap
      while (less(a[++i], v)) {
        if (i == hi) break;
      }

      // find item on hi to swap
      while (less(v, a[--j])) {
        if (j == lo) break; // redundant since a[lo] acts as sentinel
      }

      // check if pointers cross
      if (i >= j) break;

      exch(a, i, j);
    }

    // put partitioning item v at a[j]
    exch(a, lo, j);

    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    return j;
  }

  private static boolean less(Comparable v, Comparable w) {
    if (v == w) return false; // optimization when reference equals
    return v.compareTo(w) < 0;
  }

  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
}
