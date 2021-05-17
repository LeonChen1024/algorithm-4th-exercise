package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.*;

import java.util.NoSuchElementException;

/**
 * 3.2.41 Array representation. Develop a BST implementation that represents the BST
 * with three arrays (preallocated to the maximum size given in the constructor): one with
 * the keys, one with array indices corresponding to left links, and one with array indices
 * corresponding to right links. Compare the performance of your program with that of
 * the standard implementation.
 * <p>
 * 数组表示.开发一个 BST 实现使用 3 个数组代表 BST(先在构造器中预分配数量最大值):一个数组放置键,一个数组指向
 * 对应的左链接索引,一个数组指向对应的右链接索引.对比你的程序和标准实现的性能.
 *
 * @author LeonChen
 * @since 5/2/21
 */
class E3_2_41 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        int[] ns = new int[]{(int) Math.pow(10, 4), (int) Math.pow(10, 5),
                (int) Math.pow(10, 6)};
        for (int n : ns) {
            BST<Integer, Integer> bst = new BST<>();
            ArrayBST<Integer, Integer> arrayBST = new ArrayBST<>(n);
            int k = 1;
            int[] arr = new int[n];
            for (int i = 1; i <= n; i++) {
                arr[i - 1] = k;
                k += 2;
            }
            StdRandom.shuffle(arr);
            Stopwatch stopwatch = new Stopwatch();
            for (int i : arr) {
                bst.put(i, 1);
            }
            double bstInitTime = stopwatch.elapsedTime();
            stopwatch = new Stopwatch();
            for (int i : arr) {
                bst.put(i, 1);
            }
            double arrBstInitTime = stopwatch.elapsedTime();

            // 命中测试
            stopwatch = new Stopwatch();
            for (int i = 0; i < n; i++) {
                int uniform = StdRandom.uniform(n) * 2 + 1;
                bst.get(uniform);
            }
            double avgHitTime = stopwatch.elapsedTime() / n;
            stopwatch = new Stopwatch();
            for (int i = 0; i < n; i++) {
                int uniform = StdRandom.uniform(n) * 2 + 1;
                arrayBST.get(uniform);
            }
            double arrAvgHitTime = stopwatch.elapsedTime() / n;

            // 丢失测试
            stopwatch = new Stopwatch();
            for (int i = 0; i < n; i++) {
                int uniform = StdRandom.uniform(n) * 2 + 1;
                bst.get(uniform);
            }
            double avgMissTime = stopwatch.elapsedTime() / n;
            stopwatch = new Stopwatch();
            for (int i = 0; i < n; i++) {
                int uniform = StdRandom.uniform(n) * 2 + 1;
                arrayBST.get(uniform);
            }
            double arrAvgMissTime = stopwatch.elapsedTime() / n;
            StdOut.println("bstInitTime = " + bstInitTime + " , arrBstInitTime = " +
                    arrBstInitTime + " , avgHitTime = " + avgHitTime + " , " +
                    "arrAvgHitTime = " + arrAvgHitTime + " , " +
                    "avgMissTime =" +
                    avgMissTime + " , arrAvgMissTime = " + arrAvgMissTime);
        }
    }


    private static class ArrayBST<Key extends Comparable<Key>, Value> {
        private Key[] keys;
        private Value[] values;
        private int[] leftLinks;
        private int[] rightLinks;
        private int[] size;

        ArrayBST(int size) {
            keys = (Key[]) new Comparable[size];
            values = (Value[]) new Object[size];
            leftLinks = new int[size];
            rightLinks = new int[size];
            this.size = new int[size];

            for (int i = 0; i < size; i++) {
                leftLinks[i] = -1;
                rightLinks[i] = -1;
            }
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        private int size() {
            return size(0);
        }

        private int size(int i) {
            return size[i];
        }

        public boolean contains(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }

        public Value get(Key key) {
            return get(0, key);
        }

        private Value get(int i, Key key) {
            if (key == null)
                throw new IllegalArgumentException("calls get() with a null key");
            if (i == -1 || keys[i] == null) {
                return null;
            }
            int cmp = key.compareTo(keys[i]);
            if (cmp < 0) return get(leftLinks[i], key);
            else if (cmp > 0) return get(rightLinks[i], key);
            else return values[i];
        }

        public void put(Key key, Value val) {
            if (key == null)
                throw new IllegalArgumentException("calls put() with a null key");
            if (val == null) {
                delete(key);
                return;
            }
            put(0, key, val);
            assert check();
        }

        public void delete(Key key) {
            if (key == null)
                throw new IllegalArgumentException("calls delete() with a null key");
            delete(0, key, 0);
            assert check();
        }

        private int delete(int i, Key key, int depth) {
            if (i >= keys.length) return -1;


            int cmp = key.compareTo(keys[i]);
            if (cmp < 0) leftLinks[i] = delete(leftLinks[i], key, depth + 1);
            else if (cmp > 0) rightLinks[i] = delete(rightLinks[i], key, depth + 1);
            else {
                keys[i] = null;
                values[i] = null;
                size[i] = 0;

                if (rightLinks[i] == -1) {
                    int temp = leftLinks[i];
                    leftLinks[i] = -1;
                    return temp;
                } else if (leftLinks[i] == -1) {
                    int temp = rightLinks[i];
                    rightLinks[i] = -1;
                    return temp;
                } else {
                    int rightMin = min(rightLinks[i]);
                    rightLinks[i] = deleteMin(rightLinks[i], false);
                    leftLinks[rightMin] = leftLinks[i];
                    rightLinks[i] = -1;
                    leftLinks[i] = -1;
                    i = rightMin;
                }
            }
            return i;
        }

        public void deleteMin() {
            int rootIndex = deleteMin(0, true);

            if (rootIndex == -1) {
                clearIndex(0);
                return;
            }

            if (rootIndex != 0) {
                keys[0] = keys[rootIndex];
                values[0] = values[rootIndex];
                size[0] = size[rootIndex];
                leftLinks[0] = leftLinks[rootIndex];
                rightLinks[0] = rightLinks[rootIndex];
                clearIndex(rootIndex);
            }
        }

        private void clearIndex(int index) {
            keys[index] = null;
            values[index] = null;
            size[index] = 0;
            leftLinks[index] = -1;
            rightLinks[index] = -1;
        }

        private int deleteMin(int index, boolean setKeyNull) {
            if (index == -1 || keys[index] == null) {
                return -1;
            }

            if (leftLinks[index] == -1) {
                int rightKeyLink = rightLinks[index];
                if (setKeyNull) {
                    clearIndex(index);
                }

                return rightKeyLink;
            }

            int leftIndex = deleteMin(leftLinks[index], setKeyNull);
            leftLinks[index] = leftIndex;

            size[index] = size(leftLinks[index]) + 1 + size(rightLinks[index]);
            return index;
        }

        public Key min() {
            if (size() == 0) {
                throw new NoSuchElementException("Empty binary search tree");
            }

            int minIndex = min(0);
            return keys[minIndex];
        }

        private int min(int index) {
            if (leftLinks[index] == -1) {
                return index;
            }

            return min(leftLinks[index]);
        }

        private int put(int i, Key key, Value val) {
            if (i == -1 || keys[i] == null) {
                int nextIndex = size();
                keys[nextIndex] = key;
                values[nextIndex] = val;
                size[nextIndex] = 1;
                return nextIndex;
            }
            int cmp = key.compareTo(keys[i]);
            if (cmp < 0) leftLinks[i] = put(leftLinks[i], key, val);
            else if (cmp > 0) rightLinks[i] = put(rightLinks[i], key, val);
            else values[i] = val;

            size[i] = size(leftLinks[i]) + size(rightLinks[i]) + 1;

            return i;
        }


        private boolean check() {
            if (!isBST()) StdOut.println("Not in symmetric order");
            if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
            if (!isRankConsistent()) StdOut.println("Ranks not consistent");
            return isBST() && isSizeConsistent() && isRankConsistent();
        }

        // does this binary tree satisfy symmetric order?
        // Note: this test also ensures that data structure is a binary tree since order is strict
        private boolean isBST() {
            return isBST(0, null, null);
        }

        // is the tree rooted at x a BST with all keys strictly between min and max
        // (if min or max is null, treat as empty constraint)
        // Credit: Bob Dondero's elegant solution
        private boolean isBST(int i, Key min, Key max) {
            if (keys[i] == null) return true;
            if (min != null && keys[i].compareTo(min) <= 0) return false;
            if (max != null && keys[i].compareTo(max) >= 0) return false;
            return isBST(leftLinks[i], min, keys[i]) && isBST(rightLinks[i], keys[i],
                    max);
        }

        // are the size fields correct?
        private boolean isSizeConsistent() {
            return isSizeConsistent(0);
        }

        private boolean isSizeConsistent(int i) {
            if (keys[i] == null) return true;
            if (size(i) != size(leftLinks[i]) + size(rightLinks[i]) + 1) return false;
            return isSizeConsistent(leftLinks[i]) && isSizeConsistent(rightLinks[i]);
        }

        // check that ranks are consistent
        private boolean isRankConsistent() {
            for (int i = 0; i < size(); i++)
                if (i != rank(select(i))) return false;
            for (Key key : keys())
                if (key.compareTo(select(rank(key))) != 0) return false;
            return true;
        }

        public Iterable<Key> keys() {
            return keys(min(), max());
        }

        public Key max() {
            if (size() == 0) {
                throw new NoSuchElementException("Empty binary search tree");
            }

            int maxIndex = max(0);
            return keys[maxIndex];
        }

        private int max(int index) {
            if (rightLinks[index] == -1) {
                return index;
            }

            return max(rightLinks[index]);
        }

        public Iterable<Key> keys(Key low, Key high) {
            Queue<Key> queue = new Queue<>();
            keys(0, queue, low, high);
            return queue;
        }

        private void keys(int index, Queue<Key> queue, Key low, Key high) {
            if (index == -1 || keys[index] == null) {
                return;
            }

            int compareLow = low.compareTo(keys[index]);
            int compareHigh = high.compareTo(keys[index]);

            if (compareLow < 0) {
                keys(leftLinks[index], queue, low, high);
            }

            if (compareLow <= 0 && compareHigh >= 0) {
                queue.enqueue(keys[index]);
            }

            if (compareHigh > 0) {
                keys(rightLinks[index], queue, low, high);
            }
        }

        public Key select(int index) {
            if (index >= size()) {
                throw new IllegalArgumentException("Index is higher than tree size");
            }

            int keyIndex = select(0, index);
            return keys[keyIndex];
        }

        private int select(int keyIndex, int index) {
            int leftSubtreeSize = size[leftLinks[keyIndex]];

            if (leftSubtreeSize == index) {
                return keyIndex;
            } else if (leftSubtreeSize > index) {
                return select(leftLinks[keyIndex], index);
            } else {
                return select(rightLinks[keyIndex], index - leftSubtreeSize - 1);
            }
        }

        public int rank(Key key) {
            return rank(0, key);
        }

        private int rank(int index, Key key) {
            if (index == -1 || keys[index] == null) {
                return 0;
            }

            //Returns the number of keys less than keys[index] in the subtree rooted at node
            int compare = key.compareTo(keys[index]);
            if (compare < 0) {
                return rank(leftLinks[index], key);
            } else if (compare > 0) {
                return size(leftLinks[index]) + 1 + rank(rightLinks[index], key);
            } else {
                return size[leftLinks[index]];
            }
        }
    }

}
