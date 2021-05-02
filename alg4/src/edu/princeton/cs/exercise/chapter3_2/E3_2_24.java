package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.24 Prove that no compare-based algorithm can build a BST using fewer than
 * lg(N!) ~ NlgN compares.
 * <p>
 * 证明基于对比算法不可能构建出一个对比次数小于 lg(N!)~NlgN 的 BST
 *
 * @author LeonChen
 * @since 4/21/21
 */
class E3_2_24 {

    /**
     * @formatter:off
     * 对比次数最少情况是平衡二叉树的时候,此时每次对比需要 lgN 次
     * N 是递增的,所以一个大小为 N 的平衡二叉树的对比次数和为
     * lg 1 + lg 2 + ... + lg N = lg(1 * 2 * ... * N) = lg(N!)
     * 所以不可能小于 lg(N!),得证
     *
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
