package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.2.13 Give nonrecursive implementations of get() and put() for BST.
 * Partial solution : Here is an implementation of get():
 * @formatter:off
 *     public Value get(Key key) {
 *         Node x = root;
 *         while (x != null) {
 *             int cmp = key.compareTo(x.key);
 *             if (cmp == 0) return x.val;
 *             else if (cmp < 0) x = x.left;
 *             else if (cmp > 0) x = x.right;
 *         }
 *         return null;
 *     }
 * @formatter:on
 * The implementation of put() is more complicated because of the need to save a pointer
 * to the parent node to link in the new node at the bottom. Also, you need a separate
 * pass to check whether the key is already in the table because of the need to update the
 * counts. Since there are many more searches than inserts in performance-critical
 * implementations, using this code for get() is justified; the corresponding change for
 * put() might not be noticed.
 *
 * 给定BST 中get() 和put()非递归实现.部分实现:如下是 get()的实现:
 * @formatter:off
 *     public Value get(Key key) {
 *         Node x = root;
 *         while (x != null) {
 *             int cmp = key.compareTo(x.key);
 *             if (cmp == 0) return x.val;
 *             else if (cmp < 0) x = x.left;
 *             else if (cmp > 0) x = x.right;
 *         }
 *         return null;
 *     }
 * @formatter:on
 * put() 的实现会更加复杂,因为需要保存一个父节点来链接到底部的新节点.同样的,你需要单独检查这个 key 是不是
 * 已经在表中,因为你需要更新计数.由于在性能标准视线中搜索的次数是远多于插入的次数,将这个代码用在 get()上是
 * 合理的;关于 put()的改变可能不会被注意到
 *
 *
 * @author LeonChen
 * @since 4/10/20
 */
class E3_2_13 {

    /**
     * @formatter:off
     * 官方实现如下
     * @formatter:on
     */
    public static void main(String[] args) {

    }


    /******************************************************************************
     *  Compilation:  javac NonrecursiveBST.java
     *  Execution:    java  NonrecursiveBST < input.txt
     *  Dependencies: StdOut.java StdIn.java
     *
     *  A symbol table implemented with a binary search tree using
     *  iteration instead of recursion for put(), get(), and keys().
     *
     *  % more tinyST.txt
     *  S E A R C H E X A M P L E
     *
     *  % java NonrecursiveBST < tinyST.txt
     *  A 8
     *  C 4
     *  E 12
     *  H 5
     *  L 11
     *  M 9
     *  P 10
     *  R 3
     *  S 0
     *  X 7
     *
     ******************************************************************************/

    public static class NonrecursiveBST<Key extends Comparable<Key>, Value> {

        // root of BST
        private Node root;

        private class Node {
            private Key key;             // sorted by key
            private Value val;           // associated value
            private Node left, right;    // left and right subtrees

            public Node(Key key, Value val) {
                this.key = key;
                this.val = val;
            }
        }


        /***************************************************************************
         *  Insert key-value pair into symbol table (nonrecursive version).
         ***************************************************************************/
        public void put(Key key, Value val) {
            Node z = new Node(key, val);
            if (root == null) {
                root = z;
                return;
            }

            Node parent = null, x = root;
            while (x != null) {
                parent = x;
                int cmp = key.compareTo(x.key);
                if (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
                else {
                    x.val = val;
                    return;
                }
            }
            int cmp = key.compareTo(parent.key);
            if (cmp < 0) parent.left = z;
            else parent.right = z;
        }


        /***************************************************************************
         *  Search BST for given key, nonrecursive version.
         ***************************************************************************/
        Value get(Key key) {
            Node x = root;
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
                else return x.val;
            }
            return null;
        }

        /***************************************************************************
         *  Inorder traversal.
         ***************************************************************************/
        public Iterable<Key> keys() {
            Stack<Node> stack = new Stack<Node>();
            Queue<Key> queue = new Queue<Key>();
            Node x = root;
            while (x != null || !stack.isEmpty()) {
                if (x != null) {
                    stack.push(x);
                    x = x.left;
                } else {
                    x = stack.pop();
                    queue.enqueue(x.key);
                    x = x.right;
                }
            }
            return queue;
        }


        /***************************************************************************
         *  Test client.
         ***************************************************************************/
        public static void main(String[] args) {
            String[] a = StdIn.readAllStrings();
            int n = a.length;
            NonrecursiveBST<String, Integer> st = new NonrecursiveBST<String, Integer>();
            for (int i = 0; i < n; i++)
                st.put(a[i], i);
            for (String s : st.keys())
                StdOut.println(s + " " + st.get(s));
        }

    }


}
