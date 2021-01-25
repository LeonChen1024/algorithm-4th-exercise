package edu.princeton.cs.exercise.chapter1_1;

import edu.princeton.cs.algs4.StdOut;


/**
 * 1.1.8 What do each of the following print?<br>
 * a. System.out.println('b'); <br>
 * b.System.out.println('b' + 'c');<br>
 * c. System.out.println((char) ('a' + 4));<br>
 * Explain each outcome.
 *
 * 下面每个选项各会打印出什么
 *
 * 解释每个选项
 */
class e01_01_08 {

  /**
   */
  public static void main(String[] args) {
    System.out.println('b');
    // 字符相加使用ASCII码想加
    System.out.println('b' + 'c');
    // 数字可以转为对应ASCII码的char
    System.out.println((char) ('a' + 4));
  }
}
