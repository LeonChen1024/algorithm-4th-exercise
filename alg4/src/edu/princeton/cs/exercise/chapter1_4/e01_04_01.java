package edu.princeton.cs.exercise.chapter1_4;

/**
 * 1.4.1 Show that the number of different triples that can be chosen from N items is precisely <br>
 * N(N-1)(N-2)/6. Hint : Use mathematical induction.
 *
 * <p>求证从 N个项里面可以选择出N(N-1)(N-2)/6不同的三个数组合.提示: 使用数学归纳法
 *
 * @author LeonChen
 * @since 3/1/20
 */
class e01_04_01 {

  /**
   * 首先,我们从 N 个项里面得到 3 个数的组合数是 N(N-1)(N-2).因为第一位会有 N 个可能的数,第二位因为第一位选取了一位所以只剩<br>
   * N-1 种可能,第三位就只剩 N-2 种可能.由于这三个数所处的位置不同,相同的 3 个数可以组成 3! =6 种可能.所以通过去重得到最后<br>
   * 组合的数量为 N(N-1)(N-2)/6.接下来思考一下边界条件,比如 <br>
   * P(N=0) = 0(0-1)(0-2)/6 = 0 <br>
   * P(N=1) = 1(1-1)(1-2)/6 = 0 <br>
   * P(N=2) = 2(2-1)(2-2)/6 = 0 <br>
   * P(N=3) = 3(3-1)(3-2)/6 = 1 <br>
   * 都可以符合要求.得证
   */
  public static void main(String[] args) {}
}
