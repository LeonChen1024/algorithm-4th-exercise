package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.10 Give a trace of the process of inserting the keys E A S Y Q U E S T I O N into an
 * initially empty table using SequentialSearchST. How many compares are involved?
 * <p>
 * 给出一个使用SequentialSearchST 插入 E A S Y Q U E S T I O N 到一个初始为空的表中的追踪过程.会包含
 * 多少次比较
 *
 * @author LeonChen
 * @since 2/6/20
 */
class E3_1_10 {

    /**
     * 每次插入一个值都需要遍历到 key 相同为止,如果没有,则对比了全部
     * <p>
     * 总共需要 55 次
     *
     * @param args
     */
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST();
        st.put("E", 0);
        StdOut.println(st.compares);
        println(st);
        st.put("A", 1);
        StdOut.println(st.compares);
        println(st);
        st.put("S", 2);
        StdOut.println(st.compares);
        println(st);
        st.put("Y", 3);
        StdOut.println(st.compares);
        println(st);
        st.put("Q", 4);
        StdOut.println(st.compares);
        println(st);
        st.put("U", 5);
        StdOut.println(st.compares);
        println(st);
        st.put("E", 6);
        StdOut.println(st.compares);
        println(st);
        st.put("S", 7);
        StdOut.println(st.compares);
        println(st);
        st.put("T", 8);
        StdOut.println(st.compares);
        println(st);
        st.put("I", 9);
        StdOut.println(st.compares);
        println(st);
        st.put("O", 10);
        StdOut.println(st.compares);
        println(st);
        st.put("N", 11);
        StdOut.println(st.compares);
        println(st);
    }

    private static void println(SequentialSearchST<String, Integer> st) {
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("=======================");
    }


}
