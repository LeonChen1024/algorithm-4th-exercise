package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 3.2.42 Hibbard deletion degradation. Write a program that takes an integer N from the
 * command line, builds a random BST of size N, then enters into a loop where it deletes
 * a random key (using the code delete(select(StdRandom.uniform(N)))) and then
 * inserts a random key, iterating the loop N^2 times. After the loop, measure and
 * print the average length of a path in the tree (the internal path length divided by
 * N, plus 1). Run your program for N = 10^2, 10^3, and 10^4 to test the somewhat
 * counterintuitive hypothesis that this process increases the average path length of
 * the tree to be proportional to the square root of N. Run the same experiments for a
 * delete() implementation that makes a random choice whether to use the predecessor or
 * the successor node.
 * <p>
 * Hibbard 删除降级.编写一个程序从命令行接收一个整数 N,构建一个大小为N 的随机 BST,然后进入一个循环,它删除
 * 一个随机键(使用代码 delete(select(StdRandom.uniform(N))) ) 并且接着插入一个随机键,然后执行 N^2
 * 次循环.之后,测量并打印出树中路径的平均长度(内部路径长度除以 N,然后加上 1).运行你的程序使 N = 10^2,
 * 10^3, 和 10^4 来测试这个违反直觉的理论,它表示这个处理会增加树的平均路径长度会和 N 的平方根成比例.
 * 对delete() 实现运行相同的试验让它随机选择使用前驱或者后缀节点
 *
 * @author LeonChen
 * @since 5/7/21
 */
class E3_2_42 {

    /**
     * @formatter:off
     * =============== when n = 100 ====================
     * bst initial avgHeight = 4
     * rdbst initial avgHeight = 4
     * bst after operation avgHeight = 3
     * rdBst after operation avgHeight = 3
     * =============== when n = 1000 ====================
     * bst initial avgHeight = 7
     * rdbst initial avgHeight = 7
     * bst after operation avgHeight = 10
     * rdBst after operation avgHeight = 6
     * =============== when n = 10000 ====================
     * bst initial avgHeight = 9
     * rdbst initial avgHeight = 9
     * bst after operation avgHeight = 23
     * rdBst after operation avgHeight = 9
     * @formatter:on
     */
    public static void main(String[] args) {
        int[] ns = new int[]{(int) Math.pow(10, 2), (int) Math.pow(10, 3),
                (int) Math.pow(10, 4)};
        for (int n : ns) {
            StdOut.println("=============== when n = " + n + " ====================");
            BST<Integer, Integer> bst = new BST<>();
            RdBst<Integer, Integer> rdBst = new RdBst<>();
            for (int i = 0; i < n; i++) {
                int uniform = StdRandom.uniform(Integer.MAX_VALUE);
                bst.put(uniform, 1);
                rdBst.put(uniform, 1);
            }
            StdOut.println("bst initial avgHeight = " + bst.root.depthSum / n);
            StdOut.println("rdbst initial avgHeight = " + rdBst.root.depthSum / n);

            for (int i = 0; i < Math.pow(n, 2); i++) {
                int uniform = StdRandom.uniform(bst.size());
                bst.delete(bst.select(uniform));
                rdBst.delete(rdBst.select(StdRandom.uniform(rdBst.size())));

                uniform = StdRandom.uniform(Integer.MAX_VALUE);
                bst.put(uniform, 1);
                rdBst.put(uniform, 1);
            }

            StdOut.println("bst after operation avgHeight = " + bst.root.depthSum / n);
            StdOut.println("rdBst after operation avgHeight = " + rdBst.root.depthSum / n);

        }
    }


    private static class RdBst<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
        @Override
        public void delete(Key key) {
            if (key == null)
                throw new IllegalArgumentException("calls delete() with a null key");
            root = delete(root, key, 0);
        }

        private Node delete(Node x, Key key, int depth) {
            if (x == null) return null;

            int cmp = key.compareTo(x.key);
            if (cmp < 0) x.left = delete(x.left, key, depth + 1);
            else if (cmp > 0) x.right = delete(x.right, key, depth + 1);
            else {
                if (x.right == null) return x.left;
                if (x.left == null) return x.right;
                Node t = x;
                if (StdRandom.uniform() > 0.5) {
                    x = max(t.left);
                    x.left = deleteMax(t.left);
                    x.right = t.right;
                } else {
                    x = min(t.right);
                    x.right = deleteMin(t.right);
                    x.left = t.left;
                }
            }
            x.size = size(x.left) + size(x.right) + 1;
            x.depthSum = depth;
            if (x.left != null) {
                x.depthSum += x.left.depthSum;
            }
            if (x.right != null) {
                x.depthSum += x.right.depthSum;
            }
            return x;
        }
    }
}
