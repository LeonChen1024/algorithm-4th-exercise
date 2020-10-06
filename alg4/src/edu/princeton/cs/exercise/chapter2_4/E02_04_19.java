package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.MaxPQ;

/**
 * 2.4.19 Implement the constructor for MaxPQ that takes an array of items as argument, using the
 * bottom-up heap construction method described on page 323 in the text.
 *
 * <p>实现一个 MaxPQ 的构造器接收了一个数组的项作为参数,使用正文323页描述的从下往上的堆构造方法.
 *
 * @author LeonChen
 * @since 9/23/20
 */
class E02_04_19 {

  /** */
  public static void main(String[] args) {
    MaxPQ maxPQ = new MaxPQ(new Integer[] {5, 2, 5, 7, 22, 1});
  }
}
