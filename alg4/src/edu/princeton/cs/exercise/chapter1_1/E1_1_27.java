package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;


/**
 * 1.1.27 Binomial distribution. Estimate the number of recursive calls that would be used by the
 * code <code>
 * public static double binomial(int N, int k, double p) {
 * if ((N == 0) || (k < 0)) return 1.0;
 * return (1.0 - p) * binomial(N - 1, k) + p * binomial(N - 1, k - 1);
 * }
 * </code> to compute binomial(100, 50). Develop a better implementation that is based on saving
 * computed values in an array.
 * <p>
 * 二项式分配.预估这个代码计算  binomial(100, 50) 递归的次数.开发一个更好的实现可以减少计算数组中的值的次数
 */
class E1_1_27 {


    static int recursiveTime = 0;

    /**
     * 二项分布
     *
     * <p>其中当N次中正好发生k次的时候,概率为 C_n^k * p^k * (1-p)^(n-k)
     *
     * <p>由于每次抽取的结果并不会影响后面的抽取,所以这些事件是独立事件. P(A*B) = P(A) * P(B)
     *
     * <p>C_n^k 为组合,代表从n个中抽出k个,对顺序不敏感.
     *
     * @param N 实验次数
     * @param k 中标次数
     * @param p 中标概率
     * @return
     */
    public static double binomial(int N, int k, double p) {
        recursiveTime++;
        if ((N == 0) || (k < 0)) return 1.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

    /**
     * 空间换时间
     *
     * @param N
     * @param k
     * @param p
     * @return
     */
    public static double binomial1(int N, int k, double p, double[][] s) {
        recursiveTime++;
        if ((N == 0) || (k < 0)) return 1.0;
        if (s[N][k] == 0.0) {
            s[N][k] = (1.0 - p) * binomial1(N - 1, k, p, s) + p * binomial1(N - 1, k - 1, p, s);
        }
        return s[N][k];
    }

    /**
     *
     */
    public static void main(String[] args) {
        //    binomial(100, 50, 0.5);
        double result = binomial1(100, 50, 0.5, new double[100 + 1][50 + 1]);
        StdOut.println("calls = " + recursiveTime + " , result = " + result);
    }
}
