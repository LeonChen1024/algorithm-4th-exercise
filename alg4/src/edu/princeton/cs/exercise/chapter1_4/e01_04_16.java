package edu.princeton.cs.exercise.chapter1_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.16 Closest pair(in one dimension). Write a program that, given an array a[] of N double
 * values, finds a closest pair : two values whose difference is no greater than the the difference
 * of any other pair (in absolute value). The running time of your program should be linearithmic in
 * the worst case.
 *
 * <p>最接近的一对(一维的情况).编写一个程序,从给定的一个放着 N 个double值的数组 a[]中找到一对最接近的值:两个数<br>
 * 之间的差距不大于任意的其他对(使用绝对值比较). 在最差的情况下你的程序应该是一个 linearithmic 的运行时间.
 *
 * @author LeonChen
 * @since 3/13/20
 */
class e01_04_16 {

  /**
   * 思路和上一题类似.排序后进行求值.
   *
   * @param args
   */
  public static void main(String[] args) {
    double[] arr = new double[] {-4, -1.2, 3, 8, -1, 0, 0.7, 5, 2, -2.4, 2.4, 9, 22};

    findClosestPair(arr);
  }

  private static void findClosestPair(double[] arr) {
    double[] closestPair = new double[2];
    double minDiff = Double.MAX_VALUE;

    Arrays.sort(arr);
    // 最后一个数是和倒数第二个数比较,所以排除最后一个数,使用 arr.length -1
    for (int i = 0; i < arr.length - 2; i++) {
      double curDiff = Math.abs(arr[i] - arr[i + 1]);
      if (curDiff < minDiff) {
        closestPair[0] = arr[i];
        closestPair[1] = arr[i + 1];
        minDiff = curDiff;
      }
    }

    StdOut.println("min difference pair is [" + closestPair[0] + "," + closestPair[1] + "]");
  }
}
