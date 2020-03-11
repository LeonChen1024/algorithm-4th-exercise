package edu.princeton.cs.exercise.chapter1_4;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.8 Write a program to determine the number pairs of values in an input file that are equal. If
 * your first try is quadratic, think again and use Arrays.sort() to develop a linearithmic
 * solution.
 *
 * <p>编写一个程序来判断输入文件中值相等的数字对数量.如果你的初次尝试是quadratic级别的,再想想看并且使用 Arrays.sort() 来<br>
 * 开发一个linearithmic 级别的方案.
 *
 * @author LeonChen
 * @since 3/6/20
 */
class e01_04_08 {

  public static void main(String[] args) {
    In in = new In(new File("src/edu/princeton/cs/algs4/data/1Kints.txt"));
    int[] all = in.readAllInts();

    //    test
    all = new int[] {1, 1, 3, 2, 4, 6, 783, 456, 23, 56, 35, 6};
    // 方法内部使用了快排,时间复杂度是 O(n log(n))
    Arrays.sort(all);

    int num = 0;
    for (int i = 0; i < all.length - 1; i++) {
      if (all[i] == all[i + 1]) {
        num++;
      }
    }
    StdOut.println("");

    StdOut.println(num);
  }
}
