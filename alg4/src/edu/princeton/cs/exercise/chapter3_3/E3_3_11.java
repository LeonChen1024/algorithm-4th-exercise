package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.11 Draw the red-black BST that results when you insert items with the keys
 * Y L P M X H C R A E S in that order into an initially empty tree.
 * <p>
 * 绘制出当你按顺序向一个初始为空的树中插入 Y L P M X H C R A E S 的结果
 *
 * @author LeonChen
 * @since 6/17/21
 */
class E3_3_11 {

    /**
     * @formatter:off
     * 插入值                              结果
     * Y                                   Y
     *
     * ------------------------------------------------------
     * L                                   Y
     *                             rL
     *
     * ------------------------------------------------------
     * P                                   P
     *                             L               Y
     *
     * ------------------------------------------------------
     * M                                   P
     *                             M               Y
     *                        rL
     * ------------------------------------------------------
     * X                                   P
     *                             M                Y
     *                        rL                rX
     *
     * ------------------------------------------------------
     * H                                   P
     *                             rL                 Y
     *                        H         M        rX
     *
     * ------------------------------------------------------
     * C                                   P
     *                             rL                 Y
     *                        H         M        rX
     *                  rC
     * ------------------------------------------------------
     * R                                   P
     *                             L                 X
     *                        H         M        R          Y
     *                  rC
     * ------------------------------------------------------
     * A                                   P
     *                             L                 X
     *                      rC         M        R          Y
     *                  A       H
     *
     * ------------------------------------------------------
     * E                                   P
     *                             L                 X
     *                      rC         M        R          Y
     *                  A       H
     *                      rE
     *
     * ------------------------------------------------------
     * S                                   P
     *                             L                 X
     *                      rC         M        S          Y
     *                  A       H            rR
     *                       rE
     *
     * ------------------------------------------------------
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
