package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.5 The figure at right shows all the structurally different 2-3 trees with N
 * keys, for N from 1 up to 6 (ignore the order of the subtrees). Draw all the
 * structurally different trees for N = 7, 8, 9, and 10.
 * <p>
 * 右边的图展示了所有 N 个键结构不同的 2-3 树,N 从 1 到 6(忽略子树的顺序).绘制出所有N= 7,8,9 和 10 中
 * 结构不同的树
 * <p>
 * @formatter:off
 *
 *                 N
 * -------------------------------------
 *                  NN
 * -------------------------------------
 *                  N
 *                N     N
 * -------------------------------------
 *                  N
 *               N      NN
 * -------------------------------------
 *                  N                   NN
 *              NN      NN          N   N   N
 * -------------------------------------
 *                  NN
 *              N   N   NN
 * -------------------------------------
 *
 * @formatter:on
 * @author LeonChen
 * @since 6/12/21
 */
class E3_3_05 {

    /**
     * @formatter:off
     * 因为不需要处理子树的顺序问题,所以变种一直不多
     *
     * N=7
     *                  NN                              N
     *           NN     N       NN                  N       N
     *                                           N     N  N    N
     *
     * --------------------------------------------------------------------
     * N=8
     *                  NN                              N
     *          NN      NN      NN                  N       N
     *                                          N     N  N     NN
     *
     * --------------------------------------------------------------------
     * N=9
     *                      N
     *                N         N
     *          NN      NN     N  N
     *
     *
     *
     *            N
     *        N       NN
     *    N     N  N  N   N
     *
     * --------------------------------------------------------------------
     * N=10
     *                      N
     *                N         N
     *          NN      NN    NN  N
     *
     *                    N
     *              NN         N
     *          N   N  NN    N  N
     *
     * --------------------------------------------------------------------
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
