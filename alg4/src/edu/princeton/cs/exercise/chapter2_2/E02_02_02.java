package edu.princeton.cs.exercise.chapter2_2;

/**
 * 2.2.2 Give traces, in the style of the trace given with Algorithm 2.4, showing how the keys E A S
 * Y Q U E S T I O N are sorted with top-down mergesort.
 *
 * <p>给出图表,使用算法 2.4 的样式,展示出 E A S Y Q U E S T I O N 在使用 top-down 合并排序的时候是怎么排序的.
 *
 * @author LeonChen
 * @since 6/16/20
 */
class E02_02_02 {

  /**
   * <code>
   *                                          a[]
   *                           0  1  2  3  4  5  6  7  8  9  10  11
   *                           E  A  S  Y  Q  U  E  S  T  I   O   N
   *        merge(a, 0, 0, 1)  A  E  S  Y  Q  U  E  S  T  I   O   N
   *      merge(a, 0, 1, 2)    A  E  S  Y  Q  U  E  S  T  I   O   N
   *        merge(a, 3, 3, 4)  A  E  S  Q  Y  U  E  S  T  I   O   N
   *      merge(a, 3, 4, 5)    A  E  S  Q  U  Y  E  S  T  I   O   N
   *    merge(a, 0, 2, 5)      A  E  Q  S  U  Y  E  S  T  I   O   N
   *        merge(a, 6, 6, 7)  A  E  Q  S  U  Y  E  S  T  I   O   N
   *      merge(a, 6, 7, 8)    A  E  Q  S  U  Y  E  S  T  I   O   N
   *        merge(a, 9, 9, 10) A  E  Q  S  U  Y  E  S  T  I   O   N
   *      merge(a, 9, 10, 11)  A  E  Q  S  U  Y  E  S  T  I   N   O
   *    merge(a, 6, 8, 11)     A  E  Q  S  U  Y  E  I  N  O   S   T
   *  merge(a, 0, 5, 11)       A  E  E  I  N  O  Q  S  S  T   U   Y
   *                           A  E  E  I  N  O  Q  S  S  T   U   Y
   * </code>
   */
  public static void main(String[] args) {}
}
