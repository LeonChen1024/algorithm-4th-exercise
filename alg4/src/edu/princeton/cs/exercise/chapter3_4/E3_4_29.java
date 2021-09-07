package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 3.4.29 Deletion. Implement an eager delete() method for the methods described in
 * each of the previous two exercises.
 * <p>
 * 删除.实现一个饿 delete()方法给前面的两个练习
 *
 * @author LeonChen
 * @since 8/13/21
 */
class E3_4_29 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        //Double probing test
        SeparateChainingHashTableDoubleProbingDelete separateChainingHashTableDoubleProbingDelete =
                new SeparateChainingHashTableDoubleProbingDelete();

        for (int key = 1; key <= 100; key++) {
            separateChainingHashTableDoubleProbingDelete.put(key, key);
        }
        StdOut.println("Size: " + separateChainingHashTableDoubleProbingDelete.size() + " Expected: 100");

        for (int key = 1; key <= 100; key += 2) {
            separateChainingHashTableDoubleProbingDelete.delete(key);
        }
        StdOut.println("Size: " + separateChainingHashTableDoubleProbingDelete.size() + " Expected: 50");

        //Double hashing test
        DoubleHashingHashTableDelete doubleHashingHashTableDelete =
                new DoubleHashingHashTableDelete(50);

        for (int key = 1; key <= 100; key++) {
            doubleHashingHashTableDelete.put(key, key);
        }
        StdOut.println("\nSize: " + doubleHashingHashTableDelete.size() + " Expected: 100");

        for (int key = 1; key <= 100; key += 2) {
            doubleHashingHashTableDelete.eagerDelete(key);
        }
        StdOut.println("Size: " + doubleHashingHashTableDelete.size() + " Expected: 50");
    }

    private static class SeparateChainingHashTableDoubleProbingDelete<Key, Value> extends E3_4_27.SeparateChainingHashTableDoubleProbing<Key, Value> {

        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (isEmpty() || !contains(key)) {
                return;
            }

            keysSize--;

            int hash1 = hash1(key);
            int hash2 = hash2(key);

            if (!symbolTable[hash1].isEmpty() &&
                    (symbolTable[hash1].size <= symbolTable[hash2].size || symbolTable[hash2].isEmpty())) {
                int symbolTableSize = symbolTable[hash1].size;

                symbolTable[hash1].delete(key);
                //Key is not on the shorter list
                if (symbolTableSize == symbolTable[hash1].size) {
                    symbolTable[hash2].delete(key);
                }
            } else {
                int symbolTableSize = symbolTable[hash2].size;

                symbolTable[hash2].delete(key);
                //Key is not on the shorter list
                if (symbolTableSize == symbolTable[hash2].size) {
                    symbolTable[hash1].delete(key);
                }
            }

            if (size > 0 && getLoadFactor() <= averageListSize / (double) 4) {
                resize(size / 2);
            }
        }
    }

    private static class DoubleHashingHashTableDelete<Key, Value> extends E3_4_28.DoubleHashingHashTable<Key, Value> {

        DoubleHashingHashTableDelete(int size) {
            super(size);
        }

        //Eager delete in double hashing requires a rehash() operation, making it O(n)
        public void eagerDelete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (!contains(key)) {
                return;
            }

            int tableIndex = hash(key);
            while (!keys[tableIndex].equals(key)) {
                tableIndex = (tableIndex + secondaryHash(key)) % size;
            }

            keysSize--;
            keys[tableIndex] = null;
            values[tableIndex] = null;

            if (keysSize <= size / (double) 8) {
                resize(size / 2);
                lgM--;
            } else {
                rehash();
            }
        }

        private void rehash() {
            tombstoneItemCount = 0;

            DoubleHashingHashTableDelete<Key, Value> tempHashTable =
                    new DoubleHashingHashTableDelete<>(size);

            for (int i = 0; i < keys.length; i++) {
                if (values[i] != null) {
                    tempHashTable.put(keys[i], values[i]);
                }
            }

            keys = tempHashTable.keys;
            values = tempHashTable.values;
        }
    }

}
