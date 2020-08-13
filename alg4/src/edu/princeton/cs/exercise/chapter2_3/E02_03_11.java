package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.11 Suppose that we scan over items with keys equal to the partitioning item’s key instead of
 * stopping the scans when we encounter them. Show that the running time of this version of
 * quicksort is quadratic for all arrays with just a constant number of distinct keys.
 *
 * <p>假设我们扫描的时候忽略那些键和分割项相同的项而不是停止扫描等待交换.证明这种版本下的快速排序的运行时间<br>
 * 在只有常量级的不同key的数组中是平方级的.
 *
 * @author LeonChen
 * @since 8/5/20
 */
class E02_03_11 {

  /**
   * 当有大量重复的key的时候,如果是直接扫描通过的话,极端情况下会导致 j 直接走到 起始点 , 每次只排好一位.<br>
   * 比如 { 1,1,1,1,1,1,1,1} . 重复的键越多,时间越接近平方级
   *
   * @param args
   */
  public static void main(String[] args) {}
}
