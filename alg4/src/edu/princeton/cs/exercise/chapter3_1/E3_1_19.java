package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.19 Modify FrequencyCounter to print all of the values having the highest frequency
 * of occurrence, not just one of them. Hint : Use a Queue.
 * <p>
 * 修改 FrequencyCounter 来打印出所以出现频率最高的值,不仅仅是其中一个.提示:使用 Queue
 *
 * @author LeonChen
 * @since 2/12/21
 */
class E3_1_19 {


    /**
     * @param args
     */

    public static void main(String[] args) {
        String[] a = new String[]{"e", "gs", "rib", "grg", "rib", "e", "gs", "rib", "e", "gs", "gs", "rib"};
        int distinct = 0, words = 0;
        ST<String, Integer> st = new ST<String, Integer>();

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
        Queue<String> maxQ = new Queue();
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
                maxQ = new Queue<>();
                maxQ.enqueue(word);
            } else if (st.get(word) == st.get(max)) {
                maxQ.enqueue(word);
            }
        }

        StdOut.print("max are : ");
        for (String s : maxQ) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}
