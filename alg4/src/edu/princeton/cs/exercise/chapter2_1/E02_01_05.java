package edu.princeton.cs.exercise.chapter2_1;

/**
 * 2.1.5 For each of the two conditions in the inner for loop in insertion sort (Algorithm 2.2),
 * describe an array of N items where that condition is always false when the loop terminates.
 *
 * <p>对于插入排序中的内部循环的两个条件 (Algorithm 2.2) ,描述一个有 N 个项的数组它的条件总是false知道loop结束.
 *
 * @author LeonChen
 * @since 5/13/20
 */
class E02_01_05 {

  /**
   * j > 0 && less(a[j], a[j - 1]) j只与目前的排序进度相关,可以放着. less(a[j], a[j - 1]) 只要<br>
   * a[j]>a[j-1]即可所以只要是升序排好的数组即可.如 A B C D
   */
  public static void main(String[] args) {}
}
