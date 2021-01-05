package edu.princeton.cs.exercise.chapter2_5;

/**
 * 2.5.6 Implement a recursive version of select().
 *
 * 实现一个递归版本的 select() 方法
 *
 * @author LeonChen
 * @since 12/17/20
 */
class E02_05_06 {


    /**
     * 书中原来的非递归方式 select() 如下
     * <code>
     *    public static Comparable select(Comparable[] a, int k) {
     *         StdRandom.shuffle(a);
     *         int lo = 0, hi = a.length - 1;
     *         while (hi > lo) {
     *             int j = partition(a, lo, hi);
     *             if
     *             (j == k) {
     *                 return a[k];
     *             } else if (j > k) {
     *                 hi = j - 1;
     *             } else if (j < k) lo = j + 1;
     *         }
     *         return a[k];
     *     }
     * </code>
     *
     * 也就是找出第k小的元素.
     */
    public static void main(String[] args) {

    }

    //select(a, k, 0, args.length);
    public static Comparable select(Comparable[] a, int k, int lo, int hi) {
        if (hi <= lo) {
            return -1;
        }
        int j = partition(a, lo, hi);
        if (j == k) {
            return a[k];
        } else if (j > k) {
            return select(a, k, lo, j - 1);
        } else {
            return select(a, k, j + 1, hi);
        }
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // TODO: 12/17/20 忽略实现
        return 0;
    }
}
