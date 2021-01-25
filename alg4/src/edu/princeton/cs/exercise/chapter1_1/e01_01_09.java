package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.9 Write a code fragment that puts the binary representation of a positive integer N into a
 * String s. Solution: Java has a built-in method Integer.toBinaryString(N) for this job, but the
 * point of the exercise is to see how such a method might be implemented. Here is a particularly
 * concise solution: <code>
 *      String s = "";
 *     for (int n = N; n > 0; n /= 2) s = (n % 2) + s;
 *     StdOut.println(s);
 * </code>
 *
 * 编写一个代码片段将一个正整数N的二进制表达放到一个字符串 s 中.方案:Java 有内置的Integer.toBinaryString(N)来做这个工作,
 * 但是这个练习的重点是看这样的一个方法要如何实现.这里有一个简介的方案.
 */
class e01_01_09 {

  /**
   */
  public static void main(String[] args) {
    int N = 23;
    // solution 1
    String s = "";
    for (int n = N; n > 0; n /= 2) s = (n % 2) + s;
    StdOut.println(s);

    // solution 2 使用移位与的思路, 将每一位的值取出放入字符串
    s = "";
    for (int i = 0; i < 32; i++) {
      s = ((N >> i) & 1) + s;
    }
    StdOut.println(s);
  }
}
