package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.1 What is the maximum number of edges in a graph with V vertices and no parallel
 * edges? What is the minimum number of edges in a graph with V vertices, none of which
 * are isolated?
 * <p>
 * 一个有 V 个顶点并且没有平行边的图中最多可以有多少的边?没有孤立的顶点的情况下最少可以有多少边?
 *
 * @author LeonChen
 * @since 10/20/21
 */
class E4_1_01 {

    /**
     * @formatter:off
     * 最多可以有 V*(V-1)/2 个.
     * 每个节点可以连接 V-1 个节点,但是往返重复了一次,所以除以 2
     *
     * 最少可以有 V-1 个.
     * 每个节点只有一条边,2 个节点构成 1 边
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
