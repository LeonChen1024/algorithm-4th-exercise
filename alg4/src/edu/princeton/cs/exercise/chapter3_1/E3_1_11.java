package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.11 Give a trace of the process of inserting the keys E A S Y Q U E S T I O N into
 * an initially empty table using BinarySearchST. How many compares are involved?
 * <p>
 * 给出使用 BinarySearchST 插入键 E A S Y Q U E S T I O N 到一个空表中的追踪过程.总共需要多少次
 * 对比?
 *
 * @author LeonChen
 * @since 2/7/21
 */
class E3_1_11 {

    /**
     * 如果同时进行追踪和计数,追踪会影响到计数,因为修改了 rank()方法对比较次数做了记录,而 keys()方法也会用到
     * rank()方法.所以这两个操作分开执行,由于二叉树的性质使得比较次数减少
     * <p>
     * 0
     * 1
     * 3
     * 5
     * 7
     * 10
     * 13
     * 16
     * 19
     * 22
     * 25
     * 29
     * <p>
     * 总共需要 29 次
     *
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST();
        st.put("E", 0);
        StdOut.println(st.compares);
//        println(st);
        st.put("A", 1);
        StdOut.println(st.compares);
//        println(st);
        st.put("S", 2);
        StdOut.println(st.compares);
//        println(st);
        st.put("Y", 3);
        StdOut.println(st.compares);
//        println(st);
        st.put("Q", 4);
        StdOut.println(st.compares);
//        println(st);
        st.put("U", 5);
        StdOut.println(st.compares);
//        println(st);
        st.put("E", 6);
        StdOut.println(st.compares);
//        println(st);
        st.put("S", 7);
        StdOut.println(st.compares);
//        println(st);
        st.put("T", 8);
        StdOut.println(st.compares);
//        println(st);
        st.put("I", 9);
        StdOut.println(st.compares);
//        println(st);
        st.put("O", 10);
        StdOut.println(st.compares);
//        println(st);
        st.put("N", 11);
        StdOut.println(st.compares);
//        println(st);
    }

    private static void println(BinarySearchST<String, Integer> st) {
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("=======================");
    }


}
