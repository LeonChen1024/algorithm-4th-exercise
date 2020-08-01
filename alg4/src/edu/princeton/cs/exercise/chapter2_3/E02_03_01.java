package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.1 Show, in the style of the trace given with partition(), how that method patitions the array
 * E A S Y Q U E S T I O N.
 *
 * <p>展示,使用 partition() 给定的追踪风格绘制出对 E A S Y Q U E S T I O N 进行分割的效果
 *
 * @author LeonChen
 * @since 7/29/20
 */
class E02_03_01 {

  /**
   * <code>
   *                                  a[]
   *    i   j   0  1  2  3  4  5  6  7  8  9  10  11
   *            E  A  S  Y  Q  U  E  S  T  I   O   N
   *    0   1   E  A  S  Y  Q  U  E  S  T  I   O   N
   *    0   2   E  A  S  Y  Q  U  E  S  T  I   O   N
   *    0   11  E  A  S  Y  Q  U  E  S  T  I   O   N
   *    0   10  E  A  S  Y  Q  U  E  S  T  I   O   N
   *    0   9   E  A  S  Y  Q  U  E  S  T  I   O   N
   *    0   8   E  A  S  Y  Q  U  E  S  T  I   O   N
   *    0   7   E  A  S  Y  Q  U  E  S  T  I   O   N
   *    0   6   E  A  S  Y  Q  U  E  S  T  I   O   N
   * ex 2   6   E  A  E  Y  Q  U  S  S  T  I   O   N
   *    0   3   E  A  E  Y  Q  U  S  S  T  I   O   N
   *    0   5   E  A  E  Y  Q  U  S  S  T  I   O   N
   *    0   4   E  A  E  Y  Q  U  S  S  T  I   O   N
   * ex 0   2   E  A  E  Y  Q  U  S  S  T  I   O   N
   *            E  A  E  Y  Q  U  S  S  T  I   O   N
   * </code>
   */
  public static void main(String[] args) {}
}
