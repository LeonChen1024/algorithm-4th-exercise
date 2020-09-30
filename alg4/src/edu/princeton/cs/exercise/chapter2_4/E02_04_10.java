package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.10 Suppose that we wish to avoid wasting one position in a heap-ordered array pq[], putting
 * the largest value in pq[0], its children in pq[1] and pq[2], and so forth, proceeding in level
 * order. Where are the parents and children of pq[k]?
 *
 * <p>假设我们想要避免浪费堆排序数组pq[]中的一个位置,将最大的值放到 pq[0]中,他的孩子是 pq[1] 和 pq[2],以此类推,<br>
 * 按层级顺序执行. pq[k]的父与子是在哪里?
 *
 * @author LeonChen
 * @since 9/17/20
 */
class E02_04_10 {

  /**
   * <code>
   * 和原本计算规则只是差了一位而以.原规则 子元素为 2k,2k+1 , 父元素为 k/2
   * 现在就是 子元素为 2k+1,2k+2 , 父元素为 (k-1)/2
   *
   *
   * </code>
   */
  public static void main(String[] args) {}
}
