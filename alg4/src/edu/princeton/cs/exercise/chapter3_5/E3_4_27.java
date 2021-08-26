package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 3.4.27 Double probing. Modify SeparateChainingHashST to use a second hash function
 * and pick the shorter of the two lists. Give a trace of the process of inserting the keys
 * E A S Y Q U T I O N in that order into an initially empty table of size M=3 using
 * the function 11 k % M (for the kth letter) as the first hash function and the function
 * 17 k % M (for the kth letter) as the second hash function. Give the average number of
 * probes for random search hit and search miss in this table.
 * <p>
 * 双重探测.修改 SeparateChainingHashST 使用两个 hash 函数并且选择列表中较短的一个.跟踪按顺序插入键
 * E A S Y Q U T I O N 到初始为空M = 3 的表的流程,使用 hash 函数 11 k % M (k 是第 k 个字母)作为
 * 第一个 hash 函数并且使用17 k % M (k 是第 k 个字母)作为第二个 hash 函数.给出平均随机搜索命中和搜索
 * 丢失的探测次数
 *
 * @author LeonChen
 * @since 8/11/21
 */
class E3_4_27 {

    /**
     * @formatter:off
     * 也就是取两个 hash 值对应列表中较短的一个添加
     * @formatter:on
     */
    public static void main(String[] args) {
        SeparateChainingHashTableDoubleProbing<Integer, Integer> separateChainingHashTableDoubleProbing =
                new SeparateChainingHashTableDoubleProbing<>(9, 2);

        for (int key = 1; key <= 100; key++) {
            separateChainingHashTableDoubleProbing.put(key, key);
        }

        separateChainingHashTableDoubleProbing.get(3);
        separateChainingHashTableDoubleProbing.get(7);

        separateChainingHashTableDoubleProbing.delete(3);
        separateChainingHashTableDoubleProbing.delete(5);

        separateChainingHashTableDoubleProbing.put(10, 10);
    }

    protected static class SeparateChainingHashTableDoubleProbing<Key, Value> {

        protected class SequentialSearchSymbolTable<Key, Value> {

            private class Node {
                Key key;
                Value value;
                Node next;

                public Node(Key key, Value value, Node next) {
                    this.key = key;
                    this.value = value;
                    this.next = next;
                }
            }

            private Node first;
            protected int size;

            public int size() {
                return size;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean contains(Key key) {
                return get(key) != null;
            }

            public Value get(Key key) {
                for (Node node = first; node != null; node = node.next) {
                    if (key.equals(node.key)) {
                        return node.value;
                    }
                }

                return null;
            }

            public void put(Key key, Value value) {
                for (Node node = first; node != null; node = node.next) {
                    if (key.equals(node.key)) {
                        node.value = value;
                        return;
                    }
                }

                first = new Node(key, value, first);
                size++;
            }

            public void delete(Key key) {
                if (first.key.equals(key)) {
                    first = first.next;
                    size--;
                    return;
                }

                for (Node node = first; node != null; node = node.next) {
                    if (node.next != null && node.next.key.equals(key)) {
                        node.next = node.next.next;
                        size--;
                        return;
                    }
                }
            }

            public Iterable<Key> keys() {
                Queue<Key> keys = new Queue<>();

                for (Node node = first; node != null; node = node.next) {
                    keys.enqueue(node.key);
                }

                return keys;
            }

        }

        protected int averageListSize;

        protected int size;
        protected int keysSize;
        protected SequentialSearchSymbolTable<Key, Value>[] symbolTable;

        private static final int DEFAULT_HASH_TABLE_SIZE = 997;
        private static final int DEFAULT_AVERAGE_LIST_SIZE = 5;

        public SeparateChainingHashTableDoubleProbing() {
            this(DEFAULT_HASH_TABLE_SIZE, DEFAULT_AVERAGE_LIST_SIZE);
        }

        public SeparateChainingHashTableDoubleProbing(int initialSize, int averageListSize) {
            this.size = initialSize;
            this.averageListSize = averageListSize;
            symbolTable = new SequentialSearchSymbolTable[size];

            for (int i = 0; i < size; i++) {
                symbolTable[i] = new SequentialSearchSymbolTable<>();
            }
        }

        public int size() {
            return keysSize;
        }

        public boolean isEmpty() {
            return keysSize == 0;
        }

        protected double getLoadFactor() {
            return ((double) keysSize) / (double) size;
        }

