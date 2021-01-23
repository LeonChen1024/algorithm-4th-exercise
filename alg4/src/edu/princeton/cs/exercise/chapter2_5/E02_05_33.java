package edu.princeton.cs.exercise.chapter2_5;

import edu.princeton.cs.algs4.*;

/**
 * 2.5.33 Random transactions. Develop a generator that takes an argument N, generates
 * N random Transaction objects (see Exercises 2.1.21 and 2.1.22), using assumptions
 * about the transactions that you can defend. Then compare the performance of shellsort,
 * mergesort, quicksort, and heapsort for sorting N transactions, for N=10^3, 10^4, 10^5, and
 * 10^6.
 * <p>
 * 随机交易.开发一个生成器接收一个参数 N,生成 N 个随机Transaction对象(见练习 2.1.21 和 2.1.22),要符合
 * 前面的假设.然后比较使用 shellsort, mergesort, quicksort, 和 heapsort 排序 N 个交易的性能,其中
 * N=10^3, 10^4, 10^5, 和10^6.
 *
 * @author LeonChen
 * @since 1/15/20
 */
class E02_05_33 {


    private final String SHELL_SORT = "ShellSort";
    private final String MERGE_SORT = "MergeSort";
    private final String QUICK_SORT = "QuickSort";
    private final String HEAP_SORT = "HeapSort";

    public static void main(String[] args) {

        E02_05_33 randomTransactions = new E02_05_33();

        int numberOfObjects = 0;

        randomTransactions.doExperiment(numberOfObjects);
    }

    private void doExperiment(int numberOfObjects) {

        int[] values;
        if (numberOfObjects != 0) {
            values = new int[]{numberOfObjects};
        } else {
            values = new int[]{1000, 10000, 100000, 1000000};
        }

        String[] sortAlgorithms = {SHELL_SORT, MERGE_SORT, QUICK_SORT, HEAP_SORT};

        StdOut.printf("%13s %13s %13s\n", "Number of Transactions | ", "Sort Method | ", "Time Spent");

        for (int n = 0; n < values.length; n++) {
            Transaction[] transactions = generateRandomTransactions(values[n]);

            for (int i = 0; i < sortAlgorithms.length; i++) {
                Transaction[] transactionsCopy = new Transaction[transactions.length];
                System.arraycopy(transactions, 0, transactionsCopy, 0, transactions.length);

                Stopwatch timer = new Stopwatch();

                switch (sortAlgorithms[i]) {
                    case SHELL_SORT:
                        Shell.sort(transactionsCopy);
                        break;
                    case MERGE_SORT:
                        Merge.sort(transactionsCopy);
                        break;
                    case QUICK_SORT:
                        Quick.sort(transactionsCopy);
                        break;
                    case HEAP_SORT:
                        Heap.sort(transactionsCopy);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + sortAlgorithms[i]);
                }
                double runningTime = timer.elapsedTime();

                printResults(values[n], sortAlgorithms[i], runningTime);
            }
        }
    }

    private Transaction[] generateRandomTransactions(int numberOfObjects) {
        Transaction[] transactions = new Transaction[numberOfObjects];

        for (int i = 0; i < numberOfObjects; i++) {
            String who = "Client " + (i + 1);

            int month = StdRandom.uniform(12) + 1;
            int maxDay = month == 2 ? 28 : 30;
            int day = StdRandom.uniform(maxDay) + 1;
            int year = StdRandom.uniform(1900, 2018);
            Date date = new Date(month, day, year);

            double amount = (double) Math.round(StdRandom.uniform(0.0, 1000000.0) * 100) / 100;

            Transaction transaction = new Transaction(who, date, amount);
            transactions[i] = transaction;
        }

        return transactions;
    }

    private void printResults(int numberOfTransactions, String sortMethod, double timeSpent) {
        StdOut.printf("%22d %14s %16.2f\n", numberOfTransactions, sortMethod, timeSpent);
    }

}


