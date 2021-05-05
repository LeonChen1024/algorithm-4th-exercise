package edu.princeton.cs.exercise.chapter3_2;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.2.37 Level-order traversal. Write a method printLevel() that takes a Node as argument
 * and prints the keys in the subtree rooted at that node in level order (in order of
 * their distance from the root, with nodes on each level in order from left to right).
 * Hint : Use a Queue.
 * <p>
 * 层序遍历. 编写一个方法 printLevel() 接收一个 Node 作为参数并且按照层序打印出这个节点的子树的键(也就是
 * 按照他们到根节点的距离顺序,每一层的顺序则是按照从左到右).
 * 提示: 使用一个 Queue.
 *
 * @author LeonChen
 * @since 4/29/21
 */
class E3_2_37 {

    /**
     * @formatter:off
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }

    private static class LevelBST<Key extends Comparable<Key>, Value> extends BST<Key,
            Value> {
        private void printLevel(Node node) {
            Queue<Node> queue = new Queue<>();
            queue.enqueue(node);

            while (!queue.isEmpty()) {
                Node current = queue.dequeue();
                StdOut.print(current.key + " ");

                if (current.left != null) {
                    queue.enqueue(current.left);
                }
                if (current.right != null) {
                    queue.enqueue(current.right);
                }
            }
        }
    }

}
