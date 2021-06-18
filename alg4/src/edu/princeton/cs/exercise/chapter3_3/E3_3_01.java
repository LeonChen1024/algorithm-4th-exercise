package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.1 Draw the 2-3 tree that results when you insert the keys E A S Y Q U T I O N in
 * that order into an initially empty tree.
 * <p>
 * 绘制出当你向一个初始为空的树中按顺序插入 E A S Y Q U T I O N 键的 2-3 树结果.
 *
 * @author LeonChen
 * @since 6/8/21
 */
class E3_3_01 {

    /**
     * @formatter:off
     * 输入值                        结果
     * E                            E
     * ----------------------------------------------
     * A                            AE
     * ----------------------------------------------
     * S                            E
     *                          A       S
     * ----------------------------------------------
     * Y                            E
     *                          A       SY
     * ----------------------------------------------
     * Q                            ES
     *                          A    Q    Y
     * ----------------------------------------------
     * U                            ES
     *                          A    Q    UY
     * ----------------------------------------------
     * T                               S
     *                            E       U
     *                          A   Q   T   Y
     * ----------------------------------------------
     * I                              S
     *                            E       U
     *                          A   IQ   T   Y
     * ----------------------------------------------
     * O                              S
     *                            EO       U
     *                          A  I Q   T   Y
     * ----------------------------------------------
     * N                              S
     *                            EO       U
     *                          A IN Q   T   Y
     * ----------------------------------------------
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
