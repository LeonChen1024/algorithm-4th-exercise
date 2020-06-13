package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 2.1.30 Geometric increments. Run experiments to determine a value of t that leads to the lowest
 * running time of shellsort for random arrays for the increment sequence 1, ⎣t⎦,⎣t^2⎦,⎣t^3⎦,⎣t^4⎦,
 * . . . for N = 10^6. Give the values of t and the increment sequences for the best three values
 * that you find.
 *
 * <p>The following exercises describe various clients for helping to evaluate sorting methods. They
 * are intended as starting points for helping to understand performance properties, using random
 * data. In all of them, use time(), as in SortCompare, so that you can get more accurate results by
 * specifying more trials in the second command-line argument. We refer back to these exercises in
 * later sections when evaluating more sophisticated methods.
 *
 * <p>几何级递增.通过试验来证明一个t的值对于一个随机递增序列 1, ⎣t⎦,⎣t^2⎦,⎣t^3⎦,⎣t^4⎦,. . . 其中 N = 10^6<br>
 * 可以拥有最少的 shellsort 运行时间.给出三个最佳的t的值和递增序列.
 *
 * <p>下面的练习描述了多种客户端用来帮助评估排序方法.他们的目的是使用随机数据来帮助你开始理解性能特性.他们都像<br>
 * SortCompare一样使用了 time()方法,所以你可以通过再第二个命令行参数指定更多的trials来得到更精确的结果.<br>
 * 我们会在后面的章节中估算更多复杂方法的时候引用这里的练习.
 *
 * @author LeonChen
 * @since 5/27/20
 */
class E02_01_30 {

  /**
   * <code>
   *  when t = 2 , used time is 4.614999999999999
   *  when t = 3 , used time is 5.782
   *  when t = 4 , used time is 5.162666666666667
   *  when t = 5 , used time is 4.479666666666667
   *  when t = 6 , used time is 5.3566666666666665
   *  when t = 7 , used time is 5.178
   *  when t = 8 , used time is 5.250333333333333
   *  when t = 9 , used time is 5.272
   *  when t = 10 , used time is 5.548666666666667
   *  when t = 11 , used time is 6.101333333333333
   *  when t = 12 , used time is 6.509
   *  when t = 13 , used time is 7.021666666666667
   *  when t = 14 , used time is 7.612333333333333
   *  when t = 15 , used time is 7.201333333333333
   *  when t = 16 , used time is 9.863333333333333
   *  when t = 17 , used time is 9.136999999999999
   *  when t = 18 , used time is 7.831666666666667
   *  when t = 19 , used time is 8.094333333333333
   * </code> 大概可以看出规律,t越大效果越差,对于t5快于 t4 🙉t3 ,目前猜测是由于数组的随机特性导致的偶发现象,专门对 t=5<br>
   * 以下做一个增加trial到15的试验. <code>
   *  when t = 2 , used time is 4.020533333333333
   *  when t = 3 , used time is 4.236000000000001
   *  when t = 4 , used time is 4.470399999999999
   *  when t = 5 , used time is 4.785666666666667
   * </code> 样本越多,越接近我们得出的规律<br>
   * 所以t的值应该是 2,3,4
   *
   * @param args
   */
  public static void main(String[] args) {
    int N = (int) Math.pow(10, 6);
    int trial = 15;
    Comparable[] a = new Comparable[N];
    double usedTime = 0;

    for (int t = 2; t < 6; t++) {
      usedTime = 0;
      for (int i = 0; i < trial; i++) {
        for (int j = 0; j < N; j++) {
          a[j] = StdRandom.uniform(N);
        }

        Stopwatch stopwatch = new Stopwatch();
        sort(a, t);
        usedTime += stopwatch.elapsedTime();
      }
      usedTime = usedTime / trial;
      StdOut.println(" when t = " + t + " , used time is " + usedTime);
    }
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void sort(Comparable[] a, int t) {
    int n = a.length;

    // 3x+1 increment sequence:  1, ⎣t⎦,⎣t^2⎦,⎣t^3⎦,⎣t^4⎦ ...
    int h = 1;
    while (h < n / 3) h = h * t;

    while (h >= 1) {
      // h-sort the array
      for (int i = h; i < n; i++) {
        for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
          exch(a, j, j - h);
        }
      }
      assert isHsorted(a, h);
      h /= t;
    }
    assert isSorted(a);
  }

  /**
   * ************************************************************************* Helper sorting
   * functions. *************************************************************************
   */

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  // exchange a[i] and a[j]
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  /**
   * ************************************************************************* Check if array is
   * sorted - useful for debugging.
   * *************************************************************************
   */
  private static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) if (less(a[i], a[i - 1])) return false;
    return true;
  }

  // is the array h-sorted?
  private static boolean isHsorted(Comparable[] a, int h) {
    for (int i = h; i < a.length; i++) if (less(a[i], a[i - h])) return false;
    return true;
  }
}
