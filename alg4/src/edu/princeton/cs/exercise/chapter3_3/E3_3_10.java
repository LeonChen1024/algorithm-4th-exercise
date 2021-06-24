package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.10 Draw the red-black BST that results when you insert items with the keys
 * E A S Y Q U T I O N in that order into an initially empty tree.
 * <p>
 * 绘制出当你按顺序向一个初始为空的树中插入 E A S Y Q U T I O N 的结果
 *
 * @author LeonChen
 * @since 6/16/21
 */
class E3_3_10 {

    /**
     * @formatter:off
     * 插入值                              结果
     * E                                   E
     *
     * ------------------------------------------------------
     * A                                   E
     *                                  rA
     *
     * ------------------------------------------------------
     * S                                   E
     *                                  A      S
     *
     * ------------------------------------------------------
     * Y                                   E
     *                                  A       Y
     *                                        rS
     *
     * ------------------------------------------------------
     * Q                                      S
     *                                  rE        Y
     *                               A      Q
     *
     * ------------------------------------------------------
     * U                                      S
     *                                  rE           Y
     *                               A      Q    rU
     *
     * ------------------------------------------------------
     * T                                      S
     *                                  E            U
     *                               A      Q    T       Y
     *
     * ------------------------------------------------------
     * I                                      S
     *                                  E            U
     *                               A      Q    T       Y
     *                                    rI
     *
     * ------------------------------------------------------
     * O                                      S
     *                                  O            U
     *                            rE       Q      T       Y
     *                          A    I
     *
     * ------------------------------------------------------
     * N                                      S
     *                                  O            U
     *                            rE       Q      T       Y
     *                          A     N
     *                             rI
     *
     * ------------------------------------------------------
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
