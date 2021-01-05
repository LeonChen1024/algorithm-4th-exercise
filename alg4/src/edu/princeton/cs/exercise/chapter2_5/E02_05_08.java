package edu.princeton.cs.exercise.chapter2_5;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.8 Write a program Frequency that reads strings from standard input and prints the number
 * of times each string occurs, in descending order of frequency.
 *
 * 编写一个程序 Frequency 从标准输入读取字符串并且打印出每个字符串出现的次数,使用频率倒序排列
 *
 * @author LeonChen
 * @since 12/17/20
 */
class E02_05_08 {


    /**
     *
     */
    public static void main(String[] args) {

    }

    /**
     * 官方实现
     */
    public static class Frequency {

        public static void main(String[] args) {

            // read in and sort the input strings
            String[] a = new String[]{"a", "b", "asdf", "tt", "we", "rv", "b", "te",
                    "tt", "v", "b", "wer", "we"};

            int n = a.length;
            Arrays.sort(a);

            // create an array of distinct strings and their frequencies
            Record[] records = new Record[n];
            String word = a[0];
            int freq = 1;
            int m = 0;
            for (int i = 1; i < n; i++) {
                if (!a[i].equals(word)) {
                    records[m++] = new Record(word, freq);
                    word = a[i];
                    freq = 0;
                }
                freq++;
            }
            records[m++] = new Record(word, freq);

            // sort by frequency and print results
            Arrays.sort(records, 0, m);
            for (int i = m - 1; i >= 0; i--) {
                StdOut.println(records[i]);
            }
        }

        private static class Record implements Comparable<Record> {
            private String word;
            private int freq;

            public Record(String word, int freq) {
                this.word = word;
                this.freq = freq;
            }


            @Override
            public int compareTo(Record o) {

                return freq - o.freq;
            }

            @Override
            public String toString() {
                return "Record{" +
                        "word='" + word + '\'' +
                        ", freq=" + freq +
                        '}';
            }
        }
    }


}
