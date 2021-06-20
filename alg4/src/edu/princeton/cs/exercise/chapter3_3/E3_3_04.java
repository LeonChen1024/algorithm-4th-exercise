package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.4 Prove that the height of a 2-3 tree with N keys is between ~ ⎣log_3^N⎦ ~ .63lgN
 * (for a tree that is all 3-nodes) and ~⎣lg N⎦ (for a tree that is all 2-nodes).
 * <p>
 * 证明一个 N 个键的 2-3 树的高度是在 ~ ⎣log_3^N⎦ ~ .63lgN(对于所有节点是 3-节点的树) 和 ~⎣lg N⎦
 * (对与一个所有节点都是 2-节点的树) 之间.
 *
 * @author LeonChen
 * @since 6/11/21
 */
class E3_3_04 {

    /**
     * @formatter:off
     * 3-节点的树每次对比可以减少 1/3 的范围, 而 2-节点每次对比只能减少 1/2 的范围,所以可以得到这个高度
     * 结果
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
