package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.15 Answer the previous two questions for the case when the keys are inserted in
 * descending order.
 * <p>
 * 当插入顺序是倒序的时候,回答前两个问题
 *
 * @author LeonChen
 * @since 6/20/21
 */
class E3_3_15 {

    /**
     * @formatter:off
     * 错.
     *
     * 输入值                              结果
     * K                                    K
     *
     * ----------------------------------------------------------------
     * J                                    K
     *                             rJ
     *
     * ----------------------------------------------------------------
     * I                                    J
     *                             I               K
     *
     *
     * ----------------------------------------------------------------
     * H                                    J
     *                             I               K
     *                      rH
     *
     * ----------------------------------------------------------------
     * G                                    J
     *                             rH               K
     *                        G          I
     *
     * ----------------------------------------------------------------
     * F                                    J
     *                             rH               K
     *                        G          I
     *                  rF
     *
     *
     * ----------------------------------------------------------------
     * E                                   H
     *                          F                 J
     *                      E         G        I      K
     *                      高度降低了
     *
     * ----------------------------------------------------------------
     * D                                   H
     *                          F                 J
     *                      E         G        I      K
     *                 rD
     *
     * ----------------------------------------------------------------
     * C                                   H
     *                          F                 J
     *                     rD         G        I      K
     *                 C       E
     *
     * ----------------------------------------------------------------
     * B                                   H
     *                          F                 J
     *                     rD         G        I      K
     *                 C       E
     *          rB
     * ----------------------------------------------------------------
     * A                                   H
     *                         rD                  J
     *                     B         F         I      K
     *                A       C    E    G
     *
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
