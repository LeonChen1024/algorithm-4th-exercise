package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.13 What is the recursive depth of quicksort, in the best, worst, and average cases? This is
 * the size of the stack that the system needs to keep track of the recursive calls. See Exercise
 * 2.3.20 for a way to guarantee that the recursive depth is logarithmic in the worst case.
 *
 * <p>在最好,最差,平均情况下快排的递归深度是多少?这是系统为了保证递归调用所需的栈大小. 查阅练习 2.3.20 找到一个<br>
 * 方式来保证递归深度在最差的情况下是对数级的
 *
 * @author LeonChen
 * @since 8/6/20
 */
class E02_03_13 {

  /**
   * 每次分割都会至少排好一位的位置,并且会有左子数组和右子数组,类似一个二叉树的结构.<br>
   * 因此最好的情况是每次都二等分了,最后的高度就是 logn .<br>
   * 最差的情况就是每次都只有一边子数组有元素,变成一个线性结构, 高度就是 n-1<br>
   * 平均情况高度也是 logn ,因为打乱元素情况下出现线性的可能极小.
   */
  public static void main(String[] args) {}
}
