package edu.princeton.cs.exercise.chapter2_5;


import edu.princeton.cs.algs4.*;

/**
 * 2.5.18 Force stability. Write a wrapper method that makes any sort stable by creating a new
 * key type that allows you to append each key’s index to the key, call sort(), the n restore the
 * original key after the sort.
 * <p>
 * 强制稳定性. 编写一个包装方法使得任意的排序稳定,通过创建一个新的类型允许你添加key 的index 到key中,调用sort(),
 * 结束后将 key 按照初始排序稳定
 *
 * @author LeonChen
 * @since 12/29/21
 */
class E02_05_18 {

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
        // 强制稳定
        for (int i = 0; i < items.length - 1; i++) {
            if (items[i].compareTo(items[i + 1]) == 0) {
                Item min = items[i];
                int minIndex = i;
                for (int j = i + 1; j < items.length; j++) {
                    if (min.compareTo(items[j]) == 0) {
                        if (min.getIndex() > items[j].getIndex()) {
                            min = items[j];
                            minIndex = j;
                        }
                    } else {
                        break;
                    }
                }
                items[minIndex] = items[i];
                items[i] = min;
            }
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
