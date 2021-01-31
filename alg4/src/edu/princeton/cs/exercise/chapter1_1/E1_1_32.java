package edu.princeton.cs.exercise.chapter1_1;


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

/**
 * 1.1.32 Histogram. Suppose that the standard input stream is a sequence of double values. Write
 * a program that takes an integer N and two double values l and r from the command line and uses
 * StdDraw to plot a histogram of the count of the numbers in the standard input stream that fall
 * in each of the N intervals defined by dividing (l , r) into N equal-sized intervals.
 *
 * <p>写一个程序接收一个int N 一个 double l 一个 double r ,N是直方图中条的个数,l 和 r 是直方图的左右边界,
 * 每条都是平分这个边界的.接收一定数量的输入,画出这些值落在每条直方的数量的直方图.
 */
class E1_1_32 {


    private static void test1_1_32(int N, double l, double r) {

        double totalWidth = r - l;
        double perWidth = totalWidth / N;
        // 所有左侧点的位置,最后一个特殊处理,需要一个虚拟的N+1左侧点,实际是N 的右侧点
        double[] lows = new double[N + 1];
        int[] ranges = new int[N];
        // 由于第一个不需要加上每个宽度,所以需要从 -perWidth开始.
        double curLeft = -perWidth;
        // 计算左侧点
        for (int i = 0; i < lows.length; i++) {
            lows[i] = curLeft + perWidth;
            curLeft = lows[i];
        }
        int inputNum = 0;
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            // 以 # 作为结束输入的符号
            if (input.equals("#")) {
                break;
            }
            inputNum++;
            double num = Double.parseDouble(input);
            // 计算分布情况
            for (int i = 0; i < lows.length; i++) {
                if (num > lows[i] && num < lows[i + 1]) {
                    ranges[i]++;
                    break;
                }
            }
        }

        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, inputNum);
        for (int i = 0; i < N; i++) {
            StdDraw.filledRectangle(lows[i] + perWidth / 2, ranges[i] / 2, perWidth / 2, ranges[i] / 2);
        }
    }


    public static void main(String[] args) {
        test1_1_32(6, 0.3, 2.4);

    }
}
