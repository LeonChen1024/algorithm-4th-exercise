package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.19 Draw the sequence of BSTs that results when you delete the keys from the tree
 * of Exercise 3.2.1, one by one, by successively deleting the key at the root.
 * <p>
 * 绘制出将练习 3.2.1 中的二叉树一个个删除根节点后的 BST 序列
 *
 * @author LeonChen
 * @since 4/16/20
 */
class E3_2_19 {

    /**
     * @formatter:off
     *
     *  删除值                        树路径
     *
     *                                E-7
     *                       A-2               S-8
     *                                   Q-5         Y-4
     *                               I-10         U-6
     *                                  O-11    T-9
     *                                N-12
     * -----------------------------------------------------------------
     *   E
     *                                I-10
     *                       A-2               S-8
     *                                   Q-5         Y-4
     *                               O-11         U-6
     *                             N-12         T-9
     *
     * -----------------------------------------------------------------
     *   I
     *                                N-12
     *                       A-2               S-8
     *                                   Q-5         Y-4
     *                               O-11         U-6
     *                                         T-9
     *
     *  -----------------------------------------------------------------                         I-10
     *   N
     *                                O-11
     *                       A-2               S-8
     *                                   Q-5         Y-4
     *                                            U-6
     *                                         T-9
     *
     *  -----------------------------------------------------------------                                Q-5         Y-4
     *   O
     *                                Q-5
     *                       A-2               S-8
     *                                               Y-4
     *                                            U-6
     *                                         T-9
     *  -----------------------------------------------------------------
     *   Q
     *                                S-8
     *                       A-2               Y-4
     *                                      U-6
     *                                    T-9
     *
     *  -----------------------------------------------------------------
     *   S
     *                                T-9
     *                       A-2               Y-4
     *                                      U-6
     *
     *  -----------------------------------------------------------------
     *   T
     *                                U-6
     *                       A-2               Y-4
     *
     *  -----------------------------------------------------------------
     *   U
     *                               Y-4
     *                       A-2
     *  -----------------------------------------------------------------
     *   Y
     *                               A-2
     *
     *
     *  -----------------------------------------------------------------
     *   A
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}