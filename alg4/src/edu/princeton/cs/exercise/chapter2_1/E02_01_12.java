package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.1.12 Instrument shellsort to print the number of compares divided by the array size for each
 * increment. Write a test client that tests the hypothesis that this number is a small constant, by
 * sorting arrays of random Double values, using array sizes that are increasing powers of 10,
 * starting at 100.
 *
 * <p>测试 shellsort 来打印出每次増长时对比次数除以数组大小的值.编写一个测试客户端测试假说这个数字是一个小常数,<br>
 * 在对一个随机 Double 数组排序的时候,数组的大小以10的幂次增长,从100开始.
 *
 * @author LeonChen
 * @since 5/15/20
 */
class E02_01_12 {

  /**
   * <code>
   *     ============ total num = 100 , start =================================
   *
   * increment num is 40 , compare ratio is 0.97
   * increment num is 13 , compare ratio is 1.72
   * increment num is 4 , compare ratio is 2.22
   * increment num is 1 , compare ratio is 2.62
   * ============ total num = 1000 , start =================================
   *
   * increment num is 364 , compare ratio is 1.077
   * increment num is 121 , compare ratio is 1.778
   * increment num is 40 , compare ratio is 2.358
   * increment num is 13 , compare ratio is 2.94
   * increment num is 4 , compare ratio is 3.1
   * increment num is 1 , compare ratio is 2.632
   * ============ total num = 10000 , start =================================
   *
   * increment num is 9841 , compare ratio is 0.0242
   * increment num is 3280 , compare ratio is 1.1705
   * increment num is 1093 , compare ratio is 1.8321
   * increment num is 364 , compare ratio is 2.4166
   * increment num is 121 , compare ratio is 2.9323
   * increment num is 40 , compare ratio is 3.4187
   * increment num is 13 , compare ratio is 4.8825
   * increment num is 4 , compare ratio is 4.5214
   * increment num is 1 , compare ratio is 2.7979
   * ============ total num = 100000 , start =================================
   *
   * increment num is 88573 , compare ratio is 0.17118
   * increment num is 29524 , compare ratio is 1.20817
   * increment num is 9841 , compare ratio is 1.8432
   * increment num is 3280 , compare ratio is 2.43628
   * increment num is 1093 , compare ratio is 3.01513
   * increment num is 364 , compare ratio is 3.7836
   * increment num is 121 , compare ratio is 4.87944
   * increment num is 40 , compare ratio is 6.45582
   * increment num is 13 , compare ratio is 11.98533
   * increment num is 4 , compare ratio is 4.25511
   * increment num is 1 , compare ratio is 2.76915
   * ============ total num = 1000000 , start =================================
   *
   * increment num is 797161 , compare ratio is 0.304069
   * increment num is 265720 , compare ratio is 1.233144
   * increment num is 88573 , compare ratio is 1.913365
   * increment num is 29524 , compare ratio is 2.4809
   * increment num is 9841 , compare ratio is 3.083058
   * increment num is 3280 , compare ratio is 3.810961
   * increment num is 1093 , compare ratio is 5.056991
   * increment num is 364 , compare ratio is 6.435881
   * increment num is 121 , compare ratio is 9.787041
   * increment num is 40 , compare ratio is 11.877685
   * increment num is 13 , compare ratio is 11.697713
   * increment num is 4 , compare ratio is 4.610682
   * increment num is 1 , compare ratio is 2.756274
   * </code>
   */
  public static void main(String[] args) {
    int max = 100000000;
    for (int i = 100; i < max; i *= 10) {
      Double[] arr = new Double[i];
      for (int j = 0; j < i; j++) {
        double v = StdRandom.uniform();
        arr[j] = v;
      }
      StdOut.println(
          "============ total num = " + i + " , start =================================");
      sort(arr);
    }
  }

  /**
   * Rearranges the array in ascending order, using the natural order.
   *
   * @param a the array to be sorted
   */
  public static void sort(Comparable[] a) {
    int n = a.length;

    int incrementTime = 1;
    int incrementNum = 1;

    while ((incrementNum * 3 + 1) < n) {
      incrementTime++;
      incrementNum = incrementNum * 3 + 1;
    }

    int[] incrementArr = new int[incrementTime];
    int index = 0;
    while (incrementNum > 0) {
      incrementArr[index] = incrementNum;
      incrementNum--;
      incrementNum = incrementNum / 3;
      index++;
    }
    double compareTimes = 0;

    // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
    index = 0;
    int h;
    StdOut.println();
    do {
      compareTimes = 0;
      h = incrementArr[index++];
      // h-sort the array
      for (int i = h; i < n; i++) {
        compareTimes++;
        for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
          exch(a, j, j - h);
          compareTimes++;
        }
      }
      double compareRatio = compareTimes / n;
      StdOut.println("increment num is " + h + " , compare ratio is " + compareRatio);
      assert isHsorted(a, h);
    } while (index < incrementArr.length);

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

  // print array to standard output
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.print(a[i] + " ");
    }
  }
}
