package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.17 Prove that building a minimum-oriented priority queue of size k then doing N - k replace
 * the minimum (insert followed by remove the minimum) operations leaves the k largest of the N
 * items in the priority queue.
 *
 * <p>证明构造一个大小为 k 的面向最小优先队列然后进行 N-k 次替换最小值(插入之后移除最小值) 操作将会留下 N 项中 K<br>
 * 个最大的项在优先队列中.
 *
 * @author LeonChen
 * @since 9/22/20
 */
class E02_04_17 {

  /**
   * 因为是一个最小堆,所以每次插入的时候如果是一个比较大的值,是肯定不会出现在堆顶的.每次插入后移除都会将当前项中的最小<br>
   * 值移除所以最后留下的是项中 K个最大的项
   */
  public static void main(String[] args) {}
}
