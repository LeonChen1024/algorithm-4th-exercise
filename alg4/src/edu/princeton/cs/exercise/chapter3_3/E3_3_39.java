package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.3.39 Delete the minimum. Implement the deleteMin() operation for red-black
 * BSTs by maintaining the correspondence with the transformations given in the text for
 * moving down the left spine of the tree while maintaining the invariant that the current
 * node is not a 2-node.
 * Solution:
 * @formatter:off
 *     private Node moveRedLeft(Node h) {
 *         // Assuming that h is red and both h.left and h.left.left
 *         // are black, make h.left or one of its children red.
 *         flipColors(h);
 *         if (isRed(h.right.left)) {
 *             h.right = rotateRight(h.right);
 *             h = rotateLeft(h);
 *         }
 *         return h;
 *     }
 *
 *     public void deleteMin() {
 *         if (!isRed(root.left) && !isRed(root.right))
 *             root.color = RED;
 *         root = deleteMin(root);
 *         if (!isEmpty()) root.color = BLACK;
 *     }
 *
 *     private Node deleteMin(Node h) {
 *         if (h.left == null)
 *             return null;
 *         if (!isRed(h.left) && !isRed(h.left.left))
 *             h = moveRedLeft(h);
 *         h.left = deleteMin(h.left);
 *         return balance(h);
 *     }
 * @formatter:on
 *
 * This code assumes a balance() method that consists of the line of code
 *
 * if (isRed(h.right)) h = rotateLeft(h);
 *
 * followed by the last five lines of the recursive put() in Algorithm 3.4 and a
 * flipColors() implementation that complements the three colors, instead of the
 * method given in the text for insertion. For deletion, we set the parent to BLACK and the
 * two children to RED.
 *
 * 删除最小值.对红黑树实现一个 deleteMin() 操作通过正文中的转换保证左子树的要求并且确保当前节点不是一个 2-节点
 *
 * 这段代码假设一个 balance() 方法由代码
 * if (isRed(h.right)) h = rotateLeft(h);
 * 组成.上面是3.4 章节中 put() 方法的最后 5 行并且 flipColors()实现完整了 3 个颜色,而不是使用正文中的
 * 插入的翻转.对于删除来说,我们将父节点设置为 BLACK 两个子节点设置为 RED
 *
 * @author LeonChen
 * @since 7/8/21
 */
class E3_3_39 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        RedBlackBSTDeleteMin<Integer, Integer> redBlackBST = new RedBlackBSTDeleteMin<>();

        redBlackBST.put(10, 10);
        redBlackBST.put(4, 4);
        redBlackBST.put(6, 6);
        redBlackBST.put(1, 1);
        redBlackBST.put(2, 2);
        redBlackBST.put(15, 15);
        redBlackBST.put(12, 12);

        while (!redBlackBST.isEmpty()) {

            for (Integer key : redBlackBST.keys()) {
                StdOut.println(key);
            }

            StdOut.println();

            StdOut.println("Delete min");
            redBlackBST.deleteMin();
        }

    }

    private static class RedBlackBSTDeleteMin<Key extends Comparable<Key>, Value> extends RedBlackBST<Key, Value> {

        public void deleteMin() {
            if (isEmpty()) {
                return;
            }

            if (!isRed(root.left) && !isRed(root.right)) {
                root.color = RED;
            }

            root = deleteMin(root);

            if (!isEmpty()) {
                root.color = BLACK;
            }
        }

        protected Node deleteMin(Node node) {
            if (node.left == null) {
                return null;
            }

            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }

            node.left = deleteMin(node.left);
            return balance(node);
        }

        protected Node moveRedLeft(Node node) {
            //Assuming that node is red and both node.left and node.left.left are black,
            // make node.left or one of its children red
            flipColors(node);

            if (node.right != null && isRed(node.right.left)) {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
                flipColors(node);
            }

            return node;
        }

        protected Node balance(Node node) {
            if (node == null) {
                return null;
            }

            if (isRed(node.right)) {
                node = rotateLeft(node);
            }

            if (isRed(node.left) && node.left != null && isRed(node.left.left)) {
                node = rotateRight(node);
            }

            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }

            node.size = size(node.left) + 1 + size(node.right);

            return node;
        }

        protected void flipColors(Node node) {
            if (node != null) {
                node.color = !node.color;

                if (node.left != null) {
                    node.left.color = !node.left.color;
                }
                if (node.right != null) {
                    node.right.color = !node.right.color;
                }
            }
        }

    }


}
