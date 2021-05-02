package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.*;

/**
 * 3.1.14 Which of the symbol-table implementations in this section would you use for
 * an application that does 10^6 put() operations and 10^3 get() operations, randomly
 * intermixed? Justify your answer.
 * <p>
 * 你会对一个做了 10^6 put() 操作和 10^3 get()操作随机混合的应用选择本章中的哪种符号表实现?证明你的答案
 *
 * @author LeonChen
 * @since 2/9/21
 */
class E3_1_14 {

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
     * 根据上题的结论,依然是选择 BinarySearchST
     *
     * @param args
     */
    public static void main(String[] args) {
        int maxNum = 1000;
        Item[] items = new Item[(int) (Math.pow(10, 3) + Math.pow(10, 6))];
        for (int i = 0; i < Math.pow(10, 6); i++) {
            Item item = new Item(false, StdRandom.uniform(maxNum), i);
            items[i] = item;
        }
        for (int i = 0; i < Math.pow(10, 3); i++) {
            Item item = new Item(true, StdRandom.uniform(maxNum), i);
            items[(int) (i + Math.pow(10, 6))] = item;
        }

        StdRandom.shuffle(items);

        Stopwatch stopwatch = new Stopwatch();
        SequentialSearchST<Integer, Integer> sst = new SequentialSearchST<>();
        for (Item item : items) {
            if (item.get) {
                sst.get(item.key);
            } else {
                sst.put(item.key, item.value);
            }
        }
        double v = stopwatch.elapsedTime();
        StdOut.println("SequentialSearchST use " + v + "sec");

        stopwatch = new Stopwatch();
        BinarySearchST<Integer, Integer> bst = new BinarySearchST<>();
        for (Item item : items) {
            if (item.get) {
                bst.get(item.key);
            } else {
                bst.put(item.key, item.value);
            }
        }
        v = stopwatch.elapsedTime();
        StdOut.println("BinarySearchST use " + v + "sec");

    }


}
