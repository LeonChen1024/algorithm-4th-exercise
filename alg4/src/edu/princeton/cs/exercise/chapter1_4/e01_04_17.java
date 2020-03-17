package edu.princeton.cs.exercise.chapter1_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.17 Farthest pair(in one dimension). Write a program that, given an array a[] of N double
 * values, finds a farthest pair : two values whose difference is no smaller than the the difference
 * of any other pair (in absolute value). The running time of your program should be linear in the
 * worst case.
 *
 * <p>差距最大的值对(一维的情况).编写一个程序,得出一个给定的N个double值的数组arr[]中差距最多的值对:两个数的差距<br>
 * 不比其他的值对小(使用绝对值对比). 你的程序在最差的情况下运行时间应该是线性的.
 *
 * @author LeonChen
 * @since 3/13/20
 */
class e01_04_17 {

  /**
   * 思路和上一题类似.排序后进行求值.
   *
   * @param args
   */
  public static void main(String[] args) {
    double[] arr = new double[] {-4, -1.2, 3, 8, -1, 0, 0.7, 5, 2, -2.4, 2.4, 9, 22};

    findFarthestPair(arr);
  }

  private static void findFarthestPair(double[] arr) {
    double[] farthestPair = new double[2];
    double maxDiff = Double.MIN_VALUE;

    Arrays.sort(arr);
    // 最后一个数是和倒数第二个数比较,所以排除最后一个数,使用 arr.length -1
    for (int i = 0; i < arr.length - 1; i++) {
      double curDiff = Math.abs(arr[i] - arr[i + 1]);
      if (curDiff > maxDiff) {
        farthestPair[0] = arr[i];
        farthestPair[1] = arr[i + 1];
        maxDiff = curDiff;
      }
    }

    StdOut.println("max difference pair is [" + farthestPair[0] + "," + farthestPair[1] + "]");
  }
}
