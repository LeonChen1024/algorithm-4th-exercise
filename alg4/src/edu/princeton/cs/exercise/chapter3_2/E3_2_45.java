package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.FileUtil;

import java.awt.*;

/**
 * 3.2.45 Actual timing s. Instrument FrequencyCounter to use Stopwatch and StdDraw
 * to make a plot where the x axis is the number of calls on get() or put() and the y axis
 * is the total running time, with a point plotted of the cumulative time after each call.
 * Run your program for Tale of Two Cities using SequentialSearchST and again using
 * BinarySearchST and again using BST and discuss the results. Note : Sharp jumps in
 * the curve may be explained by caching, which is beyond the scope of this question (see
 * Exercise 3.1.39).
 * <p>
 * 实际用时 s. 通过 FrequencyCounter 使用 Stopwatch 和 StdDraw 来绘制一个图表其中 x 轴是调用 get()
 * 或者 put()的次数而 y 轴是总运行时间,每个点代表每次调用后的累计时间.运行你的程序输入双城表对比
 * SequentialSearchST 和 BST 并且讨论结果.注意:曲线的突变可以用缓存来解释,这个问题超出这题的范围(见练习
 * 3.1.39)
 *
 * @author LeonChen
 * @since 5/13/21
 */
class E3_2_45 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000, 800);
        StdDraw.setXscale(0, 140000);
        StdDraw.setYscale(0, 40);

        String[] strs = FileUtil.getAllStrFromFile("src/edu/princeton/cs/algs4-data/tale" +
                ".txt");

        double[] times = countSequential(strs);
//        double[] times = countBST(strs);
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i < times.length; i++) {
            StdDraw.point(i, times[i]);
        }
    }

    public static double[] countSequential(String[] a) {
        int distinct = 0, words = 0;
        double[] timeOpts = new double[a.length];
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        double totalTime = 0;
        for (String key : a) {
            Stopwatch stopwatch = new Stopwatch();
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
            totalTime += stopwatch.elapsedTime();
            timeOpts[words - 1] = totalTime;
        }

        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("last " + timeOpts[timeOpts.length - 1]);
        return timeOpts;
    }


    public static double[] countBST(String[] a) {
        int distinct = 0, words = 0;
        double[] timeOpts = new double[a.length];
        BST<String, Integer> st = new BST<>();

        double totalTime = 0;
        for (String key : a) {
            Stopwatch stopwatch = new Stopwatch();
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
            totalTime += stopwatch.elapsedTime();
            timeOpts[words - 1] = totalTime;
        }

        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("last " + timeOpts[timeOpts.length - 1]);
        return timeOpts;
    }
}
