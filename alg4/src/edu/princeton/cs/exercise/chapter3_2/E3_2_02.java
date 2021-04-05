package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.2 Inserting the keys in the order A X C S E R H into an initially empty BST gives
 * a worst-case tree where every node has one null link, except one at the bottom, which
 * has two null links. Give five other orderings of these keys that produce worst-case
 * trees.
 * <p>
 * 按照 A X C S E R H 的顺序插入键到一个空 BST 会出现最差树情况,每个节点都有一个空链接,除了最底层的一个,
 * 它有两个空链接.给出 5 种这些键会产生最坏情况树的排序
 *
 * @author LeonChen
 * @since 3/30/20
 */
class E3_2_02 {

    /**
     * @formatter:off
     * 1.   A C E H R S X   一列右子树
     * 2.   X S R H E C A   一列左子树
     * 3.   A C E H R X S   一列右子树除了最后一个节点左子树
     * 4.   X S R H E A C   一列左子树除了最后一个节点右子树
     * 5.   A C E H X S R   一列右子树除了最后两个节点左子树
     *
     */
    public static void main(String[] args) {
    }



}
