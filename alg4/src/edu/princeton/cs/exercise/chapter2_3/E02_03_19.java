package edu.princeton.cs.exercise.chapter2_3;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.3.19 Median-of-5 partitioning. Implement a quicksort based on partitioning on the median of a
 * random sample of five items from the subarray. Put the items of the sample at the appropriate
 * ends of the array so that only the median participates in partitioning. Run doubling tests to
 * determine the effectiveness of the change, in comparison both to the standard algorithm and to
 * median-of-3 partitioning (see the previous exercise). Extra credit : Devise a median-of-5
 * algorithm that uses fewer than seven compares on any input.
 *
 * <p>使用5个数的中位数进行分割.实现一个分割基于从子数组中随机取样的5个项中取出中位数的快排.将采样中的项放入合适<br>
 * 的数组末端使得只有中位数参加分割.运行倍速测试来判断这个改变的效率,将这个算法和标准算法以及3个数的中位数分割算法<br>
 * (见前面的练习)进行对比.额外的信息:设计一个只需要7次🕐以内对比的五个数取中位数算法.
 *
 * @author LeonChen
 * @since 8/18/20
 */
public class E02_03_19 {

  /**
   * <code>
   * n is 1000 , original 0.003 seconds , Median-of-3 0.003 seconds , Median-of-5 0.002 seconds
   * n is 2000 , original 0.001 seconds , Median-of-3 0.002 seconds , Median-of-5 0.0 seconds
   * n is 4000 , original 0.002 seconds , Median-of-3 0.002 seconds , Median-of-5 0.002 seconds
   * n is 8000 , original 0.004 seconds , Median-of-3 0.003 seconds , Median-of-5 0.003 seconds
   * n is 16000 , original 0.005 seconds , Median-of-3 0.006 seconds , Median-of-5 0.006 seconds
   * n is 32000 , original 0.006 seconds , Median-of-3 0.004 seconds , Median-of-5 0.005 seconds
   * n is 64000 , original 0.015 seconds , Median-of-3 0.02 seconds , Median-of-5 0.016 seconds
   * n is 128000 , original 0.034 seconds , Median-of-3 0.04 seconds , Median-of-5 0.042 seconds
   * n is 256000 , original 0.083 seconds , Median-of-3 0.07 seconds , Median-of-5 0.083 seconds
   * n is 512000 , original 0.21 seconds , Median-of-3 0.227 seconds , Median-of-5 0.198 seconds
   * n is 1024000 , original 0.303 seconds , Median-of-3 0.294 seconds , Median-of-5 0.338 seconds
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
      quicksortMedianof5(c);
      double fiveTime = timer.elapsedTime();

      StdOut.println(
          "n is "
              + n
              + " , original "
              + originTime
              + " seconds , Median-of-3 "
              + threeTime
              + " seconds , Median-of-5 "
              + fiveTime
              + " seconds ");
    }
  }

  private static void quicksortMedianof5(Comparable[] b) {
    StdRandom.shuffle(b);

    sort(b, 0, b.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    if (hi - lo + 1 < 5) {
      // 小于5个使用插入排序
      Insertion.sort(a, lo, hi);
      return;
    }

    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
    assert isSorted(a, lo, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;

    // 假设为 a b c d e 五个数字, 首先对 b c 排序 ,使得 b<c
    if (less(a[lo + 2], a[lo + 1])) exch(a, lo + 2, lo + 1);
    // 然后再排序 d e ,使得 d<e
    if (less(a[lo + 4], a[lo + 3])) exch(a, lo + 4, lo + 3);

    // 这时满足 b < c, d < e 比较 b d，把较小的一组放到 b c 的位置上去
    if (less(a[lo + 3], a[lo + 1])) {
      exch(a, lo + 1, lo + 3);
      exch(a, lo + 2, lo + 4);
    }

    // 这时满足 b < c, b < d < e，即 b 是 b c d e 中的最小值
    // 交换 a 和 b
    exch(a, lo, lo + 1);

    // 重新排序 b c
    if (less(a[lo + 2], a[lo + 1])) exch(a, lo + 2, lo + 1);

    // 这时再次满足 b < c, d < e
    // 比较 b d，把最小的一组放到 b c 的位置上去
    if (less(a[lo + 3], a[lo + 1])) {
      exch(a, lo + 1, lo + 3);
      exch(a, lo + 2, lo + 4);
    }

    // 这时 a 和 b 为五个数中的最小值和次小值（顺序不固定，a 也可以是次小值）
    // 最后比较 c 和 d，较小的那一个即为中位数（即第三小的数）
    if (less(a[lo + 3], a[lo + 2])) exch(a, lo + 3, lo + 2);

    // 此时 c 即为中位数
    exch(a, lo, lo + 2);

    // d e 放到数组末尾充当哨兵
    exch(a, lo + 3, hi);
    exch(a, lo + 4, hi - 1);

    // 调整指针位置，前两位和后两位都已经在合适位置了
    j -= 2;
    i += 2;

    Comparable v = a[lo];
    while (true) {

      // find item on lo to swap
      while (less(a[++i], v)) {}

      // find item on hi to swap
      while (less(v, a[--j])) {}

      // check if pointers cross
      if (i >= j) break;

      exch(a, i, j);
    }

    // put partitioning item v at a[j]
    exch(a, lo, j);

    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    return j;
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) if (less(a[i], a[i - 1])) return false;
    return true;
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
