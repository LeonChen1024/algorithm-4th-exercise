package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.18 Consider the following recursive function: <code>
 *     public static int mystery(int a, int b) {
 *     if (b == 0) return 0;
 *     if (b % 2 == 0) return mystery(a + a, b / 2);
 *     return mystery(a + a, b / 2) + a;
 *   }
 * </code> What are the values of mystery(2, 25) and mystery(3, 11)? Given positive integers a and
 * b, describe what value mystery(a, b) computes. Answer the same question, but replace + with *
 * and replace return 0 with return 1.
 *
 * 思考下列递归函数
 *
 * mystery(2, 25) 和 mystery(3, 11) 的值会是什么?给出正整数 a 和 b,描述 mystery(a, b) 计算了什么.将 + 替换成 * 之后回答
 * 相同的问题
 */
class E1_1_18 {

  public static int mystery(int a, int b) {
    // a*2 的时候 b/2 , b/2如果有余数就多加个a . 所以是乘法.
    if (b == 0) return 0;
    if (b % 2 == 0) return mystery(a + a, b / 2);
    return mystery(a + a, b / 2) + a;
  }

  public static int mystery1(int a, int b) {
    // a*a 的时候 , b/2 , b/2 如果有余数的时候多乘一个a , 所以是 a^b.
    if (b == 0) return 1;
    if (b % 2 == 0) return mystery1(a * a, b / 2);
    return mystery1(a * a, b / 2) * a;
  }

  /**
   */
  public static void main(String[] args) {
    StdOut.println(mystery(2, 25));
    StdOut.println(mystery(3, 11));
    StdOut.println(mystery(5, 5));

    StdOut.println(mystery1(2, 25));
    StdOut.println(mystery1(3, 11));
    StdOut.println(mystery1(5, 5));  }
}
