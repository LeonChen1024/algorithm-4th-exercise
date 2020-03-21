package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.20 Bitonic search. An array is bitonic if it is comprised of an increasing sequence of
 * integers followed immediately by a decreasing sequence of integers. Write a program that, given a
 * bitonic array of N distinct int values, determines whether a given integer is in the array. Your
 * program should use ~3lg N compares in the worst case.
 *
 * <p>Bitonic search.一个数组如果他是由一组不同的数字上升之后再下降的曲线的话那么我们叫他 bitonic,判断一个<br>
 * 给定的数字是否在数组中.你的程序在最差的情况下应该是 3lgN 的时间复杂度.
 *
 * @author LeonChen
 * @since 3/17/20
 */
class e01_04_20 {

  private static final int LEFT = 1;
  private static final int RIGHT = 2;
  private static final int NOMAL = 0;

  /**
   * 首先,我们可以想到一个最直接的办法,先用二分法找到交替趋势的最大点,然后对两边进行二分差找.<br>
   * 这个算法还可以再优化吗?我们可以使用二分法的时候同时和预期值做比较.有这么一个规律,这个数组的大小的趋势像<br>
   * 一座山的形状,也就是说当当前值小于目标值的时候,我们就往大的方向查找,当第一次出现当前值大于目标值的时候,我们就需要往<br>
   * 两边查找,而之后碰到当前值大于目标值的时候查找就是向单边方向的推进.
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] arr = new int[] {1, 4, 5, 7, 12, 14, 15, 20, 25, 27, 35, 30, 23, 20, 16, 6, 2};

    int target = 5;
    findNumInBitonic(target, arr, 0, arr.length - 1, NOMAL);
  }

  private static void findNumInBitonic(int target, int[] arr, int start, int end, int direct) {

    int mid = start + (end - start) / 2;
    while (start > end) {
      return;
    }
    if (arr[mid] == target) {
      StdOut.println("there is target in the array in index " + mid);
      return;
    } else if (arr[mid] < target) {
      if (arr[mid - 1] > arr[mid]) {
        findNumInBitonic(target, arr, start, mid - 1, direct);
      } else if (arr[mid + 1] > arr[mid]) {
        findNumInBitonic(target, arr, mid + 1, end, direct);
      } else {
        return;
      }
    } else {
      if (direct == NOMAL) {
        // 首次出现这个情况,向两边查找
        findNumInBitonic(target, arr, start, mid - 1, LEFT);
        findNumInBitonic(target, arr, mid + 1, end, RIGHT);
      } else if (direct == LEFT) {
        findNumInBitonic(target, arr, start, mid - 1, LEFT);
      } else {
        findNumInBitonic(target, arr, mid + 1, end, RIGHT);
      }
    }
  }
}
