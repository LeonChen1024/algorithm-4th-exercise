package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.math.BigInteger;

/**
 * 1.4.2 Modify ThreeSum to work properly even when the int values are so large that adding two of
 * them might cause overflow.
 *
 * <p>修改 ThreeSum 使得它即便在 int 数值非常大甚至两个相加会导致溢出的情况下也能工作.
 *
 * @author LeonChen
 * @since 3/1/20
 */
class e01_04_02 {

  /**
   * 这里有几种思路<br>
   * 1.直接修改使用的数据结构,换成BigInteger ,Long 等.通过扩充了最大数值,使得原本 int 的溢出情况得到解决.<br>
   * 这么做的优点是简单方便,代码简单不易出错.缺点是当数字再次扩大的时候依然会出现溢出的情况.<br>
   * 2.我们先来看下溢出的原因,就是数字的位数超过了数据结果所能容纳的位数,导致标志位被修改,数字变成不合理的值.这种<br>
   * 情况只会发生在两个正数相加或者两个负数相加的时候,一正一负由于本身是没有溢出的所以相加后更不可能发生溢出.<br>
   * 再根据题目的要求,如果三个数相加的时候出现了溢出,那么最多只会剩下一个数字可以加,那么这个时候无论如何都不可能变成 0.<br>
   * 因为最后一个数字不可能比溢出的数的绝对值还要大.所以只要出现了相加溢出那么这个项就可以排除了.
   *
   * @param args
   */
  public static void main(String[] args) {}

  public static class ThreeSum {

    // Do not instantiate.
    private ThreeSum() {}

    /**
     * Prints to standard output the (i, j, k) with {@code i < j < k} such that {@code a[i] + a[j] +
     * a[k] == 0}.
     *
     * @param a the array of integers
     */
    public static void printAll(int[] a) {
      int n = a.length;
      for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          for (int k = j + 1; k < n; k++) {
            if (a[i] + a[j] + a[k] == 0) {
              StdOut.println(a[i] + " " + a[j] + " " + a[k]);
            }
          }
        }
      }
    }

    /**
     * 方法 1 的方案
     *
     * @param a
     * @return
     */
    public static int count1(int[] a) {
      int n = a.length;
      int count = 0;

      BigInteger bigInteger;

      for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          for (int k = j + 1; k < n; k++) {
            bigInteger = BigInteger.valueOf(a[i]);
            bigInteger = bigInteger.add(BigInteger.valueOf(a[j]));
            bigInteger = bigInteger.add(BigInteger.valueOf(a[k]));

            if (bigInteger.intValue() == 0) {
              count++;
            }
          }
        }
      }
      return count;
    }

    /**
     * 方法 2 的方案.
     *
     * @param a
     * @return
     */
    public static int count2(int[] a) {
      int n = a.length;
      int count = 0;
      for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          // 两个数的符号位. true 都是正数, false 都是负数,null 一正一负或者带 0
          Boolean flag = null;
          if (a[i] > 0 && a[j] > 0) {
            flag = true;
          } else if (a[i] < 0 && a[j] < 0) {
            flag = false;
          }
          int sum2 = a[i] + a[j];
          if (flag != null && flag && (sum2 <= 0)) {
            // 两正相加结果却是负数或 0,发生溢出
            StdOut.println("overflow ");
            continue;
          } else if (flag != null && !flag && (sum2 >= 0)) {
            // 两负相加结果却是正数或 0,发生溢出
            StdOut.println("overflow ");
            continue;
          }
          for (int k = j + 1; k < n; k++) {
            if (sum2 > 0 && a[k] > 0) {
              flag = true;
            } else if (sum2 < 0 && a[k] < 0) {
              flag = false;
            } else {
              flag = null;
            }
            int sum3 = sum2 + a[k];
            if (flag != null && flag && (sum3 <= 0)) {
              // 两正相加结果却是负数或 0,发生溢出
              StdOut.println("overflow ");
              continue;
            } else if (flag != null && !flag && (sum3 >= 0)) {
              // 两负相加结果却是正数或 0,发生溢出
              StdOut.println("overflow ");
              continue;
            }
            if (a[i] + a[j] + a[k] == 0) {
              count++;
            }
          }
        }
      }
      return count;
    }

    /**
     * 原来的 count 方法.
     *
     * @param a
     * @return
     */
    public static int count(int[] a) {
      int n = a.length;
      int count = 0;
      for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          for (int k = j + 1; k < n; k++) {
            if (a[i] + a[j] + a[k] == 0) {
              count++;
            }
          }
        }
      }
      return count;
    }

    /**
     * Reads in a sequence of integers from a file, specified as a command-line argument; counts the
     * number of triples sum to exactly zero; prints out the time to perform the computation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
      int[] a = new int[] {2000000000, 2000000000, -2000000000, -2000000000};
      //      int[] a = new int[] {2000000000, 2000000000, -2000000000, -2000000000, -2, +1, +1};

      Stopwatch timer = new Stopwatch();
      int count = count1(a);
      StdOut.println("elapsed time = " + timer.elapsedTime());
      StdOut.println(count);
    }
  }
}
