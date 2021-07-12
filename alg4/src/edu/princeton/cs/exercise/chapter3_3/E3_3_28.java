package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;

/**
 * 3.3.28 Bottom-up 2-3-4 trees. Develop an implementation of the basic symbol-table
 * API that uses balanced 2-3-4 trees as the underlying data structure, using the red-black
 * representation and a bottom-up insertion method based on the same recursive approach
 * as Algorithm3.4.Your insertion method should split only the sequence of 4-nodes(if
 * any) on the bottom of the search path.
 * <p>
 * 自底向上的 2-3-4 树.开发一个基础符号表 API 实现使用平衡 2-3-4 树作为底层数据结构,使用红黑树表示自底向上
 * 的插入结果使用和 算法3.4 中同样的递归方式.你的插入方法应该只分割 搜索路径底部的4-节点
 *
 * @author LeonChen
 * @since 6/30/21
 */
class E3_3_28 {

    /**
     * @formatter:off
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }

    private static class Bu234BST<Key extends Comparable<Key>, Value> extends RedBlackBST<Key, Value> {
        @Override
        protected RedBlackBST<Key, Value>.Node put(RedBlackBST<Key, Value>.Node h, Key key, Value val) {
            if (h == null) return new Node(key, val, RED, 1);

            int cmp = key.compareTo(h.key);
            if (cmp < 0) h.left = put(h.left, key, val);
            else if (cmp > 0) h.right = put(h.right, key, val);
            else h.val = val;

            // fix-up any right-leaning links
            if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
            if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
            //Splitting only the sequence of 4-nodes (if any) on the bottom of the search path
            if ((h.left != null && h.left.key.compareTo(key) == 0)
                    || (h.right != null && h.right.key.compareTo(key) == 0)) {
                if (isRed(h.left) && isRed(h.right)) {
                    flipColors(h);
                }
            }
            h.size = size(h.left) + size(h.right) + 1;

            return h;
        }
    }


}
