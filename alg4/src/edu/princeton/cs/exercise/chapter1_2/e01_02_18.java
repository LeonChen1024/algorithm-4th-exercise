package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.18 Variance for accumulator. Validate that the following code, which adds the methods var()
 * and stddev() to Accumulator, computes both the mean and variance of the numbers presented as
 * arguments to addDataValue(): <code>
 *   public class Accumulator {
 *     private double m;
 *     private double s;
 *     private int N;
 *
 *     public void addDataValue(double x) {
 *       N++;
 *       s = s + 1.0 * (N - 1) / N * (x - m) * (x - m);
 *       m = m + (x - m) / N;
 *     }
 *
 *     public double mean() {
 *       return m;
 *     }
 *
 *     public double var() {
 *       return s / (N - 1);
 *     }
 *
 *     public double stddev() {
 *       return Math.sqrt(this.var());
 *     }
 *   }
 *
 * </code>This implementation is less susceptible to roundoff error than the straightforward
 * implementation based on saving the sum of the squares of the numbers.
 *
 * <p>计算器的方差.验证以下代码,它添加了 var() 和 stddev() 方法到计算器,同时计算了平均值和方差. 这个实现比起直接舍去数字<br>
 * 平方和来说更不容易出现舍入错误.
 *
 * @author LeonChen
 * @since 12/13/19
 */
class e01_02_18 {

  public static void main(String[] args) {
    Accumulator accumulator = new Accumulator();
    for (int i = 0; i < 100; i++) {
      int addnum = StdRandom.uniform(10);
      accumulator.addDataValue(addnum);
      StdOut.println(
          "current add num = "
              + addnum
              + "   , current mean = "
              + accumulator.mean()
              + "  , current variance"
              + accumulator.var()
              + "   , stddev = "
              + accumulator.stddev());
    }
  }

  public static class Accumulator {
    private double m;
    private double s;
    private int N;

    public void addDataValue(double x) {
      N++;
      s = s + 1.0 * (N - 1) / N * (x - m) * (x - m);
      m = m + (x - m) / N;
    }

    public double mean() {
      return m;
    }

    /**
     * 样本方差
     *
     * @return
     */
    public double var() {
      return s / (N - 1);
    }

    /**
     * 标准差
     *
     * @return
     */
    public double stddev() {
      return Math.sqrt(this.var());
    }
  }
}
