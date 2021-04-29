package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.1.3 Develop a symbol-table implementation OrderedSequentialSearchST that
 * uses an ordered linked list as the underlying data structure to implement our ordered
 * symbol-table API.
 * <p>
 * 开发一个符号表实现 OrderedSequentialSearchST,使用一个有序链表作为底层数据结构来实现有序符号表 API
 *
 * @author LeonChen
 * @since 1/30/21
 */
class E3_1_03 {

    /**
     * 可以基于官方的无序链表实现SequentialSearchST修改,修改 put ,get,delete
     *
     * @param args
     */
    public static void main(String[] args) {
    }

    public static class OrderedSequentialSearchST<Key extends Comparable, Value> {
        private int n;           // number of key-value pairs
        private Node first;      // the linked list of key-value pairs

        // a helper linked list data type
        private class Node {
            private Key key;
            private Value val;
            private Node next;

            public Node(Key key, Value val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        /**
         * Initializes an empty symbol table.
         */
        public OrderedSequentialSearchST() {
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

        /**
         * Returns true if this symbol table contains the specified key.
         *
         * @param key the key
         * @return {@code true} if this symbol table contains {@code key};
         * {@code false} otherwise
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public boolean contains(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }

        /**
         * Returns the value associated with the given key in this symbol table.
         *
         * @param key the key
         * @return the value associated with the given key if the key is in the symbol table
         * and {@code null} if the key is not in the symbol table
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public Value get(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to get() is null");
            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key))
                    return x.val;
                // 因为是有序的,所以当已经超过 key 的大小,可以提前结束
                if (key.compareTo(x.key) < 0) {
                    return null;
                }
            }
            return null;
        }

        public void put(Key key, Value val) {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");
            if (val == null) {
                delete(key);
                return;
            }
            if (isEmpty()) {
                first = new Node(key, val, first);
            } else {
                Node prev = null;
                for (Node x = first; x != null; x = x.next) {
                    if (key.equals(x.key)) {
                        x.val = val;
                        return;
                    }
                    if (key.compareTo(x.key) < 0) {
                        if (prev == null) {
                            first = new Node(key, val, x);
                        } else {
                            prev.next = new Node(key, val, x);
                        }
                        break;
                    }
                    prev = x;
                    if (x.next == null) {
                        x.next = new Node(key, val, null);
                        break;
                    }
                }

            }

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
            if (key.equals(x.key)) {
                n--;
                return x.next;
            }
            if (key.compareTo(x.key) < 0) {
                return x;
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


        /**
         * Unit tests the {@code SequentialSearchST} data type.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            OrderedSequentialSearchST<String, Integer> st =
                    new OrderedSequentialSearchST<String, Integer>();
            for (int i = 0; !StdIn.isEmpty(); i++) {
                String key = StdIn.readString();
                if (key.equals("-")) {
                    break;
                }
                st.put(key, i);
            }
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
        }
    }


}
