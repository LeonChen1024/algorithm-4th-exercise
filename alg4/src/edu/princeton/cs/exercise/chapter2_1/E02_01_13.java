package edu.princeton.cs.exercise.chapter2_1;

/**
 * 2.1.13 Deck sort. Explain how you would put a deck of cards in order by suit (in the order
 * spades, hearts, clubs, diamonds) and by rank within each suit, with the restriction that the
 * cards must be laid out face down in a row, and the only allowed operations are to check the
 * values of two cards and to exchange two cards (keeping them face down).
 *
 * <p>纸牌排序.说明一下你会如何将一副扑克牌根据花色排序(顺序是 黑桃,红心,梅花,方快)并且每个花色牌按照大小排序,<br>
 * 要求是卡片是正面朝下排成一行,只允许每次检查两张卡片的值和交换两张卡片的位置(保持他们正面朝下).
 *
 * @author LeonChen
 * @since 5/18/20
 */
class E02_01_13 {

  /** 使用 shellsort 或者 插入排序, 在对比牌大小的时候按照花色顺序依次加上(0,1,2,3)*13的值,这样排完就是要求的顺序了 */
  public static void main(String[] args) {}
}
