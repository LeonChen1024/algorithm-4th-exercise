package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.11 How many binary tree shapes of N nodes are there with height N? How many
 * different ways are there to insert N distinct keys into an initially empty BST that result
 * in a tree of height N? (See Exercise 3.2.2.)
 * <p>
 * 高度为 N 节点数量为 N 的二叉树有集中形状?有多少种不同的方式插入 N 中不同的键到空 BST 中会出现高度为 N
 * 的树?
 *
 * @author LeonChen
 * @since 4/8/21
 */
class E3_2_11 {

    /**
     * @formatter:off
     * 除了根节点,其他的节点都只有左右子树两个节点可选,所以是 $ 2^{N-1} $种形状.
     * 由于每层只有一个节点.插入顺序一定和层高一致,所以一旦交换一定会导致树的形状发生变化.所以只有
     * $ 2^{N-1} $种输入方式
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
