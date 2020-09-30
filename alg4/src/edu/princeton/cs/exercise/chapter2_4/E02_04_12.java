package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.12 Suppose that your application will have a huge number of find the maximum operations, but
 * a relatively small number of insert and remove the maximum operations. Which priority-queue
 * implementation do you think would be most effective: heap, unordered array, or ordered array?
 *
 * <p>假设你的应用将会有大量的查找最大值操作,但是很少的插入和移除最大值操作.哪种优先队列实现你认为会是最有效率的:<br>
 * 堆,无序数组,还是有序数组?
 *
 * @author LeonChen
 * @since 9/18/20
 */
class E02_04_12 {

  /**
   * <code>
   * 根据题意,我们应该优先选择查找最大值成本最低的结构,也就是堆和有序数组. 其中堆在插入和移除最大值操作中成本更低.
   * 所以选择堆
   *
   * </code>
   */
  public static void main(String[] args) {}
}
