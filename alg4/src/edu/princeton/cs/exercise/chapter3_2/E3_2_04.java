package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.4 Suppose that a certain BST has keys that are integers between 1 and 10, and we
 * search for 5. Which sequence below cannot be the sequence of keys examined?
 * a.10,9,8,7,6,5
 * b.4,10,8,7,53
 * c.1,10,2,9,3,8,4,7,6,5
 * d.2,7,3,8,4,5
 * e.1,2,10,4,8,5
 * <p>
 * 假设一个 BST 拥有键是整数在 1 到 10 之间,并且我们查找 5.下面哪个序列不可能是检查的键?
 * a.10,9,8,7,6,5
 * b.4,10,8,7,53
 * c.1,10,2,9,3,8,4,7,6,5
 * d.2,7,3,8,4,5
 * e.1,2,10,4,8,5
 *
 * @author LeonChen
 * @since 4/1/21
 */
class E3_2_04 {

    /**
     * @formatter:off
     * d 是错误的.
     * 根据左右子树的规律可以知道,如果你抄左子树查找,那么接下来查找的节点一定小于根节点.反之则大于.
     * d 8 这个节点是不可能出现在 7 的左子树的
     */
    public static void main(String[] args) {
    }



}
