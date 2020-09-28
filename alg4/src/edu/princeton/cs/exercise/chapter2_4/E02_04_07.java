package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.7 The largest item in a heap must appear in position 1, and the second largest must be in
 * position 2 or position 3. Give the list of positions in a heap of size 31 where the kth largest
 * (i) can appear, and (ii) cannot appear, for k=2, 3, 4 (assuming the values to be distinct).
 *
 * <p>在一个堆中最大的值肯定出现在位置1上,而第二大的值肯定出现在位置2或者3.给出在一个大小为31的堆中第 k 大的数<br>
 * 可以出现的位置列表,和不能出现的位置列表,比如 k=2,3,4 (假设值都是不同的)
 *
 * @author LeonChen
 * @since 9/16/20
 */
class E02_04_07 {

  /**
   * <code>
   * 由于每次插入上浮的时候都会保证自己比父节点小,比子节点大,也就是说每一层的最大数都肯定是比下一层的最大数要大.
   * 所以可以得出第k个最大值一定会在第 k 层及以上.
   *
   * 也就是说
   *
   * k = 2 时, 第 k 大的值会在 2,3
   * k = 3 时, 第 k 大的值会在 2,3,4,5,6,7
   * k = 4 时, 第 k 大的值会在 2,3,4,5,6,7,8,9,10,11,12,13,14,15
   *
   * </code>
   */
  public static void main(String[] args) {}
}
