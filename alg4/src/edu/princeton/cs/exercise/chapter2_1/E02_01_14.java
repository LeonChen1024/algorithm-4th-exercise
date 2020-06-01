package edu.princeton.cs.exercise.chapter2_1;

/**
 * 2.1.14 Dequeue sort. Explain how you would sort a deck of cards, with the restriction that the
 * only allowed operations are to look at the values of the top two cards, to exchange the top two
 * cards, and to move the top card to the bottom of the deck.
 *
 * <p>出队排序.说明一下你会如何排序一副纸牌,要求只能看头两张牌,改变头两张牌的顺序,并且移动最顶上的牌到纸牌底部.
 *
 * @author LeonChen
 * @since 5/18/20
 */
class E02_01_14 {

  /**
   * 假设要求按照正序排列 <br>
   * 1.对比顶上两张牌是否是逆序,如果不是则交换.<br>
   * 2.第一次顶部的牌要做个标记,之后才知道什么时候遍历了一遍<br>
   * 3.然后将顶部的牌放到牌堆最后.<br>
   * 4.重复1和3,直到标记的牌变成第二张牌 <br>
   * 5.将顶部的牌放到最后,此时被标记的牌位于顶端,代表这次循环结束.这时候牌底的牌是最小的,<br>
   * 以此类推每次循环都至少会得到一位确定的值 <br>
   * 6.如果在某次循环中没有发生交换,那么纸牌就是有序的.否则重复步骤1到6.
   */
  public static void main(String[] args) {}
}
