package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.31 Equal key check. Write a method hasNoDuplicates() that takes a Node as
 * argument and returns true if there are no equal keys in the binary tree rooted at the
 * argument node, false otherwise. Assume that the test of the previous exercise has passed.
 * <p>
 * 相等键校验.编写一个方法 hasNoDuplicates() 接收一个节点作为参数如果以参数节点为根的二叉树里没有相等的键
 * 就返回 true , 否则返回 false.假设前面练习的检验都通过了
 *
 * @author LeonChen
 * @since 4/25/21
 */
class E3_2_31 {

    /**
     * @formatter:off
     * 可以使用递归取出节点使用 hashset 等集合来测试重复性, 但是总的来的说觉得题目有歧义,如果说通过了前面
     * 的测试,那么不应该会出现重复的情况,因为重复节点最中会落到同一个节点位置.
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
