package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.4.25 Computational number theory. Write a program CubeSum.java that prints out all integers of
 * the form a^3 + b^3 where a and b are integers between 0 and N in sorted order, without using
 * excessive space. That is, instead of computing an array of the N^2 sums and sorting them, build a
 * minimum-oriented priority queue, initially containing (0^3, 0, 0),(1^3, 1, 0),(2^3, 2, 0), . . .
 * , (N^3, N, 0). Then, while the priority queue is nonempty, remove the smallest item(i^3 + j^3, i,
 * j), print it, and then, if j < N, insert the item (i^3 + (j+1)^3, i, j+1). Use this program to
 * find all distinct mintegers a, b, c, and d between 0 and 10^6 such that a^3+b^3=c^3+d^3.
 *
 * <p>计算数字理论.编写一个程序 CubeSum.java 打印出所有的 a^3 + b^3 的值,其中 a 和 b 是 0 到 N 之间的有序元素,<br>
 * 不使用额外的空间.也就是说,不是计算一个 N^2 个和的数组并且排序,而是构造一个最小优先队列,初始化包含 (0^3, 0, 0),<br>
 * (1^3, 1, 0),(2^3, 2, 0), . . ., (N^3, N, 0).然后,当优先队列不为空的时候,移除最小项 (i^3 + j^3, i,j),<br>
 * 并打印,然后,如果 j<N, 插入一个项 (i^3 + (j+1)^3, i, j+1).使用这个程序来找到所有在0~10^6中的不同的数字<br>
 * a, b, c, d使得 a^3+b^3=c^3+d^3.
 *
 * @author LeonChen
 * @since 10/21/20
 */
class E02_04_25 {

  /** 10^6 用时太长 */
  public static class CubeSum implements Comparable<CubeSum> {
    private static int num;
    private final long sum;
    private final Long i;
    private final Long j;

    public CubeSum(Long i, Long j) {
      this.sum = i * i * i + j * j * j;
      this.i = i;
      this.j = j;
    }

    public int compareTo(CubeSum that) {
      if (this.sum < that.sum) return -1;
      if (this.sum > that.sum) return +1;
      return 0;
    }

    public String toString() {
      return sum + " = " + i + "^3" + " + " + j + "^3";
    }

    public static void main(String[] args) {

      int n = (int) Math.pow(10, 4);

      // initialize priority queue
      MinPQ<CubeSum> pq = new MinPQ<CubeSum>();
      for (long i = 1; i <= n; i++) {
        pq.insert(new CubeSum(i, i));
      }

      CubeSum pre = new CubeSum(0L, 0L);
      // find smallest sum, print it out, and update
      while (!pq.isEmpty()) {
        CubeSum s = pq.delMin();
        if (s.compareTo(pre) == 0) {
          StdOut.println("two sum is " + pre + " and " + s);
          num++;
        }
        pre = s;
        if (s.j < n) pq.insert(new CubeSum(s.i, s.j + 1));
      }
      StdOut.println("total is " + num + " times");
    }
  }
}
