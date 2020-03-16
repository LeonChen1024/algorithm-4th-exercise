package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.15 Faster 3-sum . As a warmup, develop an implementation TwoSumFaster that uses a linear
 * algorithm to count the pairs that sum to zero after the array is sorted (instead of the
 * binary-search-based linearithmic algorithm). Then apply a similar idea to develop a quadratic
 * algorithm for the 3-sum problem.
 *
 * <p>更快的 3-sum. 做为一个热身,开发一个 TwoSumFaster 使用了一个 线性算法来计算有序数组中和为零的对(而不是使用<br>
 * 基于二分搜索的线性对数算法).然后使用一个类似的办法来开发一个平方级的算法给 3-sum 问题.
 *
 * @author LeonChen
 * @since 3/12/20
 */
class e01_04_15 {

  /**
   * 思路和上一题 1.4.14 类似.
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] arr = new int[] {-4, -2, -2, -1, -1, 0, 0, 2, 2, 3, 5, 8, 9, 22};

    twoSumFaster(arr);
    threeSumFaster(arr);
  }

  private static void threeSumFaster(int[] arr) {
    int count = 0;
    for (int i = 0; i < arr.length - 2; i++) {
      int lo = i + 1;
      int hi = arr.length - 1;

      while (lo < hi) {
        int sum = arr[i] + arr[lo] + arr[hi];

        if (sum > 0) {
          hi--;
        } else if (sum < 0) {
          lo++;
        } else {
          int total = 1;
          // 由于有重复的可能,所以需要计算重复数字的次数.也就是两边重复数字的乘积.如:左边两个-2,右边两个2,那个就有4种组合.
          int temp = lo;
          int sameNumLow = 0;
          int sameNumHi = 0;
          while (arr[temp] == arr[lo]) {
            sameNumLow++;
            temp++;
          }

          if (temp - 1 == hi) {
            // 两数相等导致的和为零要特殊处理.
            for (int j = 1; j < sameNumLow; j++) {
              total = total * j;
            }

          } else {
            temp = hi;
            while (arr[temp] == arr[hi]) {
              sameNumHi++;
              temp--;
            }

            total = sameNumHi * sameNumLow;
          }

          for (int j = 0; j < total; j++) {
            StdOut.println("符合的组合 : [" + arr[i] + "," + arr[lo] + "," + arr[hi] + "]");
          }

          count = count + total;

          hi = hi - sameNumHi;
          lo = lo + sameNumLow;
        }
      }
    }
    StdOut.println("total pair = " + count);
  }

  private static void twoSumFaster(int[] arr) {
    int lo = 0;
    int hi = arr.length - 1;
    int count = 0;

    while (lo < hi) {
      int sum = arr[lo] + arr[hi];

      if (sum > 0) {
        hi--;
      } else if (sum < 0) {
        lo++;
      } else {

        int total = 1;
        // 由于有重复的可能,所以需要计算重复数字的次数.也就是两边重复数字的乘积.如:左边两个-2,右边两个2,那个就有4种组合.
        int temp = lo;
        int sameNumLow = 0;
        int sameNumHi = 0;
        while (arr[temp] == arr[lo]) {
          sameNumLow++;
          temp++;
        }

        if (temp - 1 == hi) {
          // 两数相等导致的和为零要特殊处理.
          for (int j = 1; j < sameNumLow; j++) {
            total = total * j;
          }
        } else {
          temp = hi;
          while (arr[temp] == arr[hi]) {
            sameNumHi++;
            temp--;
          }
          total = sameNumHi * sameNumLow;
        }
        for (int i = 0; i < total; i++) {
          StdOut.println("符合的组合 : [" + arr[lo] + "," + arr[hi] + "]");
        }

        count = count + total;

        hi = hi - sameNumHi;
        lo = lo + sameNumLow;
      }
    }
    StdOut.println("total pair = " + count);
  }
}
