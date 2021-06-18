package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.2 Draw the 2-3 tree that results when you insert the keys Y L P M X H C R A E S
 * in that order into an initially empty tree.
 * <p>
 * 绘制出当你向一个初始为空的树中按顺序插入 Y L P M X H C R A E S 键的 2-3 树结果.
 *
 * @author LeonChen
 * @since 6/9/21
 */
class E3_3_02 {

    /**
     * @formatter:off
     * 输入值                        结果
     * Y                            Y
     * ----------------------------------------------
     * L                            LY
     * ----------------------------------------------
     * P                            P
     *                           L     Y
     * ----------------------------------------------
     * M                            P
     *                           LM     Y
     * ----------------------------------------------
     * X                            P
     *                           LM     XY
     * ----------------------------------------------
     * H                             LP
     *                           H   M    XY
     * ----------------------------------------------
     * C                             LP
     *                           CH   M    XY
     * ----------------------------------------------
     * R                                P
     *                              L       X
     *                           CH   M   R   Y
     * ----------------------------------------------
     * A                                P
     *                              CL       X
     *                           A  H   M   R   Y
     * ----------------------------------------------
     * E                                P
     *                              CL        X
     *                           A  EH  M   R   Y
     * ----------------------------------------------
     * S                                P
     *                              CL        X
     *                           A  EH  M   RS   Y
     * ----------------------------------------------
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
