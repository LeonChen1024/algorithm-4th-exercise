package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.20 Prove that the running time of the two-argument keys() in a BST with N nodes
 * is at most proportional to the tree height plus the number of keys in the range.
 * <p>
 * 证明 在 N 个节点的 BST 中两个参数的keys()的运行时间接近树的高度的一定比例比例加上范围内的键数量
 *
 * @author LeonChen
 * @since 4/17/21
 */
class E3_2_20 {

    /**
     * @formatter:off
     * keys() 的执行逻辑是按照层高往下两侧遍历,把在范围中间的键加入到队列.
     * 所以消耗时间就是 树高的倍数+范围内的键数量
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
