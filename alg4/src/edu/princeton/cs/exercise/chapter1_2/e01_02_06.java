package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.6 A string s is a circular rotation of a string t if it matches when the characters are
 * circularly shifted by any number of positions; e.g ., ACTGACG is a circular shift of TGACGAC, and
 * vice versa. Detecting this condition is important in the study of genomic sequences. Write a
 * program that checks whether two given strings s and t are circular shifts of one another. Hint :
 * The solution is a one-liner with indexOf(), length(), and string concatenation.
 *
 * <p>一个字符串 s 可以通过 t 循环位移得到的时候,s是t 的环形自转.比如, ACTGACG 是 TGACGAC 的循环移位,反之亦然.<br>
 * 这个条件检查在学习几何序列中是十分重要的.编写一个程序检查两个给定字符串 s 和 t 是不是彼此的循环移位.提示:<br>
 * 解决方案是使用 indexOf(), length(), 和 string 连接的单行代码即可.
 *
 * @author LeonChen
 * @since 12/5/19
 */
class e01_02_06 {

  public static void main(String[] args) {

    String s = "ACTGACG";
    String t = "TGACGAC";
    // 循环移位一个字符串的值都可以在重复连接这个字符串中找到. 比如 TGACGAC 右移一位 可以在 TGACGA CTGACGA C 找到,
    // 左移亦然.
    boolean isCircularRotation = s.length() == t.length() && (t + t).contains(s);
    StdOut.println("is Circular rotation " + isCircularRotation);
  }
}
