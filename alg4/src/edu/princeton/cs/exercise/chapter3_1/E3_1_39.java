package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.FileUtil;

import java.awt.*;

/**
 * 3.1.39 Actual timing s. Instrument FrequencyCounter to use Stopwatch and StdDraw
 * to make a plot where the x-axis is the number of calls on get() or put() and the y-axis
 * is the total running time, with a point plotted of the cumulative time after each call.
 * Run your program for Tale of Two Cities using SequentialSearchST and again using
 * BinarySearchST and discuss the results. Note : Sharp jumps in the curve may be explained
 * by caching, which is beyond the scope of this question.
 * <p>
 * 实际耗时 s.测试 FrequencyCounter 使用 Stopwatch 和 StdDraw 来绘制一个图,x 轴代表调用 get() 或者
 * put() 的次数,y 轴代表总的运行时间,绘制出每次调用后累积时间.使用两城表输入SequentialSearchST和
 * BinarySearchST运行它并且讨论结果.注意:曲线的极速跳动可以通过缓存解释,这超出了这个问题的范围
 *
 * @author LeonChen
 * @since 3/9/21
 */
class E3_1_39 {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        StdDraw.setCanvasSize(1000, 800);
        StdDraw.setXscale(0, 140000);
        StdDraw.setYscale(0, 40);

        String[] strs = FileUtil.getAllStrFromFile("src/edu/princeton/cs/algs4-data/tale" +
                ".txt");

        double[] times = countSequential(strs);
//        double[] times = countBinary(strs);
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


    public static double[] countBinary(String[] a) {
        int distinct = 0, words = 0;
        double[] timeOpts = new double[a.length];
        BinarySearchST<String, Integer> st = new BinarySearchST<>();

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
