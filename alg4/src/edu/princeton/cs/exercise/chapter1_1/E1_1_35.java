package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 * 1.1.35 Dice simulation. The following code computes the exact probability distribution for the
 * sum of two dice: <code>
 * int SIDES = 6;
 * double[] dist = new double[2 * SIDES + 1];
 * for (int i = 1; i <= SIDES; i++) for (int j = 1; j <= SIDES; j++) dist[i + j] += 1.0;
 * for (int k = 2; k <= 2 * SIDES; k++) dist[k] /= 36.0;
 * </code> The value dist[i] is the probability that the dice sum to k. Run experiments to
 * validate this calculation simulating N dice throws, keeping track of the frequencies of
 * occurrence of each value when you compute the sum of two random integers between 1 and 6. How
 * large does N have to be before your empirical results match the exact results to three decimal
 * places?
 *
 * <p>色子模拟.以下代码可以模拟两个色子值和的分布. dist[i] 是两个色子和为k的概率.运行这个程序来证实这个试验模拟N次投掷,
 * 当你在计算两个随机整数和的时候追踪每个值出现的频率.N要多大才能保证经验结果和准确结果保持三位小数的相同.
 */
class E1_1_35 {


    /**
     * 对比两者概率是否在3位精度要求内
     *
     * @param dist
     * @param rhitNum
     * @param n
     * @return
     */
    private static boolean compareDist(double[] dist, double[] rhitNum, int n) {

        for (int i = 0; i < 13; i++) {
            if (Math.abs((rhitNum[i] / n) - dist[i]) >= 0.001) {
                //        StdOut.println();
                return false;
            }
        }

        return true;
    }

    /**
     * 随机投掷两次色子和次数 .
     *
     * <p>注意随机时候应该用随机到的数字+1,而不是把随机的上限+1
     */
    private static double[] randomHitNum(double[] rhitNum) {
        rhitNum[(StdRandom.uniform(6) + 1) + (StdRandom.uniform(6) + 1)] += 1.0;
        return rhitNum;
    }

    /**
     * 投掷两次色子和的标准概率.
     */
    private static double[] standardProbability() {

        int SIDES = 6; // 一个色子总共有6种可能
        double[] dist = new double[2 * SIDES + 1]; // 最大的可能是12,所以需要13个位置.
        for (int i = 1; i <= SIDES; i++)
            for (int j = 1; j <= SIDES; j++) dist[i + j] += 1.0; // 计算每种和出现的次数
        for (int k = 2; k <= 2 * SIDES; k++) {
            dist[k] /= 36.0; // 计算在所有可能中的概率
            StdOut.println(k + " probability= " + dist[k]);
        }

        return dist;
    }

    public static void main(String[] args) {
        double[] dist = standardProbability();

        // 模拟随机投掷
        double[] rhitNum = new double[13];
        int n = 1;

        while (!compareDist(dist, rhitNum, n) && n++ > 0) {

            rhitNum = randomHitNum(rhitNum);
        }

        for (int i = 0; i < 13; i++) {
            StdOut.println("random " + i + " " + rhitNum[i] / n);
        }
        StdOut.println(n);
    }
}
