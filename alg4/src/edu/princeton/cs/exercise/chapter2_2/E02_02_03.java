package edu.princeton.cs.exercise.chapter2_2;

/**
 * 2.2.3 Answer Exercise 2.2.2 for bottom-up mergesort.
 *
 * <p>练习 2.2.2 如果使用 从底向上 合并排序会是什么答案?
 *
 * @author LeonChen
 * @since 6/16/20
 */
class E02_02_03 {

  /**
   * <code>
   *                                                  a[]
   *                              0  1  2  3  4  5  6  7  8  9  10  11
   *                              E  A  S  Y  Q  U  E  S  T  I   O   N
   *        merge(a, 0, 0, 1)     A  E  S  Y  Q  U  E  S  T  I   O   N
   *        merge(a, 2, 2, 3)     A  E  S  Y  Q  U  E  S  T  I   O   N
   *        merge(a, 4, 4, 5)     A  E  S  Y  Q  U  E  S  T  I   O   N
   *        merge(a, 6, 6, 7)     A  E  S  Y  Q  U  E  S  T  I   O   N
   *        merge(a, 8, 8, 9)     A  E  S  Y  Q  U  E  S  I  T   O   N
   *        merge(a, 10, 10, 11)  A  E  S  Y  Q  U  E  S  I  T   N   O
   *      merge(a, 0, 1, 3)       A  E  S  Y  Q  U  E  S  I  T   N   O
   *      merge(a, 4, 5, 7)       A  E  S  Y  E  Q  S  U  I  T   N   O
   *      merge(a, 8, 9, 11)      A  E  S  Y  E  Q  S  U  I  N   O   T
   *    merge(a, 0, 3, 7)         A  E  E  Q  S  S  U  Y  I  N   O   T
   *  merge(a, 0, 7, 11)          A  E  E  I  N  O  Q  S  S  T   U   Y
   *                              A  E  E  I  N  O  Q  S  S  T   U   Y
   * </code>
   */
  public static void main(String[] args) {}
}
