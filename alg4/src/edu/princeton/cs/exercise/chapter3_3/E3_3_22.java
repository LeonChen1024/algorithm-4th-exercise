package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.3.22 Find a sequence of keys to insert into a BST and into a red-black BST such that
 * the height of the BST is less than the height of the red-black BST, or prove that no such
 * sequence is possible.
 * <p>
 * 找到一个键序列插入到一个 BST 中和一个红黑树中使得 BST 的高度小于红黑树,或者证明这种情况不可能发生
 *
 * @author LeonChen
 * @since 6/25/21
 */
class E3_3_22 {

    /**
     * @formatter:off
     * 528970
     *
     * rbt
     *                           8
     *                  r5                9
     *             2          7
     *      r0
     *
     * bst
     *                              5
     *                     2                   8
     *              0                    7             9
     *
     *
     * 红黑树 4 层 , bst 3层
     *
     * 
     * @formatter:on
     */
    public static void main(String[] args) {
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();
        bst.put("5", 0);
        bst.put("2", 1);
        bst.put("8", 2);
        bst.put("9", 3);
        bst.put("7", 4);
        bst.put("0", 5);
        StringBuilder result = new StringBuilder();
        for (String key : bst.keys()) {
            result.append(key);
        }
        StdOut.println(result.toString());
        StdOut.println("except : AEINOQSTUY");

        String ceiling = bst.ceiling("P");
        StdOut.println(ceiling);
        StdOut.println("except: Q");

        String f = bst.floor("P");
        StdOut.println(f);
        StdOut.println("except: O");
    }


}
