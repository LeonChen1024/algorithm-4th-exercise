package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.7 What does the following recursive function return? <code>
 *     public static String mystery(String s) {
 *     int N = s.length();
 *     if (N <= 1) return s;
 *     String a = s.substring(0, N / 2);
 *     String b = s.substring(N / 2, N);
 *     return mystery(b) + mystery(a);
 *   }
 * </code>
 *
 * @author LeonChen
 * @since 12/5/19
 */
class e01_02_07 {

  public static void main(String[] args) {

    StdOut.println(mystery("1234567"));
  }

  /**
   * 一半一半的对调位置,到最后实现反转字符顺序的效果
   *
   * @param s
   * @return
   */
  public static String mystery(String s) {
    int N = s.length();
    if (N <= 1) return s;
    String a = s.substring(0, N / 2);
    String b = s.substring(N / 2, N);
    return mystery(b) + mystery(a);
  }
}
