package edu.princeton.cs.exercise.chapter2_5;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.4 Implement a method String[] dedup(String[] a) that returns the objects in a[] in sorted
 * order, with duplicates removed.
 *
 * <p>实现一个方法 String[] dedup(String[] a) 将 a[] 中的对象有序返回,并且删除重复项
 *
 * @author LeonChen
 * @since 12/16/20
 */
class E02_05_04 {

    /**
     *
     */
    public static void main(String[] args) {
        String[] input =
                new String[]{
                        "afterthougnt",
                        "test",
                        "thougnt",
                        "common",
                        "after",
                        "abandon",
                        "cool",
                        "test",
                        "java",
                        "coolguy",
                        "C",
                        "python",
                        "guy"
                };
        String[] res = dedup(input);
        for (String re : res) {
            StdOut.println(re);
        }

    }

    private static String[] dedup(String[] a) {

        ArrayList<String> uniqList = new ArrayList();
        Arrays.sort(a);
        uniqList.add(a[0]);
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) != 0) {
                uniqList.add(a[i]);
            }
        }
        String[] res = new String[uniqList.size()];
        uniqList.toArray(res);
        return res;

    }
}
