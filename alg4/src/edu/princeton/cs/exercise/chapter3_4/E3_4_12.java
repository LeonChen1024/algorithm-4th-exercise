package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.12 Suppose that the keys A through G, with the hash values given below, are inserted
 * in some order into an initially empty table of size 7 using a linear-probing table (with
 * no resizing for this problem). Which of the following could not possibly result from
 * inserting these keys?
 * <p>
 * a.EFGACBD
 * b.CEBGFDA
 * c.BDFACEG
 * d.CGBADEF
 * e.FGBDACE
 * f.GECADBF
 * <p>
 * Give the minimum and the maximum number of probes that could be required to
 * build a table of size 7 with these keys, and an insertion order that justifies your answer.
 * <p>
 * 假设有键从 A 到 G ,使用下面给定的 hash 值,用不同顺序插入到初始为空大小为 7 使用线性探查的表中(不用扩容).
 * 下面的结果不可能出现
 * <p>
 * 给出构建一个使用这些键大小为 7 的表最小和最大需要的探查数量,并且给出一个插入顺序来证明
 *
 * @author LeonChen
 * @since 7/30/21
 */
class E3_4_12 {

    /**
     * @formatter:off
     * 不理解怎么来的
     * The alternatives A, B, C, D, E and F could not possibly result from inserting these keys.
     *
     * The minimum and maximum number of probes required to build a table of size 7 with these keys is 12:
     * 1 + 2 + 1 + 2 + 1 + 2 + 3 = 12 probes
     *
     * Insertion order:
     * B, C, A, G, D, E, F
     *
     * This is because regardless of the insertion order, there will always be 12 probes since the hash collision does not overlap from one hash value to another (both keys with hash value 0 will be distributed in the indexes 0 and 1; both keys with hash value 2 will be distributed in the indexes 2 and 3 and the three keys with hash value 4 will be distributed in the indexes 4, 5 and 6).
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
