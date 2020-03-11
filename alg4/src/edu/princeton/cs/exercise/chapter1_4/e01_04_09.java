package edu.princeton.cs.exercise.chapter1_4;

/**
 * 1.4.9 Give a formula to predict the running time of a program for a problem of size N when
 * doubling experiments have shown that the doubling factor is 2^b and the running time for problems
 * of size N0 is T.
 *
 * <p>给定一个公式来预测程序对某个问题的N 翻倍的时候的表现,已知时间的变化因子是2^b 并且问题对大小N_0的运行时间是T.
 *
 * @author LeonChen
 * @since 3/6/20
 */
class e01_04_09 {

  /**
   * f(N_0) = f(N_0/2)*2^b = T <br>
   * f(2N_0) = f(N_0) *2^b = T *2^b<br>
   * f(4N_0) = f(2N_0) *2^b = T *2^b *2^b<br>
   * 根据相同的数相乘可以转换为指数可以得到<br>
   * f(1N_0) = f(N_0/2)*2^b = T *(2^b)^lg1<br>
   * f(2N_0) = f(N_0) *2^b = T *(2^b)^lg2<br>
   * f(4N_0) = f(2N_0) *2^b = T *(2^b)^lg4<br>
   * 所以可以得到<br>
   * F(N) = T * (2^b)^lg(N/N_0)
   *
   * @param args
   */
  public static void main(String[] args) {}
}
