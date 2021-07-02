package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.17 Generate two random 16-node red-black BSTs. Draw them (either by hand or with
 * a program). Compare them with the (unbalanced) BSTs built with the same keys.
 * <p>
 * 生成两个随机 16-节点红黑树.绘制出他们(用手或者程序都行).将它和相同键的(不平衡)BST 对比
 *
 * @author LeonChen
 * @since 6/22/21
 */
class E3_3_17 {

    /**
     * @formatter:off
     * 随机节点序列 1: E I Z N L S H A P Q M C X B W R
     *  红黑树:
     *                                  S
     *                   rI                                 X
     *             E           Q                          W    Z
     *        rB     H     rN      R
     *      A   C         M   P
     *                  rL
     *
     *
     *   不平衡BST:
     *                                  E
     *              A                                           I
     *                        C                     H                       Z
     *                  B                                              N
     *                                                              L     S
     *                                                                M P    X
     *                                                                   Q  W
     *                                                                    R
     *
     * 随机节点序列 1: W E X H I S Z A N L P Q M C B R
     *  红黑树:
     *                                      N
     *                    rH                                       W
     *            C                L                    rQ                   Z
     *       B       E         I       M             P       S           rX
     *   rA                                                rR
     *
     *  不平衡 BST:
     *                                      W
     *                 E                                            X
     *        A                     H                                       Z
     *            C                     I
     *          B                          S
     *                                  N
     *                                L   P
     *                                 M   Q
     *                                      R
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