        public boolean contains(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to contains() cannot be null");
            }

            return get(key) != null;
        }

        int hash1(Key key) {
            int hash = key.hashCode() & 0x7fffffff;
            hash = (11 * hash) & 0x7fffffff;
            return hash % size;
        }

        int hash2(Key key) {
            int hash = key.hashCode() & 0x7fffffff;
            hash = (17 * hash) & 0x7fffffff;
            return hash % size;
        }

        public void resize(int newSize) {
            SeparateChainingHashTableDoubleProbing<Key, Value> separateChainingHashTableDoubleProbing =
                    new SeparateChainingHashTableDoubleProbing<>(newSize, averageListSize);

            for (Key key : keys()) {
                separateChainingHashTableDoubleProbing.put(key, get(key));
            }

            symbolTable = separateChainingHashTableDoubleProbing.symbolTable;
            size = separateChainingHashTableDoubleProbing.size;
            keysSize = separateChainingHashTableDoubleProbing.keysSize;
        }

        public Value get(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to get() cannot be null");
            }

            int hash1 = hash1(key);
            int hash2 = hash2(key);

            Value value;
            if (symbolTable[hash1].size <= symbolTable[hash2].size) {
                StdOut.println("Searching in list 1");
                value = symbolTable[hash1].get(key);

                if (value == null && hash1 != hash2) {
                    StdOut.println("Searching in list 2");
                    value = symbolTable[hash2].get(key);
                }
            } else {
                StdOut.println("Searching in list 2");
                value = symbolTable[hash2].get(key);

                if (value == null) {
                    StdOut.println("Searching in list 1");
                    value = symbolTable[hash1].get(key);
                }
            }

            return value;
        }

        public void put(Key key, Value value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return;
            }

            boolean containsKey = contains(key);

            int hash1 = hash1(key);
            int hash2 = hash2(key);

            if (!containsKey) {
                keysSize++;

                if (symbolTable[hash1].size <= symbolTable[hash2].size) {
                    StdOut.println("Inserting in list 1");
                    symbolTable[hash1].put(key, value);
                } else {
                    StdOut.println("Inserting in list 2");
                    symbolTable[hash2].put(key, value);
                }
            } else {
                boolean isInList1 = false;

                for (Object keyInList1 : symbolTable[hash1].keys()) {
                    if (keyInList1.equals(key)) {
                        isInList1 = true;
                        break;
                    }
                }

                if (isInList1) {
                    StdOut.println("Updating list 1");
                    symbolTable[hash1].put(key, value);
                } else {
                    StdOut.println("Updating list 2");
                    symbolTable[hash2].put(key, value);
                }
            }

            if (getLoadFactor() > averageListSize) {
                resize(size * 2);
            }
        }

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
                StdOut.println("Deleting in list 1");
                int symbolTableSize = symbolTable[hash1].size;

                symbolTable[hash1].delete(key);
                //Key is not on the shorter list
                if (symbolTableSize == symbolTable[hash1].size) {
                    StdOut.println("Deleting in list 2");
                    symbolTable[hash2].delete(key);
                }
            } else {
                StdOut.println("Deleting in list 2");
                int symbolTableSize = symbolTable[hash2].size;

                symbolTable[hash2].delete(key);
                //Key is not on the shorter list
                if (symbolTableSize == symbolTable[hash2].size) {
                    StdOut.println("Deleting in list 1");
                    symbolTable[hash1].delete(key);
                }
            }

            if (size > 1 && getLoadFactor() <= averageListSize / (double) 4) {
                resize(size / 2);
            }
        }

        public Iterable<Key> keys() {
            Queue<Key> keys = new Queue<>();

            for (SequentialSearchSymbolTable<Key, Value> sequentialSearchST : symbolTable) {
                for (Key key : sequentialSearchST.keys()) {
                    keys.enqueue(key);
                }
            }

            if (!keys.isEmpty() && keys.peek() instanceof Comparable) {
                Key[] keysToBeSorted = (Key[]) new Comparable[keys.size()];
                for (int i = 0; i < keysToBeSorted.length; i++) {
                    keysToBeSorted[i] = keys.dequeue();
                }

                Arrays.sort(keysToBeSorted);

                for (Key key : keysToBeSorted) {
                    keys.enqueue(key);
                }
            }

            return keys;
        }
    }


}
