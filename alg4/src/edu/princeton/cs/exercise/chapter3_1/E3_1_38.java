package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.exercise.util.ArrayGenUtil;

import java.awt.*;

/**
 * 3.1.38 Amortized cost plots. Develop instrumentation for FrequencyCounter,
 * SequentialSearchST, and BinarySearchST so that you can produce plots like the
 * ones in this section showing the cost of each put() operation during the computation.
 * <p>
 * 摊销成本图.开发一个仪表给 FrequencyCounter测试SequentialSearchST, 和 BinarySearchST使得你可以产生一个
 * 如同本章中展示计算中 put() 操作成本图表
 *
 * @author LeonChen
 * @since 3/6/21
 */
class E3_1_38 {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        StdDraw.setCanvasSize(1000, 800);
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(0, 20000);

        int len = 10000;
        int trial = 3;
        int[] totalSeq = new int[len];
        int[] totalBin = new int[len];
        for (int i = 0; i < trial; i++) {
            int[] arr = ArrayGenUtil.genRandIntArr(len, 0, 5000);
            int[] costSeq = countSequential(arr);
            int[] costBin = countBinary(arr);
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(Color.RED);
            for (int j = 0; j < len; j++) {
                StdDraw.point(j, costSeq[j]);
                totalSeq[j] = totalSeq[j] + costSeq[j];
            }

            StdDraw.setPenRadius(0.006);
            StdDraw.setPenColor(Color.BLUE);
            for (int j = 0; j < len; j++) {
                StdDraw.point(j, costBin[j]);
                totalBin[j] = totalBin[j] + costBin[j];
            }

        }
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.YELLOW);
        float[] avgSeq = new float[len];
        for (int i = 0; i < len; i++) {
            avgSeq[i] = (float) totalSeq[i] / trial;
            StdDraw.point(i, avgSeq[i]);
        }

        StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(Color.GREEN);
        float[] avgBin = new float[len];
        for (int i = 0; i < len; i++) {
            avgBin[i] = (float) totalSeq[i] / trial;
            StdDraw.point(i, avgBin[i]);
        }
        StdOut.println("=============== Seq compares =================");
        for (int i = 0; i < avgSeq.length; i++) {
            StdOut.print(avgSeq[i] + " , ");
        }
        StdOut.println("=============== Bin compares =================");
        for (int i = 0; i < avgBin.length; i++) {
            StdOut.print(avgBin[i] + " , ");
        }

    }


    public static int[] countSequential(int[] a) {
        int distinct = 0, words = 0;
        int[] freqOps = new int[a.length];
        SequentialSearchST<Integer, Integer> st = new SequentialSearchST<Integer, Integer>();

        for (Integer key : a) {
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
            freqOps[words - 1] = st.compares;
            st.compares = 0;
        }

        // find a key with the highest frequency count
        Integer max = Integer.MIN_VALUE;
        st.put(max, 0);
        for (Integer word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        return freqOps;
    }

    public static int[] countBinary(int[] a) {
        int distinct = 0, words = 0;
        BinarySearchST<Integer, Integer> st = new BinarySearchST<Integer, Integer>();
        int[] freqOps = new int[a.length];
        for (Integer key : a) {
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
            freqOps[words - 1] = st.compares;
            st.compares = 0;
        }

        // find a key with the highest frequency count
        Integer max = Integer.MIN_VALUE;
        st.put(max, 0);
        for (Integer word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        return freqOps;
    }

}
