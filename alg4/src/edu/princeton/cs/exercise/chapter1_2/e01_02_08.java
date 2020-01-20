package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.8 Suppose that a[] and b[] are each integer arrays consisting of millions of integers. What
 * does the follow code do? Is it reasonably efficient? <code>
 * int[]t=a;a=b;b=t;
 * </code> Answer. It swaps them. It could hardly be more efficient because it does so by copying
 * references, so that it is not necessary to copy millions of elements.
 *
 * <p>假设 a[] 和 b[] 是包含了百万个整数的数组.下面的代码的功能是什么?他合理有效吗?
 *
 * <p>答案:他交换了他们.很难更加的高效了,因为它通过复制引用,所以它不需要复制上百万个元素.
 *
 * @author LeonChen
 * @since 12/5/19
 */
class e01_02_08 {

  public static void main(String[] args) {
    int[] a = {2, 3, 4, 5};
    int[] b = {5, 2, 4, 5};

    int[] t = a;
    a = b;
    b = t;

    StdOut.print("a = ");
    for (int i = 0; i < a.length; i++) {
      StdOut.print(a[i] + " ,");
    }

    StdOut.println("");
    StdOut.print("b = ");
    for (int i = 0; i < b.length; i++) {
      StdOut.print(b[i] + " ,");
    }
  }
}
