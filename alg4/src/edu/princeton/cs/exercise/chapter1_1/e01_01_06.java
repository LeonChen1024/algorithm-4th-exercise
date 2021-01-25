package edu.princeton.cs.exercise.chapter1_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.6 What does the following program print?<br>
 * <code>
 *     int f = 0;
 *     int g = 1;
 *     for (int i = 0; i <= 15; i++) {
 *       StdOut.println(f);
 *       f = f + g;
 *       g = f - g;
 *     }
 * </code>
 *
 * 下面的程序会打印出什么?
 */
class e01_01_06 {


  /**
   */
  public static void main(String[] args) {

    int f = 0;
    int g = 1;
    for (int i = 0; i <= 15; i++) {
      StdOut.println(f);
      f = f + g;
      g = f - g;
    }
  }
}
