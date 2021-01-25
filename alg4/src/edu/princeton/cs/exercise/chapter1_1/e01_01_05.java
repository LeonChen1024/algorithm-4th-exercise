package edu.princeton.cs.exercise.chapter1_1;

import edu.princeton.cs.algs4.StdOut;
/**
 * 1.1.5 Write a code fragment that prints true if the double variables x and y are both strictly
 * between 0 and 1 and false otherwise.
 *
 * 编写一个代码片段当 x 和 y 都在 0 到 1 之间的时候打印true,否则打印 false
 */
class e01_01_05 {



  /**
   */
  public static void main(String[] args) {
    double x = 0.5;
    double y = 1;
    if (0 < x && x < 1 && 0 < y && y < 1) {
      StdOut.println(true);
    } else {
      StdOut.println(false);
    }
  }
}
