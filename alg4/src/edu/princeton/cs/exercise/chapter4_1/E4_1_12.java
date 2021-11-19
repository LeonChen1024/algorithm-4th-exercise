package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.12 What does the BFS tree tell us about the distance from v to w when neither is
 * at the root?
 * <p>
 * BFS 树在 v 和 w 都不在根节点的时候,会告诉我们有关他们距离的什么信息
 *
 * @author LeonChen
 * @since 10/28/21
 */
class E4_1_12 {

    /**
     * @formatter:off
     * 当 v 和 w 都不在根节点的时候
     * 如果 v 和 w 在同一个分支上,那么他们的距离是这个分支中他们的距离.
     * 如果 v 和 w 不在同一个分支上,那么他们之间有一条距离为根节点到 v 的距离+根节点到 w 的距离 的路径
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
