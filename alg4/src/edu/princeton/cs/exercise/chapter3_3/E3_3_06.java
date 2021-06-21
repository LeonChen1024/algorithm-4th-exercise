package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.6 Find the probability that each of the 2-3 trees in Exercise 3.3.5 is the
 * result of the insertion of N random distinct keys into an initially empty tree.
 * <p>
 * 求出练习 3.3.5 中的结果在 N 个随机不同的键插入到空树中出现的可能性
 *
 * @author LeonChen
 * @since 6/13/21
 */
class E3_3_06 {

    /**
     * @formatter:off
     *
     * N=7
     *                  NN                              N
     *           NN     N       NN                  N       N
     *            4/7                            N     N  N    N
     *                                                  3/7
     * --------------------------------------------------------------------
     * N=8
     *                  NN                              N
     *          NN      NN      NN                  N       N
     *                                          N     N  N     NN
     *                  1/4*4/7=1/7                3/4*4/7+3/7=6/7
     * --------------------------------------------------------------------
     * N=9
     *                      N
     *                N         N
     *          NN      NN     N  N
     *              1/7+6/9*6/7=45/63
     *
     *
     *            N
     *        N       NN
     *    N     N  N  N   N
     *          3/9*6/7=18/63
     * --------------------------------------------------------------------
     * N=10
     *                      N
     *                N         N
     *          NN      NN    NN  N
     *              4/10*45/63= 18/63
     *
     *                    N
     *              NN         N
     *          N   N  NN    N  N
     *              6/10*45/63+18/63= 45/63
     * --------------------------------------------------------------------
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
