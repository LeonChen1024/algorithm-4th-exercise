package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.14 Draw the red-black BST that results when you insert letters A through K in order
 * into an initially empty tree, then describe what happens in general when trees are built
 * by insertion of keys in ascending order (see also the figure in the text).
 * <p>
 * 绘制出当你按序插入字母 A 到 K到初始空树中的红黑树结果,然后描述当构建升序插入树发生了什么(可以看正文中的图)
 *
 * @author LeonChen
 * @since 6/19/21
 */
class E3_3_14 {

    /**
     * @formatter:off
     * 输入值                              结果
     * A                                    A
     *
     * ----------------------------------------------------------------
     * B                                    B
     *                                rA
     *
     * ----------------------------------------------------------------
     * C                                    B
     *                                A            C
     *
     * ----------------------------------------------------------------
     * D                                    B
     *                                A            D
     *                                          rC
     *
     * ----------------------------------------------------------------
     * E                                    D
     *                                rB          E
     *                             A     C
     *
     * ----------------------------------------------------------------
     * F                                    D
     *                                rB          F
     *                             A     C     rE
     *
     * ----------------------------------------------------------------
     * G                                    D
     *                                B          F
     *                             A     C     E        G
     *
     * ----------------------------------------------------------------
     * H                                    D
     *                                B             F
     *                             A     C     E        H
     *                                               rG
     *
     * ----------------------------------------------------------------
     * I                                    D
     *                                B             H
     *                             A     C     rF        I
     *                                       E    G
     *
     * ----------------------------------------------------------------
     * J                                    D
     *                                B             H
     *                             A     C     rF        J
     *                                       E    G    rI
     *
     * ----------------------------------------------------------------
     * K                                    H
     *                                rD                     J
     *                           B        F                I    K
     *                        A     C   E    G
     *
     *
     * ----------------------------------------------------------------
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
