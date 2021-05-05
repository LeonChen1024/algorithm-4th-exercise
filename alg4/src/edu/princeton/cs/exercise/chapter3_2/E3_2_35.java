package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.35 Refined analysis. Refine the mathematical model to better explain the
 * experimental results in the table given in the text. Specifically, show that the
 * average number of compares for a successful search in a tree built from random keys
 * approaches the limit 2lnN + 2√ –3 ≈ 1.39lgN–1.85 as N increases,where √ = .57721..
 * is Euler’s constant. Hint : Referring to the quicksort analysis in Section 2.3, use
 * the fact that the integral of 1/x approaches lnN + √.
 * <p>
 * 精确分析.提炼出一个数学模型来更好的解释正文给定表中的实验结果.特别是展示了在一个随机键组成的树中成功搜索的
 * 对比次数当 N 增长的时候边界是  2lnN + 2√ –3 ≈ 1.39lgN–1.85,其中 √ = .57721.. 这是一个欧拉常量.
 * 提示: 参考 2.3 章中的快排分析,使用 1/x 的积分是 lnN + √ 的事实.
 *
 * @author LeonChen
 * @since 4/27/21
 */
class E3_2_35 {

    /**
     * @formatter:off
     * 平均对比次数是 Cn/N + 1 , 其实 Cn 是插入 N 个键的内部路径长度和.
     * 可知 C0=C1=0 并且对于 N>1 我们可以编写一个首尾相加关系
     *  Cn = N - 1 + (C0 + Cn-1) / N + (C1 + Cn-2) / N + ... + (Cn-1 + C0) / N
     * N-1 是因为根节点给其他所有节点的路径加 1
     * 两边同时乘以 N
     * NCn = N(N - 1) + 2(C0 + C1 + ... + Cn-2 + Cn-1)
     * 减去 N-1 时的公式得到
     * NCn - (N-1)Cn-1 = 2N - 2 + 2Cn-1
     * 除以 N(N+1) 得到
     * Cn / (N + 1) = Cn-1 / N + 2 / (N + 1) - 2 / (N (N + 1))
     * 得
     * Cn ~ 2(N + 1) * (1/3 + 1/4 + ... + 1/(N + 1) - 2)
     * 根据 1/x 的积分是  lnN + √ ,得
     * Cn ~ 2(N + 1) * (ln N + √ - 2)
     * Cn ~ (2N + 2) * (ln N + √ - 2)
     * Cn ~ 2N ln N + 2N √ - 4N + 2ln N + 2√ - 4
     * 平均搜索命中成本是  1 + Cn / N
     * 得
     * AVG Hit = 1 + (2N ln N + 2N √ - 4N + 2ln N + 2√ - 4) / N
     * AVG Hit = 1 + 2 ln N + 2√ + 2√/N - 4 - 4/N
     * 由 2 ln N ~ 1.39 lg N,得
     * AVG Hit = 1.39 lg N + 2√ - 3
     * √ = .57721 ,得
     * AVG Hit ~ 1.39 lg N + 1.154 - 3
     * AVG Hit ~ 1.39 lg N - 1.85
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
