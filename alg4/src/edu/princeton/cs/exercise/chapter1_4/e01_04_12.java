package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.12 Write a program that, given two sorted arrays of N int values, prints all elements that
 * appear in both arrays, in sorted order. The running time of your program should be proportional
 * to N in the worst case.
 *
 * <p>编写一个程序,给定两个有序数组有N个int值,打印出所有同时出现在两个数组中的元素,按照排好的顺序.你的程序的运行时间在<br>
 * 最差的情况下应该和N成正比.
 *
 * @author LeonChen
 * @since 3/10/20
 */
class e01_04_12 {

  /**
   * 因为是两组排序过的数组,所以采用类似合并排序的想法来做查找,当a1的数大于a2,那么a2的指针向后移动一位,反之a1移动<br>
   * 一位,直到碰到相同的数则打印.
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] a1 = new int[] {1, 3, 4, 6, 8, 9, 12, 21, 25, 27, 35, 57, 66, 68, 99};
    int[] a2 = new int[] {1, 2, 6, 7, 10, 22, 31, 35, 47, 55, 57, 76, 78, 89};

    findSameInTwoArrays(a1, a2);
  }

  private static void findSameInTwoArrays(int[] a1, int[] a2) {

    int a1Index = 0;
    int a2Index = 0;

    while (a1Index < a1.length && a2Index < a2.length) {
      if (a1[a1Index] < a2[a2Index]) {
        a1Index++;
      } else if (a1[a1Index] > a2[a2Index]) {
        a2Index++;
      } else {
        StdOut.println(a1[a1Index]);
        a1Index++;
        a2Index++;
      }
    }
  }
}
