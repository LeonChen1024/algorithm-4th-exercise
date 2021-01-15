package edu.princeton.cs.exercise.chapter2_5;


import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.17 Check stability. Extend your check() method from Exercise 2.1.16 to call sort() for a
 * given array and return true if sort() sorts the array in order in a stable manner, false
 * otherwise. Do not assume that sort() is restricted to move data only with exch().
 *
 * 检查稳定性.扩展你练习 2.1.16中的check() 方法来对给定数组调用 sort() 并且如果排序是稳定的就返回true,否则就
 * 返回false.不要假设 sort() 是严格使用 exch() 来移动数据.
 *
 * @author LeonChen
 * @since 12/29/20
 */
class E02_05_17 {

    private static final String QUICK = "QUICK";
    private static final String INSERTION = "INSERTION";
    private static final String MERGE = "MERGE";
    private static final String SELECTION = "SELECTION";

    private static class Item<T extends Comparable> implements Comparable<Item<T>> {

        private T key;
        private int index;

        public Item(T key, int index) {
            this.key = key;
            this.index = index;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(Item<T> o) {
            return key.compareTo(o.key);
        }
    }

    /**
     * 创建一个数据结构保存有数据key 和原来的索引 index,排序后相同元素会相邻,只需要对比这些元素的原始索引排序
     * 是否没有改变就可以了
     */
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{4, 4, 2, 76, 2, 5, 6, 23, 7, 3, 4, 2};

        StdOut.println(QUICK + ": " + checkStability(arr, QUICK));
        StdOut.println(INSERTION + ": " + checkStability(arr, INSERTION));
        StdOut.println(MERGE + ": " + checkStability(arr, MERGE));
        StdOut.println(SELECTION + ": " + checkStability(arr, SELECTION));

    }

    private static boolean checkStability(Integer[] arr, String mode) {
        Item[] items = new Item[arr.length];
        for (int i = 0; i < arr.length; i++) {
            items[i] = new Item(arr[i], i);
        }

        switch (mode) {
            case QUICK:
                Quick.sort(items);
                break;
            case INSERTION:
                Insertion.sort(items);
                break;
            case MERGE:
                Merge.sort(items);
                break;
            case SELECTION:
                Selection.sort(items);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        for (int i = 0; i < items.length - 1; i++) {
            if (items[i].compareTo(items[i + 1]) == 0) {
                if (items[i].getIndex() > items[i + 1].getIndex()) {
                    return false;
                }
            }
        }

        return true;
    }

}
