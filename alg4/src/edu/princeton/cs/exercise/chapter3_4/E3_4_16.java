package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.16 Suppose that a linear-probing table of size 10^6 is half full, with occupied
 * positions chosen at random. Estimate the probability that all positions with indices
 * divisible by 100 are occupied.
 * <p>
 * 假设一个大小为 10^6 的线性探查表一半是满的,填充的位置也是随机选择的.估算所有能被 100 整除的索引被占用
 * 的概率是多少
 *
 * @author LeonChen
 * @since 8/3/21
 */
class E3_4_16 {

    /**
     * @formatter:off
     * 待解
     * The probability can be computed using hypergeometric distribution. The distribution describes the probability of k successes in n draws, without replacement, from a finite population of size N that contains exactly K successes, wherein each draw is either a success or a failure. The probability is equal to
     *
     * P(k successes) = (K) (N - K)
     *                  (k) (n - k)
     *                  ___________
     *                     (N)
     *                     (n)
     *
     * The k successes in this case are all the indices divisible by 100 being occupied. In a hash table of size 10^6 there are 10^4 indices divisible by 100, so k = 10^4.
     * The total successes K is also 10^4, which are all possible indices divisible by 100.
     * The population size N is the hash table size, 10^6.
     * The n draws are the put() operations that were made on the hash table, 10^6 / 2 = 500.000.
     * The probability is then:
     *
     * P(all indices divisible by 100 occupied) = (10000) (10^6  -  10000)
     *                                            (10000) (500000 - 10000)
     *                                            ________________________
     *                                                   ( 10^6 )
     *                                                   (500000)
     *
     * Hypergeometric distribution: https://en.wikipedia.org/wiki/Hypergeometric_distribution
     * Discussion on Stack Exchange:
     * https://cs.stackexchange.com/questions/78174/probability-that-all-indices-divisible-by-100-in-a-hash-table-with-linear-probin
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
