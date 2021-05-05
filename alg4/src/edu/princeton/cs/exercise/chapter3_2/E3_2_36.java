package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 3.2.36 Iterator. Is it possible to write a nonrecursive version of keys() that uses space
 * proportional to the tree height (independent of the number of keys in the range)?
 * <p>
 * 迭代器.是否可能写出一个非递归版本的 keys() 使用的空间和树的高度成比例(与范围内的键数量无关)?
 *
 * @author LeonChen
 * @since 4/28/21
 */
class E3_2_36 {

    /**
     * @formatter:off
     * 从左子树开始找起,如果小于下限的时候就往右子树查找,将所有符合要求的节点添加到 stack 中.
     * 其中需要注意的是左右子树都查找过的节点要弹出 stack
     * @formatter:on
     */
    public static void main(String[] args) {
    }

    private static class nvKeysBST<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
        @Override
        public void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
            Stack<Node> stack = new Stack<Node>();

            while (x != null || stack.size() > 0) {
                if (x != null) {
                    int cmpLo = lo.compareTo(x.key);
                    int cmpHi = hi.compareTo(x.key);
                    if (cmpHi >= 0)
                        stack.push(x);
                    if (cmpLo < 0)
                        x = x.left;
                    else
                        x = null;
                } else {
                    x = stack.pop();
                    int cmpLo = lo.compareTo(x.key);
                    int cmpHi = hi.compareTo(x.key);
                    if (cmpLo <= 0 && cmpHi >= 0)
                        queue.enqueue(x.key);
                    x = x.right;
                }
            }
        }
    }

}
