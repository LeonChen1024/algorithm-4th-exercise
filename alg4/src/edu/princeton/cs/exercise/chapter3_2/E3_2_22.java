package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.22 Prove that if a node in a BST has two children, its successor has no left child and
 * its predecessor has no right child.
 * <p>
 * 证明 如果一个 BST 中的节点有两个子节点,他的后继没有左子节点冰倩他的前置没有右子节点
 *
 * @author LeonChen
 * @since 4/19/20
 */
class E3_2_22 {

    /**
     * @formatter:off
     * 因为前置不可能和该节点在某树的左右两个子树,前置会在该节点左子树的最右节点,所以前置的左子树不可能有
     * 右子节点. 后继同样不可能和该节点在某树的左右两个子树,后置会在该节点右子树的最左节点,所以后置不可能有
     * 左子节点
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
