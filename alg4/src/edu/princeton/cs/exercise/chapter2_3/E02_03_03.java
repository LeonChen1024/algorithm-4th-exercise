package edu.princeton.cs.exercise.chapter2_3;

/**
 * 2.3.3 What is the maximum number of times during the execution of Quick.sort() that the largest
 * item can be exchanged, for an array of length N ?
 *
 * <p>对于一个长度为 N 的数组来说 Quick.sort() 中一个项会被交换的最大次数是多少?
 *
 * @author LeonChen
 * @since 7/31/20
 */
class E02_03_03 {

  /**
   * N/2次<br>
   * 首先这个值要避免出现在分割点上,因为分割点只要1次就会到了他应该到的地方,之后也不会进行交换了.其次要避免他另一边的<br>
   * 元素过多,这样会导致他所在的那边分割次数变少.所以最好的情况下每次只分一个元素过去,而这个元素每次都在分割点的<br>
   * 第二个位置.也就是说,每次分割都会交换一次这个元素,每次分割会排序好两个元素,也就是共分割 N/2 次 ,交换 N/2次<br>
   * 比如 这个元素要是最大的 am 那么这个排序的模板就是<br>
   * a2 am a3 a1 <br>
   * 其中 a1<a2<a3<am , 以此类推
   */
  public static void main(String[] args) {}
}
