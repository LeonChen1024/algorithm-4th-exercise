package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.40 Delete the maximum. Implement the deleteMax() operation for red-black
 * BSTs. Note that the transformations involved differ slightly from those in the previous
 * exercise because red links are left-leaning.
 *
 * Solution:
 * @formatter:off
 *     private Node moveRedRight(Node h) {
 *         // Assuming that h is red and both h.right and h.right.left
 *         // are black, make h.right or one of its children red.
 *         flipColors(h)
 *         if (!isRed(h.left.left))
 *             h = rotateRight(h);
 *         return h;
 *     }
 *
 *     public void deleteMax() {
 *         if (!isRed(root.left) && !isRed(root.right))
 *             root.color = RED;
 *         root = deleteMax(root);
 *         if (!isEmpty()) root.color = BLACK;
 *     }
 *     private Node deleteMax(Node h)
 *     {
 *         if (isRed(h.left))
 *             h = rotateRight(h);
 *         if (h.right == null)
 *             return null;
 *         if (!isRed(h.right) && !isRed(h.right.left))
 *             h = moveRedRight(h);
 *         h.right = deleteMax(h.right);
 *         return balance(h);
 *     }
 * @formatter:on
 * <p>
 * 删除最大值.给红黑树实现一个 deleteMax() 操作.注意转换和之前的练习有些不同,因为红色链接是在左边的
 *
 * @author LeonChen
 * @since 7/9/21
 */
class E3_3_40 {


    /**
     * @formatter:off
     *  RedBlackBST 中已有解答
     * @formatter:on
     */
    public static void main(String[] args) {
//        RedBlackBST

    }


}
