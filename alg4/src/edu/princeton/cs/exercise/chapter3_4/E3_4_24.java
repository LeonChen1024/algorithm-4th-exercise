package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.24 Analyze the space usage of separate chaining, linear probing, and BSTs for
 * double keys. Present your results in a table like the one on page 476.
 * <p>
 * 分析散列链,线性探查,和 BST 对 double key的空间使用.如 476 页那样展示你的结果
 *
 * @author LeonChen
 * @since 8/6/21
 */
class E3_4_24 {

    /**
     * @formatter:off
     *             Space usage in symbol tables
     *       Method          Space usage for N items (double keys)
     * Separate chaining     表: 24 + 48N.
     *                       24 bytes: 16 bytes 对象头 + 8 bytes "first" 引用.
     *                       然后一个节点 48 bytes: 16 bytes 对象头, 8 bytes double 键, 8 bytes
     *                       double 值, 8 bytes 下个对象引用 , 8 bytes 内部类对象头
     *                       Separate chaining: 56 + 32M + 48N.
     *                       表消耗 56 bytes + 8M (16 bytes 对象头, 20 bytes 数组对象头,
     *                       8 bytes 数组引用, 4 bytes M, 4 bytes 内存对齐) + (8 bytes
     *                       每个数组对象引用) + M 序列对象
     *
     * Linear probing        80 +  (32N~128N). (16 bytes 对象头, 20 bytes 键数组对象头,
     *                       20 bytes 值数组对象头, 8 bytes key 数组引用, 8 bytes 值数组引用,
     *                       4 bytes M, 4 bytes N) + (4 到 16 bytes 数组扩容) * (8 bytes 每个
     *                       double key, 8 bytes 每个 double 值).
     *
     * BSTs                 24 + 56N. 16 bytes 对象头和 8 bytes "first" 引用加上节点内存.
     *                      BST 中每个节点消耗 56 bytes : 16 bytes 对象头, 8 bytes double 
     *                      key, 8 bytes double value, 8 bytes left 引用, 8 bytes 
     *                      right 引用 和 8 bytes 内部类对象头).
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
