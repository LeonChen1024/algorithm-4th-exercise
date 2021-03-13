package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.exercise.util.ArrayGenUtil;

/**
 * 3.1.31 Performance driver. Write a performance driver program that uses put() to
 * fill a symbol table, then uses get() such that each key in the table is hit an average of
 * ten times and there is about the same number of misses, doing so multiple times on
 * random sequences of string keys of various lengths ranging from 2 to 50 characters;
 * measures the time taken for each run; and prints out or plots the average running times.
 * <p>
 * 性能驱动.编写一个性能驱动程序使用 put() 来填充一个符号表,然后使用 get() 使得每个表中的 key 平均都命中 10 次
 * 并且有大致相同次数的未命中次数,在一个随机长度为 2 到 50 的字符串key 序列中进行多次该操作;计算每次的运行时间;
 * 并且打印出平均运行时间或者绘制出图表
 *
 * @author LeonChen
 * @since 2/26/20
 */
class E3_1_31 {

    private static final int TRIAL = 10;
    private static final int ARR_LEN = 10000;
    private static final int MIN_STR_LEN = 2;
    private static final int MAX_STR_LEN = 50;
    private static final String MISS_KEY = "50";
    private static final int HIT_AND_MISS = 10;


    /**
     * @param args
     */
    public static void main(String[] args) {
        double totalTime = 0;
        for (int i = 0; i < TRIAL; i++) {
            String[] rStrArr = ArrayGenUtil.genRandomStrArr(ARR_LEN, MIN_STR_LEN, MAX_STR_LEN);
            BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<String, Integer>();

            Stopwatch stopwatch = new Stopwatch();
            for (String s : rStrArr) {
                int v = StdRandom.uniform(2);
                binarySearchST.put(s, v);
            }
            for (String key : binarySearchST.keys()) {
                for (int j = 0; j < HIT_AND_MISS; j++) {
                    binarySearchST.get(key);
                }
                for (int j = 0; j < HIT_AND_MISS; j++) {
                    binarySearchST.get(MISS_KEY);
                }
            }
            double time = stopwatch.elapsedTime();
            StdOut.println("TRIAL = " + i + " , use time = " + time);
            totalTime += time;
        }
        StdOut.println("average use time = " + totalTime / TRIAL);

    }


}
