package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.exercise.util.FileUtil;

/**
 * 3.1.35 Performance validation I. Run doubling tests that use the first N words of Tale
 * of Two Cities for various values of N to test the hypothesis that the running time of
 * FrequencyCounter is quadratic when it uses SequentialSearchST for its symbol table.
 * <p>
 * 性能验证 I. 运行倍率测试使用两城表的前N个单词来测试使用SequentialSearchST 作为符号表时 FrequencyCounter
 * 的运行时间会是平方级的假说
 *
 * @author LeonChen
 * @since 3/2/20
 */
class E3_1_35 {

    /**
     * N = 2000 , use time = 0.01 , ratio =
     * the 341
     * distinct = 1243
     * words    = 4000
     * N = 4000 , use time = 0.032 , ratio = 3.2
     * the 573
     * distinct = 1998
     * words    = 8000
     * N = 8000 , use time = 0.121 , ratio = 3.78125
     * the 1015
     * distinct = 3099
     * words    = 16000
     * N = 16000 , use time = 0.428 , ratio = 3.5371900826446283
     * the 1966
     * distinct = 4882
     * words    = 32000
     * N = 32000 , use time = 1.581 , ratio = 3.6939252336448596
     * the 3830
     * distinct = 7338
     * words    = 64000
     * N = 64000 , use time = 4.996 , ratio = 3.1600253004427583
     * the 7572
     * distinct = 10393
     * words    = 128000
     * N = 128000 , use time = 18.112 , ratio = 3.625300240192153
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        int N = 2000;
        double prevTime = -1;
        String[] allStr = FileUtil.getAllStrFromFile("src/edu/princeton/cs/algs4-data/tale" +
                ".txt");
        if (allStr == null) {
            throw new Exception("there is not words ");
        }
        for (; N < allStr.length; N = N * 2) {
            String[] arr = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = allStr[i];
            }
            Stopwatch stopwatch = new Stopwatch();

            count(arr);
            double curTime = stopwatch.elapsedTime();
            double ratio = -1;
            if (prevTime != -1) {
                ratio = curTime / prevTime;
            }
            StdOut.println("N = " + N + " , use time = " + curTime + " , ratio = " + (ratio == -1 ? "" : ratio));
            prevTime = curTime;


        }

    }

    public static void count(String[] a) {
        int distinct = 0, words = 0;
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();

        for (String key : a) {
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }

}
