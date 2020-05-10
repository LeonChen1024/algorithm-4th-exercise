package edu.princeton.cs.exercise.chapter1_5;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.5.21 Erdös-Renyi model. Use your client from Exercise 1.5.17 to test the hypothesis that the
 * number of pairs generated to get one component is ~ 1⁄2N ln N.
 *
 * <p>Erdös-Renyi 模型.使用练习 1.5.17 的客户端来测试假说随机生成连接对变成一个组件的生成次数是 ~ 1⁄2NlnN.
 *
 * @author LeonChen
 * @since 4/26/20
 */
class E01_05_21 {

  /** */
  public static void main(String[] args) {
    for (int N = 100; N < 50000; N = N * 2) {
      int count = E01_05_17.count(N);
      double expect = ((1 / 2.0) * N * Math.log(N));
      StdOut.println(
          "i = "
              + N
              + " , count is "
              + count
              + " , 1⁄2NlnN is "
              + expect
              + " , ratio is "
              + count / expect);
    }
  }
}
