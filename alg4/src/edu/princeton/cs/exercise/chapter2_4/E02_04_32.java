package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.32 Lower bound. Prove that it is impossible to develop a compare-based implementation of the
 * MinPQ API such that both insert and delete the minimum guarantee to use ~N log log N compares.
 *
 * <p>更低的下边界.证明开发一个 MinPQ API 基于对比的实现使得插入和删除最小值的成本是 ~NloglogN 次对比是不可能的.
 *
 * @author LeonChen
 * @since 11/9/20
 */
class E02_04_32 {

  /**
   * 插入可以根据上一题中的二分法上浮得出 ~NloglogN 的复杂度对比实现,而下沉则无法实现,因为下沉的路径无法判断,<br>
   * 最少只能是 NlogN 复杂度
   *
   * @param args
   */
  public static void main(String[] args) {}
}
