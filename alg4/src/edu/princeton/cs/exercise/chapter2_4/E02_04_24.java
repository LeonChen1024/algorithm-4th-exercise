package edu.princeton.cs.exercise.chapter2_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.4.24 Priority queue with explicit links. Implement a priority queue using a heap-ordered binary
 * tree, but use a triply linked structure instead of an array. You will need three links per node:
 * two to traverse down the tree and one to traverse up the tree. Your implementation should
 * guarantee logarithmic running time per operation, even if no maximum priority-queue size is known
 * ahead of time.
 *
 * <p>使用链表实现优先队列. 使用堆排序二叉树实现一个优先队列,但是通过3链表结构而不是数组.每个节点你需要3条链:<br>
 * 两条向下遍历树还有一条向上遍历.你的实现要确保每个操作的时间复杂度是对数级别的,甚至是在最大优先队列的大小不知道的情况下
 *
 * @author LeonChen
 * @since 10/15/20
 */
class E02_04_24 {

  /**
   * <code>
   * 因为要求在事先大小不知道的情况下也要保持时间复杂度,所以不能使用大小来进行索引位置.交换节点的时候可以直接交换值就
   * 可以了.
   * 当无法使用大小来定位的时候如何找到插入时目前要插入的位置和删除后如何确定最后的节点的上一个位置?
   * 找下一个节点的位置可以分为几种情况,
   * 当前最后一个节点a是节点b的左子节点时,新加的节点应该是在b节点的右子节点.
   * 当前最后一个节点a是节点b的右子节点时,新加的节点应该往上追朔直到该节点是某个节点c的左子节点的时候,新加的节点就是
   * c的右子节点不断往左子节点延伸到叶子节点后的左子节点位置.如果已经上朔到根结点,那么就从根结点的不断往左子节点下朔
   * 到叶子节点的左子节点位置.
   * 找上一个节点的位置与上述类似,只需要将将步骤中的左右节点的目标交换一下即可.
   * 插入和删除最大复杂度都是 2logN 向上回朔到根再到叶子节点.
   * </code>
   *
   * @param <Key>
   */
  public class MaxPQLinked<Key extends Comparable> {
    /** 二叉堆的根结点。 */
    private TreeNode<Key> root = null;
    /** 二叉堆的最后一个结点。 */
    private TreeNode<Key> last = null;

    private int size = 0;

    public MaxPQLinked() {}

    /** 删除并返回最大值。 */
    public Key delMax() {
      Key result = root.value;
      exch(root, last);

      if (last.parent == root) {
        root.left = null;
        last = root;
        size--;
        return result;
      } else if (last == root) {
        last = null;
        root = null;
        size--;
        return result;
      }

      // 获得前一个结点。
      TreeNode<Key> newLast = last;
      if (newLast == last.parent.right) newLast = last.parent.left;
      else {
        // 找到上一棵子树。
        while (newLast != root) {
          // 该结点是右子树的时候,那么新的最后节点就是对应的左子树的最右节点
          if (newLast != newLast.parent.left) break;
          newLast = newLast.parent;
        }

        // 已经是满二叉树。
        if (newLast == root) {
          // 一路向右，回到上一层。
          while (newLast.right != null) newLast = newLast.right;
        }
        // 不是满二叉树。
        else {
          // 向左子树移动，再一路向右。
          newLast = newLast.parent.left;
          while (newLast.right != null) newLast = newLast.right;
        }
      }

      // 删除最后一个结点。
      if (last.parent.left == last) last.parent.left = null;
      else last.parent.right = null;

      sink(root);

      // 指向新的最后一个结点。
      last = newLast;
      size--;
      return result;
    }

    /**
     * 插入一个新的结点
     *
     * @param v 待插入的结点
     */
    public void insert(Key v) {
      TreeNode<Key> item = new TreeNode<Key>(v);
      // 堆为空
      if (last == null) {
        root = item;
        last = item;
        size++;
        return;
      }

      // 堆只有一个结点
      if (last == root) {
        item.parent = root;
        root.left = item;
        last = item;
        size++;
        swim(item);
        return;
      }

      // 定位到最后一个节点的父结点
      TreeNode<Key> prev = last.parent;

      // 右子节点为空，插入到右子节点
      if (prev.right == null) {
        item.parent = prev;
        prev.right = item;
      } else {
        // 当前子树已满，需要向上回溯
        // 找到下一个子树（回溯的时候是从左子树回溯上去的）
        while (prev != root) {
          if (prev != prev.parent.right) break;
          prev = prev.parent;
        }

        // 已经是满二叉树
        if (prev == root) {
          // 一路向左，进入下一层
          while (prev.left != null) prev = prev.left;

          item.parent = prev;
          prev.left = item;
        }
        // 不是满二叉树
        else {
          // 向右子树移动，再一路向左
          prev = prev.parent.right;
          while (prev.left != null) prev = prev.left;

          item.parent = prev;
          prev.left = item;
        }
      }

      last = item;
      size++;
      swim(item);
      return;
    }

    public boolean isEmpty() {
      return root == null;
    }

    public Key getMax() {
      return root.value;
    }

    public int size() {
      return size;
    }

    /**
     * 使结点上浮
     *
     * @param k 需要上浮的结点
     */
    private void swim(TreeNode<Key> k) {
      while (k.parent != null) {
        if (less(k.parent, k)) {
          exch(k.parent, k);
          k = k.parent;
        } else break;
      }
    }

    /**
     * 使结点下沉
     *
     * @param k 需要下沉的结点
     */
    private void sink(TreeNode<Key> k) {
      while (k.left != null || k.right != null) {
        TreeNode<Key> toExch = null;
        if (k.left != null && k.right != null) toExch = less(k.left, k.right) ? k.right : k.left;
        else if (k.left != null) toExch = k.left;
        else toExch = k.right;

        if (less(k, toExch)) exch(k, toExch);
        else break;
        k = toExch;
      }
    }

    /** 交换二叉堆中的两个结点 */
    private void exch(TreeNode<Key> a, TreeNode<Key> b) {
      Key temp = a.value;
      a.value = b.value;
      b.value = temp;
    }

    /**
     * 比较第一个结点值的是否小于第二个
     *
     * @param a
     * @param b
     * @return
     */
    private boolean less(TreeNode<Key> a, TreeNode<Key> b) {
      return a.value.compareTo(b.value) < 0;
    }
  }

  public static void main(String[] args) {
    E02_04_24.MaxPQLinked pq = new E02_04_24().new MaxPQLinked();

    StdOut.println("isEmpty: " + pq.isEmpty() + " Expected: true");

    pq.insert(10);
    pq.insert(2);
    pq.insert(7);
    pq.insert(20);
    pq.insert(21);
    pq.insert(10);
    pq.insert(44);
    pq.insert(30);
    pq.insert(25);

    StdOut.println("Size: " + pq.size() + " Expected: 4");
    StdOut.println("isEmpty: " + pq.isEmpty() + " Expected: false");

    StdOut.println("Item removed: " + pq.delMax());
    StdOut.println("Item removed: " + pq.delMax());
    StdOut.println("Item removed: " + pq.delMax());
    StdOut.println("Item removed: " + pq.delMax());
  }

  private class TreeNode<Key> {
    TreeNode<Key> left;
    TreeNode<Key> right;
    TreeNode<Key> parent;

    Key value;

    public TreeNode(Key v) {
      value = v;
    }
  }
}
