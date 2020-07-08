package edu.princeton.cs.exercise.chapter2_2;

/**
 * 2.2.5 Give the sequence of subarray sizes in the merges performed by both the top-down and the
 * bottom-up mergesort algorithms, for N = 39.
 *
 * <p>在N=39的情况下,给出使用 top-down 和 bottom-up 的合并排序算法的子数组大小的序列.
 *
 * @author LeonChen
 * @since 6/19/20
 */
class E02_02_05 {

  /**
   * 注意这里是 N =39 不是index =39
   *
   * <p>top-down 2 3 2 5 2 3 2 5 10 2 3 2 5 2 3 2 5 10 20 2 3 2 5 2 3 2 5 10 2 3 2 5 2 2 4 9 19 39
   *
   * <p>bottom-up 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 4 4 4 4 4 4 4 4 4 3 8 8 8 8 7 16 16 32 39
   * <br>
   * 19个2 , 9个4 ,
   */
  public static void main(String[] args) {}
}
