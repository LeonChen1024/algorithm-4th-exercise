package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 3.4.31 Cuckoo hashing. Develop a symbol-table implementation that maintains two
 * hash tables and two hash functions. Any given key is in one of the tables, but not both.
 * When inserting a new key, hash to one of the tables; if the table position is occupied,
 * replace that key with the new key and hash the old key into the other table (again
 * kicking out a key that might reside there). If this process cycles, restart. Keep the
 * tables less than half full. This method uses a constant number of equality tests in
 * the worst case for search (trivial) and amortized constant time for insert.
 * <p>
 * 布谷 hash.开发一个符号表实现维护两个 hash 表和两个 hash 函数.所有给定的键都在其中一个表,不是两个.
 * 当插入一个新key 时,hash 到其中一个表;如果这个表位置被占用了,使用新 key 替换旧 key 并将旧 key hash 到
 * 另一个表中(再次踢出可能占有该位置的键).如果这个操作循环了,重启.保证表大小半满以下.这个方法使用一个常量等级
 * 成本在最差的搜索情况中并且插入摊销成本也是常量级
 *
 * @author LeonChen
 * @since 8/15/21
 */
class E3_4_31 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        CuckooHashing<Integer, Integer> cuckooHashing = new CuckooHashing<>(16);

        for (int key = 1; key < 10; key++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            cuckooHashing.put(randomKey, randomKey);
        }

        cuckooHashing.get(5);

        for (Integer key : cuckooHashing.keys()) {
            StdOut.print(key + " ");
        }

        StdOut.println();

        for (int key = 1; key < 1000000; key++) {
            cuckooHashing.put(key, key);
        }
        for (int key = 1; key < 1000000; key++) {
            cuckooHashing.delete(key);
        }

        for (int key = 1; key < 1500000; key++) {
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            cuckooHashing.put(randomKey, randomKey);
        }
    }

    private static class CuckooHashing<Key, Value> {

        private class Entry {
            Key key;
            Value value;

            Entry(Key key, Value value) {
                this.key = key;
                this.value = value;
            }
        }

        private final class HashFunction<T> {
            private final int mA, mB;  // Coefficients for this hash function
            private final int mLgSize; // Log of the size of the hash tables.

            /**
             * Constructs a new hash function using the specified coefficients and
             * the log of the number of buckets in the hash table.
             */
            public HashFunction(int coefficientA, int coefficientB, int lgSize) {
                mA = coefficientA;
                mB = coefficientB;
                mLgSize = lgSize;
            }

            public int hash(Key key) {
                /* If the object is null, just evaluate to zero. */
                if (key == null) {
                    return 0;
                }

                /* Otherwise, split its hash code into upper and lower bits. */
                final int hashCode = key.hashCode();
                final int upper = hashCode >>> 16;
                final int lower = hashCode & (0xFFFF);

                /* Return the pairwise product of those bits, shifted down so that
                 * only lgSize bits remain in the output.
                 */
                return (upper * mA + lower * mB) >>> (32 - mLgSize);
            }
        }

        private int keysSize;
        private int size;

        private Entry keysAndValues[][];
        private HashFunction[] hashFunctions;

        CuckooHashing(int size) {
            this.size = size;

            keysAndValues = (Entry[][]) Array.newInstance(Entry.class,
                    2, size);

            //The lg of the hash table size
            //Used to distribute keys uniformly in the hash function
            int lgM = (int) (Math.log(size) / Math.log(2));

            hashFunctions = new HashFunction[2];
            for (int i = 0; i < 2; i++) {
                int randomCoefficientA = StdRandom.uniform(Integer.MAX_VALUE);
                int randomCoefficientB = StdRandom.uniform(Integer.MAX_VALUE);

                hashFunctions[i] = new HashFunction(randomCoefficientA, randomCoefficientB, lgM);
            }
        }

        public boolean isEmpty() {
            return keysSize == 0;
        }

        public int keysSize() {
            return keysSize;
        }

        private void updateHashFunctions() {
            int lgM = (int) (Math.log(size) / Math.log(2));

            for (int i = 0; i < 2; i++) {
                int randomCoefficientA = StdRandom.uniform(Integer.MAX_VALUE);
                int randomCoefficientB = StdRandom.uniform(Integer.MAX_VALUE);

                hashFunctions[i] = new HashFunction(randomCoefficientA, randomCoefficientB, lgM);
            }
        }

        private void resize(int newSize) {
            StdOut.println("New Size: " + newSize);

            size = newSize;

            Entry[][] oldEntries = keysAndValues;
            keysAndValues = (Entry[][]) Array.newInstance(Entry.class,
                    2, newSize);

            boolean tryToResize = true;

            while (tryToResize) {
                tryToResize = false;

                updateHashFunctions();

                for (Entry[] keysAndValues : keysAndValues) {
                    Arrays.fill(keysAndValues, null);
                }

                //Try to add all keys and values
                //Hash table 1
                for (Entry entry : oldEntries[0]) {
                    if (entry != null && tryToInsert(entry) != null) {
                        tryToResize = true;
                        break;
                    }
                }

                //Hash table 2
                if (!tryToResize) {
                    for (Entry entry : oldEntries[1]) {
                        if (entry != null && tryToInsert(entry) != null) {
                            tryToResize = true;
                            break;
                        }
                    }
                }
            }
        }

        private void rehash() {
            StdOut.println("Rehashing Keys: " + keysSize);

            Entry[] tempKeysAndValues = (Entry[]) Array.newInstance(Entry.class, keysSize);
            int tempKeysAndValuesIndex = 0;

            for (int i = 0; i < 2; i++) {
                for (Entry entry : keysAndValues[i]) {
                    if (entry != null) {
                        tempKeysAndValues[tempKeysAndValuesIndex++] = entry;
                    }
                }
            }

            boolean tryToRehash = true;

            while (tryToRehash) {
                tryToRehash = false;

                updateHashFunctions();

                for (Entry[] keysAndValues : keysAndValues) {
                    Arrays.fill(keysAndValues, null);
                }

                //Try to add all keys and values
                for (Entry entry : tempKeysAndValues) {
                    if (tryToInsert(entry) != null) {
                        tryToRehash = true;
                        break;
                    }
                }
            }
        }

        public boolean contains(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to contains() cannot be null");
            }

            return get(key) != null;
        }

        public Value get(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to get() cannot be null");
            }

            for (int hashTableIndex = 0; hashTableIndex < 2; hashTableIndex++) {
                int hash = hashFunctions[hashTableIndex].hash(key);

                if (keysAndValues[hashTableIndex][hash] != null && keysAndValues[hashTableIndex][hash].key.equals(key)) {
                    return keysAndValues[hashTableIndex][hash].value;
                }
            }

            return null;
        }

        public void put(Key key, Value value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return;
            }

            //Update key if it already exists
            for (int hashTableIndex = 0; hashTableIndex < 2; hashTableIndex++) {
                int hash = hashFunctions[hashTableIndex].hash(key);

                if (keysAndValues[hashTableIndex][hash] != null && keysAndValues[hashTableIndex][hash].key.equals(key)) {
                    keysAndValues[hashTableIndex][hash].value = value;
                    return;
                }
            }

            //Key does not exist, let's insert it
            //Check if the number of keys is equal or more than half of the hash table size
            if (keysSize >= size) {
                resize(size * 2);
            }

            Entry entry = new Entry(key, value);
            while (entry != null) {
                entry = tryToInsert(entry);

                if (entry != null) {
                    rehash();
                }
            }

            keysSize++;
        }

        /**
         * Given an Entry, tries to insert that entry into the hash table, taking
         * several iterations if necessary.
         *
         * @return The last displaced entry, or null if all collisions were resolved.
         */
        private Entry tryToInsert(Entry entry) {

            int maxTries = size + 1;
            int hashTableIndex = 0;

            for (int numberOfTries = 0; numberOfTries < maxTries; numberOfTries++) {
                int hash = hashFunctions[hashTableIndex].hash(entry.key);

                if (keysAndValues[hashTableIndex][hash] == null) {
                    keysAndValues[hashTableIndex][hash] = entry;
                    return null;
                }

                Entry entryToDisplace = keysAndValues[hashTableIndex][hash];
                keysAndValues[hashTableIndex][hash] = entry;

                entry = entryToDisplace;
                hashTableIndex = (hashTableIndex + 1) % 2;
            }

            return entry;
        }

        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (!contains(key)) {
                return;
            }

            for (int hashTableIndex = 0; hashTableIndex < 2; hashTableIndex++) {
                int hash = hashFunctions[hashTableIndex].hash(key);

                if (keysAndValues[hashTableIndex][hash] != null && keysAndValues[hashTableIndex][hash].key.equals(key)) {
                    keysAndValues[hashTableIndex][hash] = null;
                    break;
                }
            }

            keysSize--;

            if (keysSize > 1 && keysSize <= size / (double) 8) {
                resize(size / 2);
            }
        }

        public Iterable<Key> keys() {
            Queue<Key> keySet = new Queue<>();

            for (int hashTableIndex = 0; hashTableIndex < keysAndValues.length; hashTableIndex++) {
                for (Entry entry : keysAndValues[hashTableIndex]) {
                    if (entry != null) {
                        keySet.enqueue(entry.key);
                    }
                }
            }

            if (!keySet.isEmpty() && keySet.peek() instanceof Comparable) {
                Key[] keysToBeSorted = (Key[]) new Comparable[keySet.size()];
                for (int i = 0; i < keysToBeSorted.length; i++) {
                    keysToBeSorted[i] = keySet.dequeue();
                }

                Arrays.sort(keysToBeSorted);

                for (Key key : keysToBeSorted) {
                    keySet.enqueue(key);
                }
            }

            return keySet;
        }
    }


}
