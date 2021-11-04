package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.exercise.util.Constants;
import edu.princeton.cs.exercise.util.FileUtil;

import java.util.HashSet;

/**
 * 3.5.31 Spell checker. With the file dictionary.txt from the booksite as command-line
 * argument, the BlackFilter client described on page 491 prints all misspelled words
 * in a text file taken from standard input. Compare the performance of RedBlackBST,
 * SeparateChainingHashST, and LinearProbingHashST for the file WarAndPeace.txt
 * (available on the booksite) with this client and discuss the results.
 * <p>
 * 拼写检查器.使用书的网站中的dictionary.txt作为命令行参数,491 页的 BlackFilter 客户端打印出所有拼写
 * 有误的单词.使用网站中 WarAndPeace.txt 和 RedBlackBST,SeparateChainingHashST 还有
 * LinearProbingHashST 对比性能并讨论结果
 *
 * @author LeonChen
 * @since 9/16/21
 */
class E3_5_31 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        doExperiment(args);

    }

    private static class BlackFilter {

        public HashSet<String> filterUsingRedBlackBST(String dictionaryFilePath, String warAndPeaceFilePath) {
            HashSet<String> filteredWords = new HashSet<>();

            // The value field is not used, but it is required by the red-black BST
            RedBlackBST<String, Boolean> redBlackBST = new RedBlackBST<>();

            In in = new In(dictionaryFilePath);
            while (!in.isEmpty()) {
                redBlackBST.put(in.readString(), true);
            }

            String[] allWords = FileUtil.getAllStrFromFile(warAndPeaceFilePath);

            if (allWords == null) {
                return filteredWords;
            }

            for (String word : allWords) {
                if (!redBlackBST.contains(word)) {
                    filteredWords.add(word);
                }
            }

            return filteredWords;
        }

        public HashSet<String> filterUsingSeparateChainingHashST(String dictionaryFilePath, String warAndPeaceFilePath) {
            HashSet<String> filteredWords = new HashSet<>();

            // The value field is not used, but it is required by the separate chaining hash table
            SeparateChainingHashST<String, Boolean> separateChainingHashTable =
                    new SeparateChainingHashST<>();

            In in = new In(dictionaryFilePath);
            while (!in.isEmpty()) {
                separateChainingHashTable.put(in.readString(), true);
            }

            String[] allWords = FileUtil.getAllStrFromFile(warAndPeaceFilePath);

            if (allWords == null) {
                return filteredWords;
            }

            for (String word : allWords) {
                if (!separateChainingHashTable.contains(word)) {
                    filteredWords.add(word);
                }
            }

            return filteredWords;
        }

        public HashSet<String> filterUsingLinearProbingHashST(String dictionaryFilePath, String warAndPeaceFilePath) {
            HashSet<String> filteredWords = new HashSet<>();

            // The value field is not used, but it is required by the linear probing hash table
            LinearProbingHashST<String, Boolean> linearProbingHashTable =
                    new LinearProbingHashST<>(997);

            In in = new In(dictionaryFilePath);
            while (!in.isEmpty()) {
                linearProbingHashTable.put(in.readString(), true);
            }

            String[] allWords = FileUtil.getAllStrFromFile(warAndPeaceFilePath);

            if (allWords == null) {
                return filteredWords;
            }

            for (String word : allWords) {
                if (!linearProbingHashTable.contains(word)) {
                    filteredWords.add(word);
                }
            }

            return filteredWords;
        }

    }

    // There was no dictionary.txt file on the booksite, so I suspect it has been renamed to commonwords.txt
    // Parameter example: common_words.txt

    private static void doExperiment(String[] args) {
        String dictionaryFileName = args[0];
        String dictionaryFilePath = Constants.FILES_PATH + dictionaryFileName;

        String WarAndPeace = args[1];
        String warAndPeaceFilePath = Constants.FILES_PATH + WarAndPeace;

        BlackFilter blackFilter = new BlackFilter();

        Stopwatch stopwatch = new Stopwatch();
        blackFilter.filterUsingRedBlackBST(dictionaryFilePath, warAndPeaceFilePath);
        double timeSpentWithRedBlackBST = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        blackFilter.filterUsingSeparateChainingHashST(dictionaryFilePath, warAndPeaceFilePath);
        double timeSpentWithSeparateChainingST = stopwatch.elapsedTime();

        stopwatch = new Stopwatch();
        blackFilter.filterUsingLinearProbingHashST(dictionaryFilePath, warAndPeaceFilePath);
        double timeSpentWithLinearProbingST = stopwatch.elapsedTime();

        StdOut.printf("%20s %20s %20s\n", "Time spent red-black BST | ", "Time spent separate chaining | ",
                "Time spent linear probing");
        printResults(timeSpentWithRedBlackBST, timeSpentWithSeparateChainingST, timeSpentWithLinearProbingST);
    }

    private static void printResults(double redBlackBSTTime, double separateChainingTime, double linearProbingTime) {
        StdOut.printf("%24.2f %31.2f %28.2f\n", redBlackBSTTime, separateChainingTime, linearProbingTime);
    }


}
