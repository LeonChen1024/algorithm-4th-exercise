package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.27 Determine the amount of memory used by Graph to represent a graph with V
 * vertices and E edges, using the memory-cost model of Section 1.4.
 * <p>
 * 判断使用 Graph 来表示一个 V 个顶点和 E 条边的图需要多少的内存,使用 1.4 章节的内存消耗模型
 *
 * @author LeonChen
 * @since 11/5/21
 */
class E4_1_27 {

    /**
     * @formatter:off
     * Integer
     * * object overhead -> 16 bytes
     * * int value -> 4 bytes
     * * padding -> 4 bytes
     * Amount of memory needed: 16 + 4 + 4 = 24 bytes
     *
     * Node
     * * object overhead -> 16 bytes
     * * extra overhead for reference to the enclosing instance -> 8 bytes
     * * Item reference (item) -> 8 bytes
     * * Node reference (next) -> 8 bytes
     * Amount of memory needed: 16 + 8 + 8 + 8 = 40 bytes
     *
     * Bag
     * * object overhead -> 16 bytes
     * * Node reference (first) -> 8 bytes
     * * int value (size) -> 4 bytes
     * * padding -> 4 bytes
     * * N Nodes -> 40N bytes
     * * Integer (item) -> 24N bytes
     * Amount of memory needed: 16 + 8 + 4 + 4 + 40N + 24N = 64N + 32 bytes
     *
     * Graph
     * * object overhead -> 16 bytes
     * * int value (V) -> 4 bytes
     * * int value (E) -> 4 bytes
     * * Bag<Integer>[] reference (adj) -> 8 bytes
     * * Bag<Integer>[] (adj)
     *    object overhead -> 16 bytes
     *    int value (length) -> 4 bytes
     *    padding -> 4 bytes
     *    Bag references -> 8V
     *    Bag -> 64E + 32 bytes -> There are V Bags and in total, they have 2E nodes -> 128E + 32V
     * Amount of memory needed: 16 + 4 + 4 + 8 + 16 + 4 + 4 + 8V + 128E + 32V = 128E + 40V + 56 bytes
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
