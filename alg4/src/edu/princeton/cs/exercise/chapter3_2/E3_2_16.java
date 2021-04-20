package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.16 Define the external path length of a tree to be the sum of the number of nodes on
 * the paths from the root to all null links. Prove that the difference between the external
 * and internal path lengths in any binary tree with N nodes is 2N (see Proposition C).
 * <p>
 * 定义一个树的外部路径长度,它的值是从根到所有空链接的路径之和.证明在一个 N 个节点的二叉树中外部路径和内部路径
 * 长度的差额为 2N(见定理C)
 *
 * @author LeonChen
 * @since 4/13/20
 */
class E3_2_16 {

    /**
     * @formatter:off
     * 外部路径长度为 E,内部路径长度 I,当你删除了一个路径长度为 k的节点的时候,E 会减少 2(k+1) 但是由于v 节点
     * 原本的位置会变成一个外部节点 E 会增加 k,所以总的减少 k+2. I 会减少 k.
     * 重复 N 次操作之后 E=I+2N ,得证
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
