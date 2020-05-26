package edu.princeton.cs.exercise.chapter2_1;

/**
 * 2.1.8 Suppose that we use insertion sort on a randomly ordered array where elements have only one
 * of three values. Is the running time linear, quadratic, or something in between?
 *
 * <p>假设我们对一个值的取值在3个数中的随机排序的数组使用插入排序.运行时间是线形的,平方的,还是某个中间值?
 *
 * @author LeonChen
 * @since 5/14/20
 */
class E02_01_08 {

  /**
   * 平方级别的.<br>
   * <code>
   *     假设这3个数是1,2,3.由于是随机的,所以他们的概率相同.
   *     假设已经排序了部分
   *     1111...222...333...2311231...
   *     a个     b个   c个
   *     其中我们可以得出,当排序的数是 1时,需要交换 b+c次;如果是 2时,需要交换 c 次;如果是3时,则无需交换.
   *     由概率相同可知,平均交换次数是 (b+c+c+0)/3 = (b+2c)/3 . 此时插入后,如果插入的是1,下一次 b+2c 不变;
   *     如果插入的是2,b+2c 的值 +1 ; 如果插入的是 3 ,b+2c的值 +2;由于概率相同可得,下一次的交换次数平均值是
   *     (b+2c+ (0+1+2)/3 )/3 也就是增加了1/3次. 当没有元素时i=0,交换次数也=0,可得 0+1/3+2/3+3/3+4/3....N/3
   *     根据等差数列和可得 N*(N-1)/6 所以是平方级别的.
   * </code>
   */
  public static void main(String[] args) {}
}
