package edu.princeton.cs.exercise.chapter1_4;

/**
 * 1.4.26 3-collinearity. Suppose that you have an algorithm that takes as input N distinct points
 * in the plane and can return the number of triples that fall on the same line. Show that you can
 * use this algorithm to solve the 3-sum problem. Strong hint : Use algebra to show that (a, a^3),
 * (b, b^3), and (c, c^3) are collinear if and only if a + b + c = 0.
 *
 * <p>3 点共线.假设你又一个算法接收一个平面内 N个不同的点作为输入并且可以返回三个在同一条直线上的点.<br>
 * 证明你可以使用这个算法来解决 3-sum 问题.强力提示:使用代数证明当且仅当 a + b + c = 0 的时候<br>
 * (a, a^3), (b, b^3), 和 (c, c^3)是共线的
 *
 * @author LeonChen
 * @since 3/22/20
 */
class e01_04_26 {

  /**
   * 首先,提示要证明当且仅当 a + b + c = 0 的时候 (a, a^3), (b, b^3), 和 (c, c^3)是共线的.<br>
   * 也就是说每两点组成的直线的斜率相同. (b^3 - a^3)/(b - a) = (c^3 - b^3)/(c - b) 用立方差公式化简，有：<br>
   * b^2 + ab + a^2 = c^2 + bc + b^2 ; 消去 b^2， c^2 +bc - a^2 - ab = 0; 用十字相乘法进行因式分解<br>
   * (a + b + c)(c - a) = 0; 解方程得 c = -a - b 或 c = a;可得当 c != a 时，当且仅当 a + b + c = 0 时 A, B, C 三点共线。
   *
   * @param args
   */
  public static void main(String[] args) {}
}
