package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.29 Test client. Write a test client TestBinarySearch.java for use in testing the
 * implementations of min(), max(), floor(), ceiling(), select(), rank(), deleteMin(),
 * deleteMax(), and keys() that are given in the text. Start with the standard indexing
 * client given on page 370. Add code to take additional command-line arguments, as
 * appropriate.
 * <p>
 * 测试端.编写一个测试端 TestBinarySearch.java 用来测试正文中的 min(), max(), floor(), ceiling(), select(),
 * rank(), deleteMin(), deleteMax(), 和 keys() 的实现.
 *
 * @author LeonChen
 * @since 2/24/21
 */
class E3_1_29 {


    /**
     * 官方解如下,但是里面有几个错误,做了下修改
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public static class TestBinarySearchST {

        /***************************************************************************
         *  Unit test client.
         ***************************************************************************/
        public static void main(String[] args) {
            String test = "S E A R C H E X A M P L E";
            String[] keys = test.split("\\s+");
            int n = keys.length;

            BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
            for (int i = 0; i < n; i++)
                st.put(keys[i], i);

            StdOut.println("size = " + st.size());
            StdOut.println("min  = " + st.min());
            StdOut.println("max  = " + st.max());
            StdOut.println();


            // print keys in order using keys()
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
            StdOut.println("key rank floor ceil");
            StdOut.println("-------------------");
            for (char i = 'A'; i <= 'Z'; i++) {
                String s = i + "";
                StdOut.printf("%2s %4d %4s %4s\n", s, st.rank(s), st.floor(s), st.ceiling(s));
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
            if (!st.isEmpty()) {
                for (String s : st.keys())
                    StdOut.println(s + " " + st.get(s));
                StdOut.println();
            }

            StdOut.println("After adding back N keys");
            StdOut.println("--------------------------------");
            for (int i = 0; i < n; i++)
                st.put(keys[i], i);
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
            StdOut.println();

        }
    }

}
