package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.23 Is delete() commutative? (Does deleting x, then y give the same result as
 * deleting y, then x?)
 * <p>
 * delete() 操作是否是可交换的?(删除 x,然后再删除 y 会得出会和 先删除 y 再删除 x 相同的结果?)
 *
 * @author LeonChen
 * @since 4/21/21
 */
class E3_2_23 {

    /**
     * @formatter:off
     * 没有可交换性.反例如
     *            B
     *       A         D
     *              C
     *
     * 先删除 B 再删除 A 的结果是
     *            C
     *                 D
     *
     * 而 先删除 A 再删除 B 的结果是
     *            D
     *        C
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {

    }


}
