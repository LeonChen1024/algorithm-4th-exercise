package edu.princeton.cs.exercise.chapter2_4;

/**
 * 2.4.1 Suppose that the sequence P R I O * R * * I * T * Y * * * Q U E * * * U * E (where a letter
 * means insert and an asterisk means remove the maximum) is applied to an initially empty priority
 * queue. Give the sequence of letters returned by the remove the maximum operations.
 *
 * <p>假设有这个一个序列 P R I O * R * * I * T * Y * * * Q U E * * * U * E (其中字母代表着插入而星号代表着<br>
 * 移除最大值)被应用到一个初始为空的优先队列.给出移除最大值操作后的字母序列
 *
 * @author LeonChen
 * @since 9/14/20
 */
class E02_04_01 {

  /**
   * <code>
   *   输入    优先队列               移除的队列
   *   P       P
   *   R       R P
   *   I       R P I
   *   O       R P O I
   *   *       P O I                  R
   *   R       R P O I                R
   *   *       P O I                  R R
   *   *       O I                    R R P
   *   I       O I I                  R R P
   *   *       I I                    R R P O
   *   T       T I I                  R R P O
   *   *       I I                    R R P O T
   *   Y       Y I I                  R R P O T
   *   *       I I                    R R P O T Y
   *   *       I                      R R P O T Y I
   *   *                              R R P O T Y I I
   *   Q       Q                      R R P O T Y I I
   *   U       U Q                    R R P O T Y I I
   *   E       U Q E                  R R P O T Y I I
   *   *       Q E                    R R P O T Y I I U
   *   *       E                      R R P O T Y I I Q
   *   *                              R R P O T Y I I E
   *   U       U                      R R P O T Y I I E
   *   *                              R R P O T Y I I E U
   *   E       E                      R R P O T Y I I E U
   *
   * </code>
   */
  public static void main(String[] args) {}
}
