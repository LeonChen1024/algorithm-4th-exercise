package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.11 Give the contents of a linear-probing hash table that results when you insert the
 * keys E A S Y Q U T I O N in that order into an initially empty table of initial size M
 * = 4 that is expanded with doubling whenever half full. Use the hash function 11 k % M
 * to transform the kth letter of the alphabet into a table index.
 * <p>
 * 给出有序插入 E A S Y Q U T I O N 键到初始为空的线性探查表中,其中初始 M = 4 当一半满了之后就翻倍.使用
 * hash 函数 11 k % M 来转换字母表第 k 个字母到表索引
 *
 * @author LeonChen
 * @since 7/30/21
 */
class E3_4_11 {

    /**
     * @formatter:off
     *
     * 插入        hash                   结果
     *
     *  M = 4
     * E       11*5%4=3                 null
     *                                  null
     *                                  null
     *                                  E
     * --------------------------------------------------
     * A       11*1%4=5                 null
     *                                  null
     *                                  null
     *                                  E A
     * --------------------------------------------------
     * S       11*19%4=1                null
     *                                  S
     *                                  null
     *                                  E A
     * --------------------------------------------------
     * 扩容 M = 8
     * E       11*5%8=7
     * A       11*1%8=3
     * S       11*19%8=1
     * Y       11*25%8=3                null
     *                                  S
     *                                  null
     *                                  A Y
     *                                  null
     *                                  null
     *                                  null
     *                                  E
     * --------------------------------------------------
     * Q       11*17%8=3                null
     *                                  S
     *                                  null
     *                                  A Y
     *                                  null
     *                                  null
     *                                  null
     *                                  E
     * --------------------------------------------------
     * U       11*21%8=7                null
     *                                  S
     *                                  null
     *                                  A Y
     *                                  null
     *                                  null
     *                                  null
     *                                  E U
     * --------------------------------------------------
     * T       11*20%8=4                null
     *                                  S
     *                                  null
     *                                  A Y
     *                                  T
     *                                  null
     *                                  null
     *                                  E U
     * --------------------------------------------------
     * 扩容 M = 16
     *
     *
     *
     *
     *
     *
     * E       11*5%16=7                null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  E
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * A       11*1%16=5                null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  A
     *                                  null
     *                                  E
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * S       11*19%16=1               null
     *                                  S
     *                                  null
     *                                  null
     *                                  null
     *                                  A
     *                                  null
     *                                  E
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * Y       11*25%16=3               null
     *                                  S
     *                                  null
     *                                  Y
     *                                  null
     *                                  A
     *                                  null
     *                                  E
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * Q       11*17%16=3               null
     *                                  S
     *                                  null
     *                                  Y
     *                                  null
     *                                  A
     *                                  null
     *                                  E
     *                                  null
     *                                  null
     *                                  null
     *                                  Q
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * U       11*21%16=7               null
     *                                  S
     *                                  null
     *                                  Y
     *                                  null
     *                                  A
     *                                  null
     *                                  E U
     *                                  null
     *                                  null
     *                                  null
     *                                  Q
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * T       11*20%16=12              null
     *                                  S
     *                                  null
     *                                  Y
     *                                  null
     *                                  A
     *                                  null
     *                                  E U
     *                                  null
     *                                  null
     *                                  null
     *                                  Q
     *                                  T
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * I       11*9%16=3                null
     *                                  S
     *                                  null
     *                                  Y I
     *                                  null
     *                                  A
     *                                  null
     *                                  E U
     *                                  null
     *                                  null
     *                                  null
     *                                  Q
     *                                  T
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * O       11*15%16=5               null
     *                                  S
     *                                  null
     *                                  Y I
     *                                  null
     *                                  A O
     *                                  null
     *                                  E U
     *                                  null
     *                                  null
     *                                  null
     *                                  Q
     *                                  T
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * N       11*14%16=10              null
     *                                  S
     *                                  null
     *                                  Y I
     *                                  null
     *                                  A O
     *                                  null
     *                                  E U
     *                                  null
     *                                  null
     *                                  N
     *                                  Q
     *                                  T
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
