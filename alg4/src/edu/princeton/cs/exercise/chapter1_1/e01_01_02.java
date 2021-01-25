package edu.princeton.cs.exercise.chapter1_1;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.2 Give the type and value of each of the following expressions: <br>
 * a. (1 + 2.236)/2 <br>
 * b.1+2+3+4.0 <br>
 * c.4.1>=4 <br>
 * d.1+2+"3"
 *
 * 给出下面表达式的类型和值
 */
class e01_01_02 {

  /**
   */
  public static void main(String[] args) {

    //      a. (1 + 2.236)/2
    //      浮点型参与计算,所以值会转为浮点型
    StdOut.println((1 + 2.236) / 2);

    //      b.1+2+3+4.0
    //      浮点型参与计算,所以值会转为浮点型
    StdOut.println(1 + 2 + 3 + 4.0);

    //      c.4.1>=4
    //      判断逻辑,值为boolean
    StdOut.println(4.1 >= 4);

    //      1+2+"3"
    //      加上字符串,值转为字符串
    StdOut.println(1 + 2 + "3");
  }
}
