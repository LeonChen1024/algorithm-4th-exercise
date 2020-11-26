package edu.princeton.cs.exercise.chapter2_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.4.35 Sampling from a discrete probability distribution. Write a class Sample with a constructor
 * that takes an array p[] of double values as argument and supports the following two operations:
 * random()—return an index i with probability p[i]/T (where T is the sum of the numbers in p[])—and
 * change(i, v)—change the value of p[i] to v. Hint: Use a complete binary tree where each node has
 * implied weight p[i]. Store in each node the cumulative weight of all the nodes in its subtree. To
 * generate a random index, pick a random number between 0 and T and use the cumulative weights to
 * determine which branch of the subtree to explore. When updating p[i], change all of the weights
 * of the nodes on the path from the root to i. Avoid explicit pointers, as we do for heaps.
 *
 * <p>从离散概率分布中采样.编写一个示例类带有一个接收一个 double 数值的数组 p[] 的构造器并且支持以下操作:<br>
 * random()-返回一个索引i的概率是 p[i]/T (T是 p[]中的数值之和)<br>
 * change(i,v)-修改 p[i]的值为 v.<br>
 * 提示:使用一个完全二叉树使得每个节点拥有一个隐式p[i]的权重.它保存了每个节点以及它的子树的权重之和.为了生成一个<br>
 * 随机索引,选择一个0到T之间的随机数并且根据总权重来判断应该寻找哪个子树.当更新p[i]的时候,修改所有从root 到i的节点<br>
 * 的权重.避免显式的指针,就像我们在堆中做的那样
 *
 * @author LeonChen
 * @since 11/13/20
 */
class E02_04_35 {

  /** @param args */
  public static void main(String[] args) {
    Sample sample = new Sample(new double[] {0.1, 0.1, 0.15, 0.1, 0.3, 0.15});
    StdOut.println("sample = " + sample);
    StdOut.println("random = " + sample.random());
    sample.change(0, 0.3);
    StdOut.println("sample = " + sample);
    StdOut.println("random = " + sample.random());
  }

  private static class Sample {
    private double[] p;
    private double[] sum;

    private double T = 0;

    public Sample(double[] a) {
      p = new double[a.length + 1];
      for (int i = 1; i < a.length + 1; i++) {
        p[i] = a[i - 1];
        T += a[i - 1];
      }

      // 记录子树权重和
      sum = new double[a.length + 1];
      for (int i = a.length; i / 2 > 0; i--) {
        sum[i / 2] += p[i] + sum[i];
      }
    }

    /**
     * 相当于节点按照概率从上到下从左子树往右子树排序,当随机值权重越大越往下沉,每次下沉需要减掉跳过节点的权重概率.
     *
     * @return
     */
    public int random() {
      double r = StdRandom.uniform() * T;
      StdOut.println("r = " + r);
      int i = 1;
      while (i * 2 <= p.length) {
        if (r <= p[i]) {
          // 找到了随机命中节点
          break;
        }
        // 减去当前权重继续下沉
        r -= p[i];
        i *= 2;
        if (r <= sum[i] + p[i]) {
          // 往左子树下沉
          continue;
        }
        // 在右子树范围内,减去左子树权重
        r = r - sum[i] - p[i];
        i++;
      }
      return i - 1;
    }

    /**
     * 修改值后需要更新当前节点上面的节点的子树权重以及总和T
     *
     * @param i
     * @param v
     */
    public void change(int i, double v) {
      i++;
      T = T - p[i] + v;
      p[i] = v;
      i /= 2;
      while (i > 0) {
        sum[i] = p[i * 2] + sum[i * 2];
        // 如果有右子树,加上右子树的值
        if (i * 2 + 1 < p.length) {
          sum[i] += p[i * 2 + 1] + sum[i * 2 + 1];
        }
        i /= 2;
      }
    }

    @Override
    public String toString() {
      return "Sample{"
          + "p="
          + Arrays.toString(p)
          + ", sum="
          + Arrays.toString(sum)
          + ", T="
          + T
          + '}';
    }
  }
}
