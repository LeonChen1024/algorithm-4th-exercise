package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.20 Compute the internal path length in a perfectly balanced BST of N nodes, when
 * N is a power of 2 minus 1.
 * <p>
 * 计算一个 N 节点的完美平衡BST 的内部路径长度,当 N 是 2 的幂次-1
 *
 * @author LeonChen
 * @since 6/24/21
 */
class E3_3_20 {

    /**
     * @formatter:off
     * N = 2^h-1    h 是树的高度
     *
     * 内部路径长度 = ∑_{i=0}^{h-1} 2^i *i  每层节点数除以每层路径长度
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
