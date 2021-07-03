package edu.princeton.cs.exercise.chapter3_3;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

/**
 * 3.3.21 Create a test client TestRB.java, based on your solution to Exercise 3.2.10.
 * <p>
 * 创建一个测试客户端 TestRB.java,根据练习 3.2.10 的解决方案
 *
 * @author LeonChen
 * @since 6/24/21
 */
class E3_3_21 {

    /**
     * @formatter:off
     * 输入  E A S Y Q U T I O N 
     * 
     * 
     * @formatter:on
     */
    public static void main(String[] args) {
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();
        bst.put("E", 0);
        bst.put("A", 1);
        bst.put("S", 2);
        bst.put("Y", 3);
        bst.put("Q", 4);
        bst.put("U", 5);
        bst.put("T", 6);
        bst.put("I", 7);
        bst.put("O", 8);
        bst.put("N", 9);
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
