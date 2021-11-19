package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.14 Suppose you use a stack instead of a queue when running breadth-first search.
 * Does it still compute shortest paths?
 * <p>
 * 假设你在运行广度优先搜索时使用一个 stack 来替代 queue ,他是否仍然计算了最短路径?
 *
 * @author LeonChen
 * @since 10/30/21
 */
class E4_1_14 {

    /**
     * @formatter:off
     * 不会,因为 stack 的后入先出原则,他的检索顺序被改变
     * 如 :
     *         ___1____
     *         |      |
     *         2      3
     *         |      |
     *         4------5
     *
     * 如果先插入 1-3 比 1-2 先插入,那么 1-5 的距离就变成 3 而不是 2
     *
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
