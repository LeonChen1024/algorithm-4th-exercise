package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.3 Give five orderings of the keys A X C S E R H that, when inserted into an
 * initially empty BST, produce the best-case tree.
 * <p>
 * 给出 5 种键 A X C S E R H 的顺序,当他们插入一个空 BST 的时候会产生最佳情况
 *
 * @author LeonChen
 * @since 3/31/20
 */
class E3_2_03 {

    /**
     * @formatter:off
     * 1.   H S C A E R X
     * 2.   H C S A E R X
     * 3.   H S C E A R X
     * 4.   H S C A E X R
     * 5.   H S C E A X R
     *
     * H 一定要最先插入,S 一定要在 RX 之前插入,C 一定要在 AE 之前插入
     */
    public static void main(String[] args) {
    }



}
