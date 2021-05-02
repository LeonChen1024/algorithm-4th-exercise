package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.*;

/**
 * 3.1.13 Which of the symbol-table implementations in this section would you use for
 * an application that does 10^3 put() operations and 10^6 get() operations, randomly
 * intermixed? Justify your answer.
 * <p>
 * 你会为一个做 10^3 put() 操作和10^6 get()操作随机混合的应用选择本章中的哪个符号表?证明你的答案
 *
 * @author LeonChen
 * @since 2/8/21
 */
class E3_1_13 {

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
     * get 操作比put 操作多了 10^3 次, BinarySearchST 和 SequentialSearchST 平均的 put 操作消耗相同,
     * 使用 get 操作开销小的 BinarySearchST. 我们以 1000 以内的数字为例,使用两个客户端来对比结果,使用一个
     * 10^3+10^6 的数组,前 10^3 是 put ,后面是 get,然后打乱.输入两个客户端测试
     *
     * @param args
     */
    public static void main(String[] args) {
        int maxNum = 1000;
        Item[] items = new Item[(int) (Math.pow(10, 3) + Math.pow(10, 6))];
        for (int i = 0; i < Math.pow(10, 3); i++) {
            Item item = new Item(false, StdRandom.uniform(maxNum), i);
            items[i] = item;
        }
        for (int i = 0; i < Math.pow(10, 6); i++) {
            Item item = new Item(true, StdRandom.uniform(maxNum), i);
            items[(int) (i + Math.pow(10, 3))] = item;
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
