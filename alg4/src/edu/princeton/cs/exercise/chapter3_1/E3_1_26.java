package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 3.1.26 Frequency count from a dictionary. Modify FrequencyCounter to take the
 * name of a dictionary file as its argument, count frequencies of the words from standard
 * input that are also in that file, and print two tables of the words with their frequencies,
 * one sorted by frequency, the other sorted in the order found in the dictionary file.
 * <p>
 * 字典频率计算.修改FrequencyCounter接受一个字典文件作为参数,计算标准输入中单词的频率,并且打印出单词的频率,
 * 一个用频率排序,另一个按照字典文件中出现的顺序排序
 *
 * @author LeonChen
 * @since 2/19/21
 */
class E3_1_26 {

    private static class Word implements Comparable<Word> {

        private String value;
        private int frequency;
        private int orderInFile;

        private Word(String wordValue) {
            this.value = wordValue;
            frequency = 1;
        }

        @Override
        public int compareTo(Word that) {
            return value.compareTo(that.value);
        }

        @Override
        public String toString() {
            return "Word{" +
                    "value='" + value + '\'' +
                    ", frequency=" + frequency +
                    ", orderInFile=" + orderInFile +
                    '}';
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        frequencyCounter(new String[]{"test", "af", "ewe", "boso", "ovn", "a", "vi", "a", "test", "test"});
    }

    private static void frequencyCounter(String[] values) {

        int distinct = 0, words = 0;
        BinarySearchST<String, Word> binarySearchST = new BinarySearchST<>();

        for (int i = 0; i < values.length; i++) {
            String key = values[i];
            words++;
            if (binarySearchST.contains(key)) {
                binarySearchST.get(key).frequency += 1;
            } else {
                Word word = new Word(key);
                word.orderInFile = i;
                binarySearchST.put(key, word);
                distinct++;
            }
        }

        Word[] unsortWords = new Word[binarySearchST.size()];
        int i = 0;
        for (String key : binarySearchST.keys()) {
            unsortWords[i] = binarySearchST.get(key);
            i++;
        }

        Arrays.sort(unsortWords, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.frequency - o2.frequency;
            }
        });

        StdOut.println(Arrays.toString(unsortWords));

        Arrays.sort(unsortWords, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.orderInFile - o2.orderInFile;
            }

        });

        StdOut.println(Arrays.toString(unsortWords));
    }

}
