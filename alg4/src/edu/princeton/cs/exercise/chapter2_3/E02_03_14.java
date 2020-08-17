package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.14 Prove that when running quicksort on an array with N distinct items, the probability of
 * comparing the i th and j th largest items is 2/(j - i). Then use this result to prove Proposition
 * K.
 *
 * <p>证明当在一个拥有N个不同项的数组中运行快速排序,对比 第i个和第j个最大的项的概率是 2/(j-i).然后使用这个<br>
 * 结果来证明假说 K.
 *
 * @author LeonChen
 * @since 8/6/20
 */
class E02_03_14 {

  /**
   * 快排中,如果两个元素进行比较,那么其中一个元素会是分割点.因为这轮比较后,分割点已经到了它应该到了它最终的位置<br>
   * 不参与后面的对比,所以最多只会对比一次.<br>
   * 当 i 和 j 对应的是最小值和最大值的时候,除非i 和 j 一开始就在分割点上,否则就会被分到两个子数组中,不会互相对比.<br>
   * 概率为 2/n = 2/(j-i+1). 其它普通情况下, 元素如果选择到 i~j 范围外则不影响结果,因为 i,j会被分到同一个<br>
   * 子数组,如果选到 i~j 之间会导致 i j 被分到两个子数组无法对比. 所以比较发生概率为 2/(j-i+1)<br>
   * 接下来看假说K 中每两个元素比较的概率 <code>
   *     p = SUM(from i=1 to N) * SUM(from j = i + 1 to N) * 2/(j - i + 1)
   *     p = SUM(from i=1 to N) * 2(1/2 + 1/3 + 1/4 + ... + 1/(n - i + 1))
   *     p < 2n(1/2+1/3+...+1/n)
   *     根据调和级数的性质 ln(n) < 1+1/2+⋯+1/n < 1+ln(n) 可得
   *     p < 2nln(n)
   *     得证
   * </code>
   */
  public static void main(String[] args) {}
}
