package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.2 Show, in the style of the quicksort trace given in this section, how quicksort sorts the
 * array E A S Y Q U E S T I O N (for the purposes of this exercise,ignore the initial shuffle).
 *
 * <p>展示,使用本章节中的快排追踪风格展示出数组 E A S Y Q U E S T I O N 是如何进行快排的 <br>
 * (假设在这个练习中,忽略初始的打乱顺序操作)
 *
 * @author LeonChen
 * @since 7/29/20
 */
class E02_03_02 {

  /**
   * <code>
   *
   *                                a[]
   * lo   i    j    hi   0  1  2  3  4  5  6  7  8  9  10  11
   *                     E  A  S  Y  Q  U  E  S  T  I   O   N
   *  0   2    6    11   E  A  E  Y  Q  U  S  S  T  I   O   N
   *  0   0    1    2    A  E  E  Y  Q  U  S  S  T  I   O   N
   *  0             0    A  E  E  Y  Q  U  S  S  T  I   O   N
   *  3   11   11   11   A  E  E  N  Q  U  S  S  T  I   O   Y
   * 11             11   A  E  E  N  Q  U  S  S  T  I   O   Y
   *  3   4    9    10   A  E  E  I  N  U  S  S  T  Q   O   Y
   *  3             3    A  E  E  I  N  U  S  S  T  Q   O   Y
   *  5             10   A  E  E  I  N  O  S  S  T  Q   U   Y
   *  5             9    A  E  E  I  N  O  S  S  T  Q   U   Y
   *  6             9    A  E  E  I  N  O  Q  S  T  S   U   Y
   *  7             9    A  E  E  I  N  O  Q  S  T  S   U   Y
   *  8         9   9    A  E  E  I  N  O  Q  S  S  T   U   Y
   *                     A  E  E  I  N  O  Q  S  S  T   U   Y
   *
   * </code>
   */
  public static void main(String[] args) {}
}
