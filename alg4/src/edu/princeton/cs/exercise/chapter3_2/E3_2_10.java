package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.2.10 Write a test client TestBST.java for use in testing the implementations of
 * min(), max(), floor(), ceiling(), select(), rank(), delete(), deleteMin(),
 * deleteMax(), and keys() that are given in the text. Start with the standard indexing
 * client given on page 370. Add code to take additional command-line arguments, as
 * appropriate.
 * <p>
 * 编写一个测试客户端 TestBST.java 用来测试正文中 min(), max(), floor(), ceiling(), select(),
 * rank(), delete(), deleteMin(), deleteMax(), 和 keys() 实现.从370 页标准索引客户端开始.
 * 添加代码来接受合适的额外命令行参数
 *
 * @author LeonChen
 * @since 4/7/21
 */
class E3_2_10 {

    /**
     * @formatter:off
     *
     * 官方实现如下
     * @formatter:on
     */
    public static void main(String[] args) {
    }

    /******************************************************************************
     *  Compilation:  javac TestBST.java
     *  Execution:    java TestBST
     *  Dependencies: BST.java
     *
     *  A test client for BST.java.
     *
     *  % java -ea TestBST
     *
     ******************************************************************************/

    public static class TestBST {
        public static void main(String[] args) {
            String test = "S E A R C H E X A M P L E";
            String[] keys = test.split(" ");
            int n = keys.length;
            BST<String, Integer> st = new BST<String, Integer>();
            for (int i = 0; i < n; i++)
                st.put(keys[i], i);

            StdOut.println("size = " + st.size());
            StdOut.println("min  = " + st.min());
            StdOut.println("max  = " + st.max());
            StdOut.println();


            // print keys in order using allKeys()
            StdOut.println("Testing keys()");
            StdOut.println("--------------------------------");
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
            StdOut.println();

            // print keys in order using select
            StdOut.println("Testing select");
            StdOut.println("--------------------------------");
            for (int i = 0; i < st.size(); i++)
                StdOut.println(i + " " + st.select(i));
            StdOut.println();

            // test rank, floor, ceiling
            StdOut.println("key rank floor flor2 ceil");
            StdOut.println("-------------------------");
            for (char i = 'A'; i <= 'X'; i++) {
                String s = i + "";
                StdOut.printf("%2s %4d %4s %4s %4s\n", s, st.rank(s), st.floor(s), st.floor2(s), st.ceiling(s));
            }
            StdOut.println();

            // test range search and range count
            String[] from = {"A", "Z", "X", "0", "B", "C"};
            String[] to = {"Z", "A", "X", "Z", "G", "L"};
            StdOut.println("range search");
            StdOut.println("-------------------");
            for (int i = 0; i < from.length; i++) {
                StdOut.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
                for (String s : st.keys(from[i], to[i]))
                    StdOut.print(s + " ");
                StdOut.println();
            }
            StdOut.println();

            // delete the smallest keys
            for (int i = 0; i < st.size() / 2; i++) {
                st.deleteMin();
            }
            StdOut.println("After deleting the smallest " + st.size() / 2 + " keys");
            StdOut.println("--------------------------------");
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
            StdOut.println();

            // delete all the remaining keys
            while (!st.isEmpty()) {
                st.delete(st.select(st.size() / 2));
            }
            StdOut.println("After deleting the remaining keys");
            StdOut.println("--------------------------------");
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
            StdOut.println();

            StdOut.println("After adding back the keys");
            StdOut.println("--------------------------------");
            for (int i = 0; i < n; i++)
                st.put(keys[i], i);
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
            StdOut.println();

        }
    }

}
