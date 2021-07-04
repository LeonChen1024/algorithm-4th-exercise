package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.24 Worst case for red-black BSTs. Show how to construct a red-black BST
 * demonstrating that, in the worst case, almost all the paths from the root to a null
 * link in a red-black BST of N nodes are of length 2lgN.
 * <p>
 * 红黑树的最差情况.展示如何构造一个红黑树处于最差的情况,所有从根节点到 null 链接的长度是 2lgN
 *
 * @author LeonChen
 * @since 6/27/21
 */
class E3_3_24 {

    /**
     * @formatter:off
     * 倒序插入  1, ..., 2^(n + 1) - 2  个节点,形成一棵瘦树
     *
     * 例如 654321
     *                     (B)5
     *                (R)3      (B)6
     *             (B)2  (B)4
     *           (R)1
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
