package edu.princeton.cs.exercise.chapter1_5;

/**
 * 1.5.9 Draw the tree corresponding to the id[] array depicted at right. Can this be the result of
 * running weighted quick-union? Explain why this is impossible or give a sequence of operations
 * that results in this array. <code>
 *     i    0 1 2 3 4 5 6 7 8 9
 *     -------------------------
 *   id[i]  1 1 3 1 5 6 1 3 4 5
 *
 * </code> 画出与展示的 id[] 数组相关的树图.这个有可能是 weighted quick-union 的运行结果吗?解释为什么这个不可能是 <br>
 * weighted quick-union 的运行结果或者是给出一个操作序列使得 weighted quick-union 可以出现给定数组.
 *
 * @author LeonChen
 * @since 4/19/20
 */
class e01_05_09 {

  /**
   * <code>
   *   _______1______
   *   |      |     |
   *   0  ____3     6
   *      |   |     |
   *      7   2     5___
   *                |   |
   *                4   9
   *                |
   *                8
   *
   * </code> 他不可能是 weighted quick-union 的结果,因为的他最深的树深度 1-6-5-4-8 是 4 ,超过了 lgN = lg10 ~ 3 < 4<br>
   * 所以不符合要求
   *
   * @param args
   */
  public static void main(String[] args) {}
}
