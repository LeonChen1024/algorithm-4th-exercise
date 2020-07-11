package edu.princeton.cs.exercise.chapter2_2;

/**
 * 2.2.8 Suppose that Algorithm 2.4 is modified to skip the call on merge() whenever a[mid] <=
 * a[mid+1]. Prove that the number of compares used to mergesort a sorted array is linear.
 *
 * <p>假设算法 2.4 修改后当 a[mid] <= a[mid+1] 不调用 merge() 方法.证明对一个有序数组使用合并排序的对比次数是<br>
 * 线性的
 *
 * @author LeonChen
 * @since 6/24/20
 */
class E02_02_08 {

  /**
   * 由于数组有序,所以每次merge都会被跳过.每个子数组只需要对比一次.当N是2的幂次的时候,可以得到<br>
   * T(N) = 2 T(N/2) + 1,T(1) = 0  所以是线性的.
   */
  public static void main(String[] args) {}
}
