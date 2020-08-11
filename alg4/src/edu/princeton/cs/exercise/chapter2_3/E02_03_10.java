package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.10 Chebyshev’s inequality says that the probability that a random variable is more than k
 * standard deviations away from the mean is less than 1/k^2. For N = 1 million, use Chebyshev’s
 * inequality to bound the probability that the number of compares used by quicksort is more than
 * 100 billion (0.1N^2).
 *
 * <p>切比雪夫不等式说随机变量与均值之差大于k个标准偏差的概率小于1/k^2. 给定 N = 1 百万,使用切比雪夫不等式<br>
 * 来限制quicksort使用的比较次数超过1000亿（0.1N^2）的概率。
 *
 * @author LeonChen
 * @since 8/4/20
 */
class E02_03_10 {

  /**
   * 根据题意和切比雪夫不等式可得 P(|X-u| >= kσ) <= 1/k^2. u 代表期望,σ代表标准偏差.对于快速排序来说,<br>
   * μ=2NlnN,σ=0.65N (正文中的命题K,L 已经证明过了).<br>
   * 要求比较次数大于 0.1N^2 ，可以求得 k 的值。 0.65kN = 0.1N^2 . k = 2N/13 <br>
   * 将 N=1,000,000 代入, P(|X−27,631,021|≥100,000,000,000)≤0.00000000004225
   *
   * @param args
   */
  public static void main(String[] args) {}
}
