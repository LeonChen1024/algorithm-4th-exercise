package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.4 Suppose that the initial random shuffle is omitted. Give six arrays of ten elements for
 * which Quick.sort() uses the worst-case number of compares.
 *
 * <p>假设忽略初始的打乱顺序的操作.给出6个包含10个元素的数组在使用 Quick.sort() 的时候对比次数是最差的情况
 *
 * @author LeonChen
 * @since 7/31/20
 */
class E02_03_04 {

  /**
   * 要让对比次数最多,那么只要每次分割的时候都只有一个元素排序好就可以达到这个效果.也就是按照排序的顺序来排列.<br>
   * 假设要求从小到大排序<br>
   * <code>
   *     a1 [1,2,3,4,5,6,7,8,9,10]
   *     a2 [2,3,4,5,6,7,8,9,10,11]
   *     a3 [3,4,5,6,7,8,9,10,11,12]
   *     a4 [4,5,6,7,8,9,10,11,12,13]
   *     a5 [5,6,7,8,9,10,11,12,13,14]
   *     a6 [6,7,8,9,10,11,12,13,14,15]
   *     </code>
   */
  public static void main(String[] args) {}
}
