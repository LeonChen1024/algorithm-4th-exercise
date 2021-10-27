package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.5.12 Modify LookupCSV to associate with each key all values that appear in a key-value
 * pair with that key in the input (not just the most recent, as in the associative-array
 * abstraction).
 * <p>
 * 修改 LookupCSV 来出现在键值对中每个键的所有值(不仅仅是最经常的 key,就像关联数组抽象一样)
 *
 * @author LeonChen
 * @since 9/8/21
 */
class E3_5_12 {

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
        find(symbolTable, "3");
        StdOut.println("expect: 2 5 6");
    }

    private static void find(RedBlackBST<String, List<String>> symbolTable, String key) {
        boolean isFirstValue = true;

        if (symbolTable.contains(key)) {
            for (String value : symbolTable.get(key)) {
                if (isFirstValue) {
                    isFirstValue = false;
                } else {
                    StdOut.print(" ");
                }

                StdOut.print(value);
            }
            StdOut.println();
        }
    }
}
