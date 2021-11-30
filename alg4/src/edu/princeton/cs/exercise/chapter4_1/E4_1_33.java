package edu.princeton.cs.exercise.chapter4_1;

/**
 * 4.1.33 Odd cycles. Prove that a graph is two-colorable (bipartite) if and only if it
 * contains no odd-length cycle.
 * <p>
 * 奇数环.证明一个图只有当它包含非奇数长度环的时候才是双色的
 *
 * @author LeonChen
 * @since 11/11/21
 */
class E4_1_33 {

    /**
     * @formatter:off
     *
     * 1. 证明一个拥有奇数长度环的图不可能是二分的:
     * 如果一个图 G 是二分的,顶点集合分别是 V1 ,V2,每一步都是从 V1 到 V2 或者是 V2 到 V1.在你开始的地方
     * 结束,因此你必须走偶数步.
     *
     * 2.证明只有偶数长的环是二分的:
     * 考虑 G 是一个只有偶数长的环.V0 是任意的顶点.对于每个在相同组件 C0 的 v0 使得 d(v) 是 v0 到 v 的
     * 最短路径.将所有 C0 中距离 v0 距离是奇数的顶点染红,其余的染蓝.对 G 中的每个组件做相同的操作.检查 G
     * 是否有任意边是在两个红色顶点或者两个蓝色顶点之间,这会产生一个奇数环.因此,G 是二分的,红色顶点和蓝色顶点
     * 是两个部分.
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
