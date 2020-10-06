package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.18 In MaxPQ, suppose that a client calls insert() with an item that is larger than all items
 * in the queue, and then immediately calls delMax(). Assume that there are no duplicate keys. Is
 * the resulting heap identical to the heap as it was before these operations? Answer the same
 * question for two insert() operations (the first with a key larger than all keys in the queue and
 * the second for a key larger than that one) followed by two delMax() operations.
 *
 * <p>在一个最大优先队列中,假设有一个客户端调用了 insert() 方法传入了一个比队列中所有项都大的项,然后立刻调用 delMax()<br>
 * 方法.假设这里没有重复的键.这个结果堆会和它进行这些操作之前一样吗?如果是进行了两次 insert() 操作(第一次传入一个<br>
 * 比队列中所有的键都大的项并且第二次传入的项比第一次的还大)然后再进行两次 delMax()操作这个问题的答案会一样吗?
 *
 * @author LeonChen
 * @since 9/23/20
 */
class E02_04_18 {

  /**
   * 情况一, 首先插入一个最大的值到原来的队列中,使得原来的根结点下沉到新插入值上浮的分支,所以当我们移除最大值的时候,<br>
   * 最后一个节点交换到了根结点,那么它肯定会下沉,因为其中有一个子分支就是原来的根结点,所以这个节点会下沉到它原有的<br>
   * 位置.所以和最开始的时候保持一致.<br>
   * 情况二和情况一类似,连续插入两次,但是都是往插入的分支中下沉,所以同样保持一致.
   *
   * <p>情况一示例<code>
   *            6
   *       5          4
   *   3      2   1
   *
   * 插入 7
   *            6
   *       5          4
   *   3      2   1      7
   *
   * 上浮
   *
   *            7
   *       5          6
   *   3      2   1      4
   *
   * 移除最大值
   *            4
   *       5          6
   *   3      2   1      7
   *
   * 下沉
   *            6
   *       5          4
   *   3      2   1      7
   *
   * </code>
   */
  public static void main(String[] args) {}
}
