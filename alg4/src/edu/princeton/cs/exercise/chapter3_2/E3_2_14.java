package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.NoSuchElementException;

/**
 * 3.2.14 Give nonrecursive implementations of min(), max(), floor(), ceiling(),
 * rank(), and select().
 * <p>
 * 给出非递归的 min(), max(), floor(), ceiling(),rank(), 和 select()的实现
 *
 * @author LeonChen
 * @since 4/11/20
 */
class E3_2_14 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
    }


    public static class NonrecursiveBST<Key extends Comparable<Key>, Value>
            extends BST<Key, Value> {

        public Key min() {
            if (root == null) {
                throw new NoSuchElementException("Empty binary search tree");
            }

            Node current = root;

            while (current != null) {
                if (current.left == null) {
                    return current.key;
                } else {
                    current = current.left;
                }
            }

            return null;
        }

        public Key max() {
            if (root == null) {
                throw new NoSuchElementException("Empty binary search tree");
            }

            Node current = root;

            while (current != null) {
                if (current.right == null) {
                    return current.key;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        public Key floor(Key key) {

            Node current = root;
            Key currentFloor = null;

            while (current != null) {
                int compare = key.compareTo(current.key);

                if (compare < 0) {
                    current = current.left;
                } else if (compare > 0) {
                    currentFloor = current.key;
                    current = current.right;
                } else {
                    currentFloor = current.key;
                    break;
                }
            }

            return currentFloor;
        }

        public Key ceiling(Key key) {

            Node current = root;
            Key currentCeiling = null;

            while (current != null) {
                int compare = key.compareTo(current.key);

                if (compare < 0) {
                    currentCeiling = current.key;
                    current = current.left;
                } else if (compare > 0) {
                    current = current.right;
                } else {
                    currentCeiling = current.key;
                    break;
                }
            }

            return currentCeiling;
        }

        public Key select(int index) {
            if (index >= size()) {
                throw new IllegalArgumentException("Index is higher than tree size");
            }

            Node current = root;

            while (current != null) {
                int leftSubtreeSize = size(current.left);

                if (leftSubtreeSize == index) {
                    return current.key;
                } else if (leftSubtreeSize > index) {
                    current = current.left;
                } else {
                    index -= (leftSubtreeSize + 1);
                    current = current.right;
                }

            }

            return null;
        }

        public int rank(Key key) {
            Node current = root;

            int rank = 0;

            while (current != null) {
                int compare = key.compareTo(current.key);

                if (compare < 0) {
                    current = current.left;
                } else if (compare > 0) {
                    rank += size(current.left) + 1;
                    current = current.right;
                } else {
                    rank += size(current.left);
                    return rank;
                }
            }

            return rank;
        }

        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<>();

            Stack<Node> stack = new Stack<>();

            Node current = root;

            while (current != null || !stack.isEmpty()) {
                if (current != null) {
                    stack.push(current);
                    current = current.left;
                } else {
                    current = stack.pop();
                    queue.enqueue(current.key);

                    current = current.right;
                }
            }

            return queue;
        }
    }


}
