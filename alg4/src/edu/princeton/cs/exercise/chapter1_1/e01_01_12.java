package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.12 What does the following code fragment print? <code>
 *     int[] a = new int[10];
 *     for (int i = 0; i < 10; i++) a[i] = 9 - i;
 *     for (int i = 0; i < 10; i++) a[i] = a[a[i]];
 *     for (int i = 0; i < 10; i++) System.out.println(i);
 * </code>
 *
 * 下面的代码片段会打印出什么?
 */
class e01_01_12 {

  /**
   */
  public static void main(String[] args) {
    int[] a = new int[10];
    for (int i = 0; i < 10; i++) a[i] = 9 - i;
    for (int i = 0; i < 10; i++) a[i] = a[a[i]];
    for (int i = 0; i < 10; i++) System.out.println(i);
  }
}
