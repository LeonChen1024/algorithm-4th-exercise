package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.10 Insert the keys E A S Y Q U T I O N in that order into an initially empty table
 * of size M =16 using linear probing. Use the hash function 11 k % M to transform the
 * kth letter of the alphabet into a table index. Redo this exercise for M = 10.
 * <p>
 * 插入键 E A S Y Q U T I O N 到一个空表中,其中表 M =16 并使用线性探测.使用 hash 函数 11 k % M 来
 * 转换字母表里第 k 个字母到表索引.再使用 M = 10 来计算
 *
 * @author LeonChen
 * @since 7/30/21
 */
class E3_4_10 {

    /**
     * @formatter:off
     * M = 16
     * 插入        hash                   结果
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
     *
     *
     *
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     * --------------------------------------------------
     *
     *
     *
     *
     * M = 10
     * 插入        hash                   结果
     * E       11*5%10=5                null
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     *                                  E
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * A       11*1%10=1                null
     *                                  A
     *                                  null
     *                                  null
     *                                  null
     *                                  E
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * S       11*19%10=9               null
     *                                  A
     *                                  null
     *                                  null
     *                                  null
     *                                  E
     *                                  null
     *                                  null
     *                                  null
     *                                  S
     * --------------------------------------------------
     * Y       11*25%10=5               null
     *                                  A
     *                                  null
     *                                  null
     *                                  null
     *                                  E Y
     *                                  null
     *                                  null
     *                                  null
     *                                  S
     * --------------------------------------------------
     * Q       11*17%10=7               null
     *                                  A
     *                                  null
     *                                  null
     *                                  null
     *                                  E Y
     *                                  null
     *                                  Q
     *                                  null
     *                                  S
     * --------------------------------------------------
     * U       11*21%10=1               null
     *                                  A U
     *                                  null
     *                                  null
     *                                  null
     *                                  E Y
     *                                  null
     *                                  Q
     *                                  null
     *                                  S
     * --------------------------------------------------
     * T       11*20%10=0               T
     *                                  A U
     *                                  null
     *                                  null
     *                                  null
     *                                  E Y
     *                                  null
     *                                  Q
     *                                  null
     *                                  S
     * --------------------------------------------------
     * I       11*9%10=9                T
     *                                  A U
     *                                  null
     *                                  null
     *                                  null
     *                                  E Y
     *                                  null
     *                                  Q
     *                                  null
     *                                  S I
     * --------------------------------------------------
     * O       11*15%10=5               T
     *                                  A U
     *                                  null
     *                                  null
     *                                  null
     *                                  E Y O
     *                                  null
     *                                  Q
     *                                  null
     *                                  S I
     * --------------------------------------------------
     * N       11*14%10=4               T
     *                                  A U
     *                                  null
     *                                  null
     *                                  N
     *                                  E Y O
     *                                  null
     *                                  Q
     *                                  null
     *                                  S I
     * --------------------------------------------------
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
