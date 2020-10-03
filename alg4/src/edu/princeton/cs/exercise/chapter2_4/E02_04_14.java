package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.14 What is the minimum number of items that must be exchanged during a remove the maximum
 * operation in a heap of size N with no duplicate keys? Give a heap of size 15 for which the
 * minimum is achieved. Answer the same questions for two and three successive remove the maximum
 * operations.
 *
 * <p>当在一个大小为N无重复键的堆中移除一个最大值最少要交换几个项?给定一个大小为15的堆.如果连续移除最大值2,3次的时候<br>
 * 又会是多少呢?
 *
 * @author LeonChen
 * @since 9/18/20
 */
class E02_04_14 {

  /**
   * 下沉交换的都有同一个元素,所以交换的项的数量等于交换次数+1.<br>
   * 当N=1时,堆 高度为1,无需交换.<br>
   * 当 <1N <= 3 时,堆高度为2,最少交换1次,交换2个项<br>
   * 当N>3时,高度最少为3,根据堆的定义,交换到顶部的最后一个项肯定会比他原来的子树小,所以至少会和第二层的元素发生交换,<br>
   * 也就是至少发生2次交换,交换3个项.<br>
   * 由于第一次交换的时候会使得一个第二层子树的末尾节点移动到了第二层的另一个位置,所以下一次交换,一定是原来的第二层<br>
   * 子树的节点交换到根结点.所以会继续下沉继续到第3层,因为第三层原本就有一个节点是这个节点的父节点.也就是3次交换<br>
   * 第三次也是如此.所以 2次移除最大值 是5次交换,交换6个项. 3次移除最大值 是8次交换,交换9个项 <code>
   *                   100
   *           99               98
   *       9       10      97        96
   *      5 6     7  8   95  94    93  92
   * </code>
   */
  public static void main(String[] args) {}
}
