package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.5.2 Develop a SET implementation SequentialSearchSET by starting with the code
 * for SequentialSearchST and eliminating all of the code involving values.
 * <p>
 * 开发一个 SET 实现SequentialSearchSET 从 SequentialSearchST衍生并且消除所有带有值得方法.
 *
 * @author LeonChen
 * @since 9/3/21
 */
class E3_5_02 {

    /**
     * @formatter:off
     * 就是在这两个结构忽略值即可,key 本身就是去重的.使用 ST 的 Set 由于 ST 的特性可以更方便的实现一些如
     * min,floor 等操作,但是插入和搜索效率会低一些
     * @formatter:on
     */
    public static void main(String[] args) {
        SequentialSearchSET<Integer> sequentialSearchSET = new SequentialSearchSET<>();
        sequentialSearchSET.put(3);
        sequentialSearchSET.put(67);
        sequentialSearchSET.put(4);
        sequentialSearchSET.put(6);
        sequentialSearchSET.put(7);
        sequentialSearchSET.put(6);

        StdOut.println("test keys ");
        for (Integer key : sequentialSearchSET.keys()) {
            StdOut.print(key + ",");
        }
        StdOut.println(" expect: 3,67,4,6,7 not ordered");

        StdOut.println("test contains  ");
        StdOut.print(sequentialSearchSET.contains(3));
        StdOut.println(" expect: true");

        StdOut.println("test delete  ");
        sequentialSearchSET.delete(3);
        for (Integer key : sequentialSearchSET.keys()) {
            StdOut.print(key + ",");
        }
        StdOut.println(" expect: 67,4,6,7 not ordered");

    }

    private static class SequentialSearchSET<Key> {

        private int n;           // number of key-value pairs
        private Node first;      // the linked list of key-value pairs
        public int compares; //对比次数

        // a helper linked list data type
        private class Node {
            private Key key;
            private Node next;

            public Node(Key key, Node next) {
                this.key = key;
                this.next = next;
            }
        }

        /**
         * Initializes an empty symbol table.
         */
        public SequentialSearchSET() {
        }

        /**
         * Returns the number of key-value pairs in this symbol table.
         *
         * @return the number of key-value pairs in this symbol table
         */
        public int size() {
            return n;
        }

        /**
         * Returns true if this symbol table is empty.
         *
         * @return {@code true} if this symbol table is empty;
         * {@code false} otherwise
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        public boolean contains(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to get() is null");
            for (Node x = first; x != null; x = x.next) {
                compares++;
                if (key.equals(x.key))
                    return true;
            }
            return false;
        }


        public void put(Key key) {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");

            for (Node x = first; x != null; x = x.next) {
                compares++;
                if (key.equals(x.key)) {
                    return;
                }
            }
            first = new Node(key, first);
            n++;
        }

        /**
         * Removes the specified key and its associated value from this symbol table
         * (if the key is in this symbol table).
         *
         * @param key the key
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void delete(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to delete() is null");
            first = delete(first, key);
        }

        // delete key in linked list beginning at Node x
        // warning: function call stack too large if table is large
        private Node delete(Node x, Key key) {
            if (x == null) return null;
            compares++;
            if (key.equals(x.key)) {
                n--;
                return x.next;
            }
            x.next = delete(x.next, key);
            return x;
        }


        /**
         * Returns all keys in the symbol table as an {@code Iterable}.
         * To iterate over all of the keys in the symbol table named {@code st},
         * use the foreach notation: {@code for (Key key : st.keys())}.
         *
         * @return all keys in the symbol table
         */
        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (Node x = first; x != null; x = x.next)
                queue.enqueue(x.key);
            return queue;
        }


    }


}
