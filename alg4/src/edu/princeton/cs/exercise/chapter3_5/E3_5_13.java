package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.5.13 Modify LookupCSV to make a program RangeLookupCSV that takes two key values from
 * the standard input and prints all key-value pairs in the .csv file such that the
 * key falls within the range specified.
 * <p>
 * 修改 LookupCSV 来制作一个程序 RangeLookupCSV 从输入接收两个 key values并且打印出 .csv 文件中所有的
 * 指定范围内键值对
 *
 * @author LeonChen
 * @since 9/8/21
 */
class E3_5_13 {

    /**
     * @formatter:off
     * 
     * @formatter:on
     */
    public static void main(String[] args) {
        lookupCSV(args);
    }

    // Parameters example: 0: csv_file.txt
    //                     1: 0
    //                     2: 1

    // Queries: rene
    //         sedgewick
    //         wayne

    // Output expected:
    // rene
    // 1 3 5
    // sedgewick
    // 9
    // wayne
    // 9 10
    private static void lookupCSV(String[] args) {
        String[] keys = new String[]{"3", "4", "6", "2", "3", "4", "3"};
        String[] vals = new String[]{"2", "2", "1", "1", "5", "4", "6"};

        RedBlackBST<String, List<String>> symbolTable = new RedBlackBST<>();

        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            String value = vals[i];
            if (!symbolTable.contains(key)) {
                symbolTable.put(key, new ArrayList<>());
            }
            symbolTable.get(key).add(value);
        }
        find(symbolTable, "3", "5");
        StdOut.println("expect: 2 5 6 2 4");
    }

    private static void find(RedBlackBST<String, List<String>> symbolTable, String lo,
                             String hi) {

        for (String key : symbolTable.keys(lo, hi)) {

            StdOut.print(symbolTable.get(key));
        }
        StdOut.println();
    }
}
