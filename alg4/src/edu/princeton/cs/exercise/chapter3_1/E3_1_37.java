package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.exercise.util.ArrayGenUtil;
import edu.princeton.cs.exercise.util.FileUtil;

/**
 * 3.1.37 Put/get ratio. Determine empirically the ratio of the amount of time that
 * BinarySearchST spends on put() operations to the time that it spends on get() operations
 * when FrequencyCounter is used to find the frequency of occurrence of values in 1 million
 * random M-bit int values, for M = 10, 20, and 30. Answer the same question for tale.txt and
 * compare the results.
 * <p>
 * put/get 比例.根据经验判断在 FrequencyCounter 中使用 BinarySearchST 查找 1 百万个随机 M bit整数值的出现
 * 频率时花在 put()操作和 get()操作的时间比,其中 M =10, 20, 30. 如果使用 tale.txt 又会是什么结果呢?
 *
 * @author LeonChen
 * @since 3/5/21
 */
class E3_1_37 {

    /**
     * M = 10 的时候,由于重复的概率比较高,所以 put() 操作的时间比率会比较小,随着重复次数的减少,比率逐渐变大
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        int[] bits = new int[]{10, 20, 30};
        for (int bit : bits) {
            int[] arr = ArrayGenUtil.genRandIntArr(1000000, (int) Math.pow(2, bit - 1),
                    (int) Math.pow(2, bit) - 1);
            Stopwatch stopwatch = new Stopwatch();
            count(arr);
            double time = stopwatch.elapsedTime();
            StdOut.println("Bit = " + bit + " , use time = " + time);
        }

        String[] allStr = FileUtil.getAllStrFromFile(
                "src/edu/princeton/cs/algs4-data/tale.txt");
        if (allStr == null) {
            throw new Exception("there is not words ");
        }
        Stopwatch stopwatch = new Stopwatch();
        count(allStr);
        double time = stopwatch.elapsedTime();
        StdOut.println("use tale use time = " + time);
    }

    public static void count(int[] a) {
        int distinct = 0, words = 0;
        BinarySearchST<Integer, Integer> st = new BinarySearchST<Integer, Integer>();
        double getTime = 0;
        double putTime = 0;

        Stopwatch getWatch;
        Stopwatch putWatch;
        for (Integer key : a) {
            getWatch = new Stopwatch();
            boolean contains = st.contains(key);
            getTime += getWatch.elapsedTime();
            if (contains) {
                getWatch = new Stopwatch();
                Integer oldV = st.get(key);
                getTime += getWatch.elapsedTime();
                putWatch = new Stopwatch();
                st.put(key, oldV + 1);
                putTime += putWatch.elapsedTime();
            } else {
                putWatch = new Stopwatch();
                st.put(key, 1);
                putTime += putWatch.elapsedTime();
                distinct++;
            }
        }

        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("gettime = " + getTime + " , puttime = " + putTime +
                " , ratio = " + getTime / putTime);
    }

    public static void count(String[] a) {
        int distinct = 0, words = 0;
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        double getTime = 0;
        double putTime = 0;

        Stopwatch getWatch;
        Stopwatch putWatch;
        for (String key : a) {
            getWatch = new Stopwatch();
            boolean contains = st.contains(key);
            getTime += getWatch.elapsedTime();
            if (contains) {
                getWatch = new Stopwatch();
                Integer oldV = st.get(key);
                getTime += getWatch.elapsedTime();
                putWatch = new Stopwatch();
                st.put(key, oldV + 1);
                putTime += putWatch.elapsedTime();
            } else {
                putWatch = new Stopwatch();
                st.put(key, 1);
                putTime += putWatch.elapsedTime();
                distinct++;
            }
        }

        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("gettime = " + getTime + " , puttime = " + putTime +
                " , ratio = " + getTime / putTime);
    }


}
