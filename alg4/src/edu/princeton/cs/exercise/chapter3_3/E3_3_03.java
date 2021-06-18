package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.3 Find an insertion order for the keys S E A R C H X M that leads to a 2-3 tree
 * of height 1.
 * <p>
 * 找到键 S E A R C H X M 的插入顺序得到一个 2-3 树的高度是 1
 *
 * @author LeonChen
 * @since 6/10/21
 */
class E3_3_03 {

    /**
     * @formatter:off
     * 总共 8 个元素,高度为 1 , 也就是 4 个 3-节点组成,按顺序排序得到ACEHMRSX, 因为根节点要有 3 个
     * 子节点并且分别是最小,之间,最大规则,所以可以得到最后的结果是
     *          ER
     *     AC   HM  SX
     * 反推插入顺序得 E M R X A C H S
     *
     * 输入值                        结果
     *  E                            E
     * --------------------------------------------
     *  M                            E
     *                                 M
     * --------------------------------------------
     *  R                            E
     *                                   MR
     * --------------------------------------------
     *  X                            ER
     *                                M X
     * --------------------------------------------
     *  A                            ER
     *                              A M X
     * --------------------------------------------
     *  C                            ER
     *                             AC M X
     * --------------------------------------------
     *  H                            ER
     *                            AC HM X
     * --------------------------------------------
     *  S                            ER
     *                            AC HM SX
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
