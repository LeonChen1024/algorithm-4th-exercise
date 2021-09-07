package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.4.26 Lazy delete for linear probing. Add to LinearProbingHashST a delete()
 * method that deletes a key-value pair by setting the value to null (but not removing
 * the key) and later removing the pair from the table in resize(). Your primary
 * challenge is to decide when to call resize(). Note : You should overwrite the null
 * value if a subsequent put() operation associates a new value with the key. Make sure
 * that your program takes into account the number of such tombstone items, as well as
 * the number of empty positions, in making the decision whether to expand or contract
 * the table.
 * <p>
 * 线性探查的懒删除.添加一个delete()方法到 LinearProbingHashST ,它设置键的值为 null(但是不删除键)并且
 * 在后面扩容的时候删除键值对.你的主要挑战是决定什么时候调用  resize().注意: 你应该重写 put() 操作关联到
 * 该键的新值.确保你的程序计算到了已经删除的项,同时还有空的位置,来判断是该扩展还是收缩表
 *
 * @author LeonChen
 * @since 8/10/21
 */
class E3_4_26 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        LinearProbingHashTableLazyDelete<Integer, Integer> linearProbingHashTableLazyDelete =
                new LinearProbingHashTableLazyDelete<>(16);

        for (int i = 0; i < 4; i++) {
            linearProbingHashTableLazyDelete.put(i, i);
        }

        StdOut.println("Size: " + linearProbingHashTableLazyDelete.size() + " Expected: 4");
        StdOut.println("Lazy delete 2");
        linearProbingHashTableLazyDelete.lazyDelete(2);
        StdOut.println("Size: " + linearProbingHashTableLazyDelete.size() + " Expected: 3");
        StdOut.println("Lazy delete 0");
        linearProbingHashTableLazyDelete.lazyDelete(0);
        StdOut.println("Expected: Deleting tombstone items");
        StdOut.println("Size: " + linearProbingHashTableLazyDelete.size() + " Expected: 2");

        for (int i = 4; i < 8; i++) {
            linearProbingHashTableLazyDelete.put(i, i);
        }

        StdOut.println("\nLazy delete 3");
        linearProbingHashTableLazyDelete.lazyDelete(3);
        StdOut.println("Lazy delete 4");
        linearProbingHashTableLazyDelete.lazyDelete(4);
        StdOut.println("Lazy delete 5");
        linearProbingHashTableLazyDelete.lazyDelete(5);
        StdOut.println("Put 3");
        linearProbingHashTableLazyDelete.put(3, 3);
        StdOut.println("Lazy delete 6");
        linearProbingHashTableLazyDelete.lazyDelete(6);
        StdOut.println("Lazy delete 7");
        linearProbingHashTableLazyDelete.lazyDelete(7);
        StdOut.println("Expected: Deleting tombstone items");
        StdOut.println("Size: " + linearProbingHashTableLazyDelete.size() + " Expected: 2");
    }

    private static class LinearProbingHashTableLazyDelete<Key, Value> extends LinearProbingHashST<Key, Value> {

        private int tombstoneItemCount;

        LinearProbingHashTableLazyDelete(int size) {
            super(size);
        }

        private void resize(int newSize) {
            StdOut.println("Deleting tombstone items");
            tombstoneItemCount = 0;

            //Resize
            LinearProbingHashTableLazyDelete<Key, Value> tempHashTable = new LinearProbingHashTableLazyDelete<>(newSize);
            for (int i = 0; i < m; i++) {
                if (vals[i] != null) {
                    tempHashTable.put(keys[i], vals[i]);
                }
            }

            keys = tempHashTable.keys;
            vals = tempHashTable.vals;
            m = tempHashTable.m;
        }

        public void put(Key key, Value value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return;
            }

            if (n + tombstoneItemCount >= m / (double) 2) {
                resize(m * 2);
            }

            int tableIndex;
            for (tableIndex = hash(key); keys[tableIndex] != null; tableIndex =
                    (tableIndex + 1) % m) {
                if (keys[tableIndex].equals(key)) {

                    if (vals[tableIndex] == null) {
                        tombstoneItemCount--;
                        n++;
                    }

                    vals[tableIndex] = value;
                    return;
                }
            }

            keys[tableIndex] = key;
            vals[tableIndex] = value;
            n++;
        }

        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (!contains(key)) {
                return;
            }

            int tableIndex = hash(key);
            while (!keys[tableIndex].equals(key)) {
                tableIndex = (tableIndex + 1) % m;
            }

            keys[tableIndex] = null;
            vals[tableIndex] = null;
            n--;

            tableIndex = (tableIndex + 1) % m;

            while (keys[tableIndex] != null) {
                Key keyToRedo = keys[tableIndex];
                Value valueToRedo = vals[tableIndex];

                keys[tableIndex] = null;
                vals[tableIndex] = null;
                n--;

                put(keyToRedo, valueToRedo);
                tableIndex = (tableIndex + 1) % m;
            }

            if (n > 1 && n <= m / (double) 8) {
                resize(m / 2);
            }
        }

        public void lazyDelete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (!contains(key)) {
                return;
            }

            int tableIndex = hash(key);
            while (!keys[tableIndex].equals(key)) {
                tableIndex = (tableIndex + 1) % m;
            }

            vals[tableIndex] = null;
            tombstoneItemCount++;

            n--;

            if (n <= m / (double) 8) {
                resize(m / 2);
            }
        }
    }


}
