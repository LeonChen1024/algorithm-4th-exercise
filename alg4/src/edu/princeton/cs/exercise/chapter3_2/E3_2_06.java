package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.6 Add to BST a method height() that computes the height of the tree. Develop two
 * implementations: a recursive method (which takes linear time and space proportional
 * to the height), and a method like size() that adds a field to each node in the tree (and
 * takes linear space and constant time per query).
 * <p>
 * 添加一个方法 height() 到 BST 中,它计算了树的高度.开发两种实现:一种递归方法(与高度成比例的消耗线性时间
 * 和空间),还有一种方法像 size() 一样添加一个字段到每个树里的节点(并且在每次查询的时候消耗常量级的时间,但是
 * 使用了线性级的空间).
 *
 * @author LeonChen
 * @since 4/3/20
 */
class E3_2_06 {

    /**
     * @formatter:off
     * 官方的 BST 使用的是递归求取
     * public int height() {
     *         return height(root);
     *     }
     *     private int height(Node x) {
     *         if (x == null) return -1;
     *         return 1 + Math.max(height(x.left), height(x.right));
     *     }
     *
     * 如果要添加一个 height 属性的话,只要在每次插入和删除的时候更新即可
     *     x.height = 1 + Math.Max(height(x.Left), height(x.Right));
     */
    public static void main(String[] args) {
//        BST
    }


}
