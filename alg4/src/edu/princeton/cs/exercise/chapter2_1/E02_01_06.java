package edu.princeton.cs.exercise.chapter2_1;

/**
 * 2.1.6 Which method runs faster for an array with all keys identical, selection sort or insertion
 * sort?
 *
 * <p>选择排序和插入排序哪个方法对一个所有key都相同的数组进行排序速度更快?
 *
 * @author LeonChen
 * @since 5/14/20
 */
class E02_01_06 {

  /**
   * 插入排序更快.选择排序必须对所有未排序的数作对比,也就是 n*(n-1)*(n-2)...2*1 . <br>
   * 而插入排序则可以遇到相同不需要交换,只需要线性时间 n 即可
   */
  public static void main(String[] args) {}
}
