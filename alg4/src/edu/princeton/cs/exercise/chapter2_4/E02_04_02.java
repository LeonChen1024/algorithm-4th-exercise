package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.2 Criticize the following idea: To implement find the maximum in constant time, why not use a
 * stack or a queue, but keep track of the maximum value inserted so far, then return that value for
 * find the maximum?
 *
 * <p>评判下面的想法: 为了实现一个常数时间的找到最大值的方法,为什么不使用一个 栈 或者一个 队列,但是保持跟踪插入的数值<br>
 * 的最大值,然后当需要找到最大值的时候返回该值?
 *
 * @author LeonChen
 * @since 9/14/20
 */
class E02_04_02 {

  /** 这个方法是不可行的,它只保存了当前为止的最大值,当把这个最大值移除之后,需要找到现在的最大值,这个算法就没有用了 */
  public static void main(String[] args) {}
}
