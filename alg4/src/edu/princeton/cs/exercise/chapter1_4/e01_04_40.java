package edu.princeton.cs.exercise.chapter1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSum;

/**
 * 1.4.40 3-sum for random values. Formulate and validate a hypothesis describing the number of
 * triples of N random int values that sum to 0. If you are skilled in mathematical analysis,
 * develop an appropriate mathematical model for this problem, where the values are uniformly
 * distributed between –M and M, where M is not small.
 *
 * <p>随机数值的 3-sum.猜测并验证N个随机数中和为0的组合的数量.如果你擅长数学分析技巧,为这个问题开发一个合适<br>
 * 的数学模型,这些数值是均匀分布在 -M 到 M 中的,M不是一个小数字.
 *
 * @author LeonChen
 * @since 4/1/20
 */
class e01_04_40 {

  /**
   * 参考 https://alg4.ikesnowy.com/1-4-40/
   *
   * <p>在 [−M,M] 中取三个数,和为零的序列有几个？ 有这么几种情况<br>
   * 1. 三个数都是 0,(0,0,0) 的情况有一个. <br>
   * 2. 有一个0的时候, 剩下两个数只能是符号相反的数,总共 2M/2 种组合.换成排列数(例如 (0 ,1 ,-1) 和 (0 ,-1 ,1)<br>
   * 是两种不同的排列) 就是 2M 种. <br>
   * 3. 没有0的情况,假设第一个数取 1,剩下的数不能选择 M(因为另一个数不可能是 -(M+1)),剩下 2M 对数值组合等于-1. <br>
   * 总共 (2M)/2∗2=2M 种排列. 假设第一个数取 2,那么剩下的数就不可能是M 和 M−1,剩下 2M−1 个数配对.<br>
   * 总共 2M−1 种序列.以此类推,第一个数取 M 时,只能在 −M 到 0 之间配对,总共 M+1 种序列. −M 到 −1 之间的序列数<br>
   * 计算完全一样，于是由等差数列求和公式： (M+1+2M)M/2 所以 -M 到 M 是(M+1+2M)M/2*2 <br>
   * 总的数量是1+2M+ (M+1+2M)M/2*2 =3M^2+3M+1
   *
   * <p>N 个数可组成的三元组的总数为： C(N,3)=N(N−1)(N−2)/3!= (N3)/6​ (组合公式)<br>
   * [−M,M]​ 中随机 N​ 次,有 (2M+1)^N​ 种随机序列(每次都有 2M+1​ 种可能)<br>
   * 和为零的三元组有 3M^2+3M+1​ 种可能.不为零的 N−3​ 个位置有 (2M+1)^(N−3)​ 种可能.<br>
   * 总共有 ((N^3)/6)×(3M^2+3M+1)×(2M+1)^(N−3)​ 种可能性<br>
   * 平均值为： [((N^3)/6)*(3M^2+3M+1)*(2M+1)^(N−3)]/(2M+1)N​ =N^3/16M​
   */
  public static void main(String[] args) {

    int M = 10000;

    for (int n = 125; n < 10000; n += n) {
      int[] a = new int[n];
      for (int i = 0; i < n; ++i) {
        a[i] = StdRandom.uniform(-M, M);
      }
      StdOut.println(
          "N= " + n + " ,计算值=" + Math.pow(n, 3) / (16 * M) + " ,实际值= " + ThreeSum.count(a));
    }
  }
}
