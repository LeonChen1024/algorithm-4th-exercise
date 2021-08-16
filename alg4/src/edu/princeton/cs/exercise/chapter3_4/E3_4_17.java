package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.17 Show the result of using the delete() method on page 471 to delete C from the
 * table resulting from using LinearProbingHashST with our standard indexing client
 * (shown on page 469).
 * <p>
 * 展示出对使用LinearProbingHashST 和标准索引的客户端(469 页) 使用 471 页的删除方法的结果.
 *
 * @author LeonChen
 * @since 8/4/21
 */
class E3_4_17 {

    /**
     * @formatter:off
     *
     * Hash table
     * 0 P10       8 L11
     * 1 M9        9 null
     * 2 null     10 E12
     * 3 null     11 null
     * 4 A8       12 null
     * 5 C4       13 null
     * 6 S0       14 R3
     * 7 H5       15 X7
     *
     * 删除 C (hash 5)
     * 0 P10       8 L11
     * 1 M9        9 null
     * 2 null     10 E12
     * 3 null     11 null
     * 4 A8       12 null
     * 5 null     13 null
     * 6 S0       14 R3
     * 7 H5       15 X7
     *
     * 插入 S0 (hash 6)
     * 0 P10       8 L11
     * 1 M9        9 null
     * 2 null     10 E12
     * 3 null     11 null
     * 4 A8       12 null
     * 5 null     13 null
     * 6 S0       14 R3
     * 7 H5       15 X7
     *
     * 插入 H5 (hash 4)
     * 0 P10       8 L11
     * 1 M9        9 null
     * 2 null     10 E12
     * 3 null     11 null
     * 4 A8       12 null
     * 5 H5       13 null
     * 6 S0       14 R3
     * 7 null     15 X7
     *
     * 插入 L11 (hash 6)
     * 0 P10       8 null
     * 1 M9        9 null
     * 2 null     10 E12
     * 3 null     11 null
     * 4 A8       12 null
     * 5 H5       13 null
     * 6 S0       14 R3
     * 7 L11      15 X7
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
