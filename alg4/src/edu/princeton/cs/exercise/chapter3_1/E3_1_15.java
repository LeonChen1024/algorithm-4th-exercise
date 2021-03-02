package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 3.1.15 Assume that searches are 1,000 times more frequent than insertions for a
 * BinarySearchST client. Estimate the percentage of the total time that is devoted to
 * insertions, when the number of searches is 10^3, 10^6, and 10^9.
 * <p>
 * 假设在一个 BinarySearchST 客户端中搜索是插入操作的 1000 倍的频率.估算插入耗时占总时间的比率,搜索次数
 * 分别为 10^3, 10^6, and 10^9
 *
 * @author LeonChen
 * @since 2/9/20
 */
class E3_1_15 {

    private static class Item {
        boolean get;
        Integer key;
        Integer value;

        public Item(boolean get, Integer key, Integer value) {
            this.get = get;
            this.key = key;
            this.value = value;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int maxNum = 1000;
        int[] searchTimeArr = new int[]{(int) Math.pow(10, 3), (int) Math.pow(10, 6),
                (int) Math.pow(10, 9)};
        for (int i = 0; i < searchTimeArr.length; i++) {
            int searchTime = searchTimeArr[i];
            int insertTime = searchTime / 1000;
            Item[] items = new Item[searchTime + insertTime];
            for (int j = 0; j < insertTime; j++) {
                Item item = new Item(false, StdRandom.uniform(maxNum), j);
                items[j] = item;
            }
            for (int j = 0; j < searchTime; j++) {
                Item item = new Item(true, StdRandom.uniform(maxNum), j);
                items[j + insertTime] = item;
            }
            StdRandom.shuffle(items);
            double totalPut = 0;
            Stopwatch putWatch;
            Stopwatch stopwatch = new Stopwatch();
            BinarySearchST<Integer, Integer> bst = new BinarySearchST<>();
            for (Item item : items) {
                if (item.get) {
                    bst.get(item.key);
                } else {
                    putWatch = new Stopwatch();
                    bst.put(item.key, item.value);
                    totalPut += putWatch.elapsedTime();
                }
            }
            double total = stopwatch.elapsedTime();
            StdOut.println("BinarySearchST put use " + (totalPut * 100) / total + "%");
        }


    }


}
