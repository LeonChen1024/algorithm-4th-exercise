package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.2.39 Average case . Run empirical studies to estimate the average and standard
 * deviation of the number of compares used for search hits and for search misses in a BST
 * built by running 100 trials of the experiment of inserting N random keys into an
 * initially empty tree, for N = 10^4, 10^5, and 10^6. Compare your results against the
 * formula for the average given in Exercise 3.2.35.
 * <p>
 * 平均情况.运行试验学习并估算在一个运行 100 次插入 N 个随机键到空树的实验中搜索命中和搜索失败的对比次数的
 * 平均和标准偏差是多少,其中 N= 10^4, 10^5, 和 10^6.对比你得出的平均结果和练习 3.2.35 中公式的值
 *
 * @author LeonChen
 * @since 4/31/21
 */
class E3_2_39 {

    /**
     * @formatter:off
     * 为了保证找不到的情况是分布在各个位置的,所以在树中添加奇数,要找不到的时候使用偶数查找,避免使用最大值重复
     * 的往右子树不停递归等情况.
     * @formatter:on
     */
    public static void main(String[] args) {
        int[] ns = new int[]{(int) Math.pow(10, 4), (int) Math.pow(10, 5),
                (int) Math.pow(10, 6)};
        for (int n : ns) {
            BSTCompare<Integer, Integer> bstCompare = new BSTCompare<>();
            int k = 1;
//            int hitTotalCompares = 0;
            int[] arr = new int[n];
            for (int i = 1; i <= n; i++) {
                arr[i - 1] = k;
                k += 2;

            }
            StdRandom.shuffle(arr);
            for (int i : arr) {
                bstCompare.put(i, 1);
            }
            // 命中测试
            int[] hitCompArr = new int[n];
            int hitTotalCompares = 0;
            for (int i = 0; i < n; i++) {
                int uniform = StdRandom.uniform(n) * 2 + 1;
                bstCompare.get(uniform);
                hitTotalCompares += bstCompare.compareNum;
                hitCompArr[i] = bstCompare.compareNum;
            }
            float hitAvgCompares = hitTotalCompares / (float) n;
            // 丢失测试
            int[] missCompArr = new int[n];
            int missTotalCompares = 0;
            for (int i = 0; i < n; i++) {
                int uniform = StdRandom.uniform(n) * 2 + 1;
                bstCompare.get(uniform);
                missTotalCompares += bstCompare.compareNum;
                missCompArr[i] = bstCompare.compareNum;
            }
            float missAvgCompares = missTotalCompares / (float) n;

            long totalSquareHit = 0;
            long totalSquareMiss = 0;

            //Standard deviation for search hits
            for (int j = 0; j < n; j++) {
                long numberOfCompares = hitCompArr[j];

                long squareDeviation =
                        (long) Math.pow((numberOfCompares - hitAvgCompares), 2);
                totalSquareHit += squareDeviation;
            }
            double hitStandardDeviation = Math.sqrt(totalSquareHit / n);

            //Standard deviation for search misses
            for (int j = 0; j < n; j++) {
                long numberOfCompares = missCompArr[j];

                long squareDeviation =
                        (long) Math.pow((numberOfCompares - missAvgCompares), 2);
                totalSquareMiss += squareDeviation;
            }
            double missStandardDeviation = Math.sqrt(totalSquareMiss / n);

            StdOut.println("when n = " + n + " , hitAvgCompares = " + hitAvgCompares + " , " +
                    "missAvgCompares = " + missAvgCompares
                    + "formula result = " + (1.39 * (Math.log(n) / Math.log(2)) - 1.85)
                    + " , hitStandardDeviation = " + hitStandardDeviation
                    + " , missStandardDeviation = " + missStandardDeviation);
        }
    }

    private static class BSTCompare<Key extends Comparable<Key>, Value> extends
            BST<Key, Value> {
        public int compareNum;

        @Override
        public Value get(Key key) {
            compareNum = 0;
            return get(root, key);
        }

        @Override
        protected Value get(Node x, Key key) {
            if (key == null)
                throw new IllegalArgumentException("calls get() with a null key");
            compareNum++;
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) return get(x.left, key);
            else if (cmp > 0) return get(x.right, key);
            else return x.val;
        }
    }


}
