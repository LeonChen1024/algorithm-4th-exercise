package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.32 Certification. Write a method isBST() that takes a Node as argument and returns
 * true if the argument node is the root of a binary search tree, false otherwise.
 * Hint : This task is also more difficult than it might seem, because the order in which
 * you call the methods in the previous three exercises is important.
 * @formatter:off
 * Solution :
 * private boolean isBST()
 * {
 *      if (!isBinaryTree(root)) return false;
 *      if (!isOrdered(root, min(), max())) return false;
 *      if (!hasNoDuplicates(root)) return false;
 *      return true;
 * }
 * @formatter:on
 * <p>
 * 认证.编写一个方法 isBST() 接收一个节点作为参数并且如果该参数是一个二分搜索树的根节点就返回 true,否则返回
 * false. 提示: 这个任务比它看起来的还要复杂,因为你调用前 3 个练习的顺序是很重要的
 *
 * @author LeonChen
 * @since 4/25/21
 */
class E3_2_32 {

    /**
     * @formatter:off
     * 使用前3个练习的 isBinaryTree() && isOrdered() && hasNoDuplicates() 来判断
     * 题目已给解决方案
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
