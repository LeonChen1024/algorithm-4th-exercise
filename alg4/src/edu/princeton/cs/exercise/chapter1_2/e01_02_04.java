package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.4 What does the following code fragment print? <code>
 *     String string1 = "hello";
 *     String string2 = string1;
 *     string1 = "world";
 *     StdOut.println(string1);
 *     StdOut.println(string2);
 * </code>
 *
 * @author LeonChen
 * @since 12/4/19
 */
class e01_02_04 {

  public static void main(String[] args) {
    // 并不是修改引用地址,而是新建了一个对象
    String string1 = "hello";
    String string2 = string1;
    string1 = "world";
    StdOut.println(string1);
    StdOut.println(string2);
  }
}
