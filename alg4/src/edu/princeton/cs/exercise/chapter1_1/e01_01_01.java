package edu.princeton.cs.exercise.chapter1_1;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Give the value of each of the following expressions: <br>
 *     a. (0+15)/2 <br>
 *     b. 2.0e-6 * 100000000.1 <br>
 *     c.true && false || true && true
 *
 * 给出下面表达式的值
 */
class e01_01_01 {
  public static void main(String[] args) {
    // a. (0+15)/2
    // java 整型除法保留整数
    StdOut.println((0 + 15) / 2);

    // b. 2.0e-6 * 100000000.1
    // e代表以10为底,后面跟的数n 代表10 n次方, 考察该表达式和 * 法的优先级. n次方优先于乘法. 因为有浮点型计算,
    // 所以输出为浮点型
    StdOut.println(2.0e-6 * 100000000.1);

    // c. true && false || true && true
    // && 计算优先级 高于 ||
    StdOut.println(true && false || true && true);
  }
}
