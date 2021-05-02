package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.7 Add to BST a recursive method avgCompares() that computes the average number of
 * compares required by a random search hit in a given BST (the internal path length of
 * the tree divided by its size, plus one). Develop two implementations: a recursive
 * method (which takes linear time and space proportional to the height), and a method
 * like size() that adds a field to each node in the tree (and takes linear space and
 * constant time per query).
 * <p>
 * 向 BST 添加一个递归方法 avgCompares() 计算给定 BST 随机搜索命中需要多少次对比(树的大小除以内部路径长度
 * ,加 1).开发两种实现: 一种递归方法(消耗与高度成比例的线性时间和空间),还有一种像 size() 一样添加一个字段
 * 到每个节点(消耗线性空间和每次查找的常量级时间)
 *
 * @author LeonChen
 * @since 4/4/21
 */
class E3_2_07 {

    /**
     *
     * @formatter:off
     * 递归方法如下:
     * 计算左右节点深度加上 x 的节点大小减去本身1 个,因为根节点的出现会导致下面的节点深度+1
     *
     * public int avgCompares() {
     *         return depthSum(root) / size() + 1;
     *     }
     *
     *     private int depthSum(Node x) {
     *         if (x == null)
     *             return 0;
     *         return depthSum(x.left) + depthSum(x.right) + x.size - 1;
     *     }
     *
     *
     * 计数方法如下:
     * 在每次添加 和删除 的时候计数递归重新计算,
     *
     * private class Node {
     *         public int depthSum;
     *         private Key key;           // sorted by key
     *         private Value val;         // associated data
     *         private Node left, right;  // left and right subtrees
     *         private int size;          // number of nodes in subtree
     *
     *         public Node(Key key, Value val, int size) {
     *             this.key = key;
     *             this.val = val;
     *             this.size = size;
     *         }
     *     }
     *
     *
     *public void put(Key key, Value val) {
     *         if (key == null)
     *             throw new IllegalArgumentException("calls put() with a null key");
     *         if (val == null) {
     *             delete(key);
     *             return;
     *         }
     *         root = put(root, key, val, 0);
     *         assert check();
     *     }
     *
     *     private Node put(Node x, Key key, Value val, int depth) {
     *         if (x == null) return new Node(key, val, 1);
     *         int cmp = key.compareTo(x.key);
     *         if (cmp < 0) x.left = put(x.left, key, val, depth + 1);
     *         else if (cmp > 0) x.right = put(x.right, key, val, depth + 1);
     *         else x.val = val;
     *         x.size = 1 + size(x.left) + size(x.right);
     *         x.depthSum = depth;
     *         if (x.left != null) {
     *             x.depthSum += x.left.depthSum;
     *         }
     *         if (x.right != null) {
     *             x.depthSum += x.right.depthSum;
     *         }
     *         return x;
     *     }
     *
     *
     *
     *
     *     public void delete(Key key) {
     *         if (key == null)
     *             throw new IllegalArgumentException("calls delete() with a null key");
     *         root = delete(BST.Node, key, 0);
     *         assert check();
     *     }
     *     private Node delete(Node x, Key key, int depth) {
     *         if (x == null) return null;
     *
     *         int cmp = key.compareTo(x.key);
     *         if (cmp < 0) x.left = delete(x.left, key, depth + 1);
     *         else if (cmp > 0) x.right = delete(x.right, key, depth + 1);
     *         else {
     *             if (x.right == null) return x.left;
     *             if (x.left == null) return x.right;
     *             Node t = x;
     *             x = min(t.right);
     *             x.right = deleteMin(t.right);
     *             x.left = t.left;
     *         }
     *         x.size = size(x.left) + size(x.right) + 1;
     *         x.depthSum = depth;
     *         if (x.left != null) {
     *             x.depthSum += x.left.depthSum;
     *         }
     *         if (x.right != null) {
     *             x.depthSum += x.right.depthSum;
     *         }
     *         return x;
     *     }
     *
     */
    public static void main(String[] args) {
//        BST
    }


}
