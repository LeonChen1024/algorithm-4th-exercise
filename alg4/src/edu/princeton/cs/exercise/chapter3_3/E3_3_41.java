package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.41 Delete. Implement the delete() operation for red-black BSTs, combining the
 * methods of the previous two exercises with the delete() operation for BSTs.
 * Solution :
 * @formatter:off
 *  public void delete(Key key) {
 *         if (!isRed(root.left) && !isRed(root.right))
 *             root.color = RED;
 *         root = delete(root, key);
 *         if (!isEmpty()) root.color = BLACK;
 *     }
 *
 *     private Node delete(Node h, Key key) {
 *         if (key.compareTo(h.key) < 0) {
 *             if (!isRed(h.left) && !isRed(h.left.left))
 *                 h = moveRedLeft(h);
 *             h.left = delete(h.left, key);
 *         } else {
 *             if (isRed(h.left))
 *                 h = rotateRight(h);
 *             if (key.compareTo(h.key) == 0 && (h.right == null))
 *                 return null;
 *             if (!isRed(h.right) && !isRed(h.right.left))
 *                 h = moveRedRight(h);
 *             if (key.compareTo(h.key) == 0) {
 *                 h.val = get(h.right, min(h.right).key);
 *                 h.key = min(h.right).key;
 *                 h.right = deleteMin(h.right);
 *             } else h.right = delete(h.right, key);
 *         }
 *         return balance(h);
 *     }
 * @formatter:on
 *
 * <p>
 * 删除.给红黑树实现一个 delete() 操作,结合前面两个练习到删除方法中
 *
 * @author LeonChen
 * @since 7/10/21
 */
class E3_3_41 {

    /**
     * @formatter:off
     *  RedBlackBST 中已有解答
     * @formatter:on
     */
    public static void main(String[] args) {
//        RedBlackBST

    }


}
