package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.21 Elementary data structures. Explain how to use a priority queue to implement the stack,
 * queue, and randomized queue data types from Chapter 1.
 *
 * <p>基础数据结构.解释如何使用一个优先队列来实现第一章中的栈,队列,还有随机队列结构
 *
 * @author LeonChen
 * @since 9/25/20
 */
class E02_04_21 {

  /**
   * 我们可以通过将插入的项进一步封装来实现, 增加一个属性索引来代表它在优先队列中的键. <br>
   * 栈实现<br>
   * 使用最大优先队列,每次插入的时候都将索引加1,使得后加入的项始终在顶部,每次移除的时候页会从后加入的项开始移除<br>
   * 注意在插入的时候可能需要扩容,同时要保持对索引的跟踪.<br>
   * 队列实现<br>
   * 使用最小优先队列,每次插入的时候都将索引加1,使得前面加入的项始终在顶部,每次移除的时候页会从早加入的项开始移除<br>
   * 注意在插入的时候可能需要扩容,同时要保持对索引的跟踪.<br>
   * 随机队列实现<br>
   * 和队列实现类似,只需要在插入的时候使用随机索引即可.
   */
  public static void main(String[] args) {}
}
