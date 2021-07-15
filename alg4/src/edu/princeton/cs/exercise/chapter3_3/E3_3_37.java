package edu.princeton.cs.exercise.chapter3_3;

/**
 * 3.3.37 Memoryless. Show that red-black BSTs are not memoryless: for example, if you
 * insert a key that is smaller than all the keys in the tree and then immediately
 * delete the minimum, you may get a different tree.
 * <p>
 * 无记忆的.证明红黑树是无记忆的: 比如,如果你插入了一个键比树中所有的键都小然后立刻删除最小键,你可能会得到一个
 * 不同的树
 *
 * @author LeonChen
 * @since 7/7/21
 */
class E3_3_37 {

    /**
     * @formatter:off
     *   B3
     * R2
     *
     * 插入最小建
     *     B3
     *   R2
     * R1
     *
     *      B2
     *   R1  R3
     *
     *      R2
     *   B1  B3
     *
     *      B2
     *   B1  B3
     *
     * 删除最小建
     *      R2
     *   B1  B3
     *
     *      B2
     *   R1  R3
     *
     *      B2
     *         R3
     *
     *      B3
     *    R2
     * @formatter:on
     */
    public static void main(String[] args) {
    }

}
