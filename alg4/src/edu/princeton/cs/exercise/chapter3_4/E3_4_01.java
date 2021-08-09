package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.1 Insert the keys E A S Y Q U T I O N in that order into an initially empty table
 * of M = 5 lists, using separate chaining. Use the hash function 11 k % M to transform
 * the kth letter of the alphabet into a table index.
 * <p>
 * 按顺序插入 E A S Y Q U T I O N 到一个初始为空的M=5表中,使用独立链接.使用 hash 函数 11 k % M 来
 * 将字母表中第 k 个字母转换为表索引
 *
 * @author LeonChen
 * @since 7/21/21
 */
class E3_4_01 {

    /**
     * @formatter:off
     *
     * 插入        hash                   结果
     * E       11*5%5=0                 E
     *                                  null
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * A       11*1%5=1                 E
     *                                  A
     *                                  null
     *                                  null
     *                                  null
     * --------------------------------------------------
     * S       11*19%5=4                E
     *                                  A
     *                                  null
     *                                  null
     *                                  S
     * --------------------------------------------------
     * Y       11*25%5=0                E Y
     *                                  A
     *                                  null
     *                                  null
     *                                  S
     * --------------------------------------------------
     * Q       11*17%5=2                E Y
     *                                  A
     *                                  Q
     *                                  null
     *                                  S
     * --------------------------------------------------
     * U       11*21%5=1                E Y
     *                                  A U
     *                                  Q
     *                                  null
     *                                  S
     * --------------------------------------------------
     * T       11*20%5=0                E Y T
     *                                  A U
     *                                  Q
     *                                  null
     *                                  S
     * --------------------------------------------------
     * I       11*9%5=4                 E Y T
     *                                  A U
     *                                  Q
     *                                  null
     *                                  S I
     * --------------------------------------------------
     * O       11*15%5=0                E Y T O
     *                                  A U
     *                                  Q
     *                                  null
     *                                  S I
     * --------------------------------------------------
     * N       11*14%5=0                E Y T O
     *                                  A U
     *                                  Q
     *                                  null
     *                                  S I N
     * --------------------------------------------------
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
