package edu.princeton.cs.exercise.chapter3_2;

/**
 * 3.2.27 Memory usage. Compare the memory usage of BST with the memory usage of
 * BinarySearchST and SequentialSearchST for N key-value pairs, under the assumptions
 * described in Section 1.4 (see Exercise 3.1.21). Do not count the memory for
 * the keys and values themselves, but do count references to them. Then draw a diagram
 * that depicts the precise memory usage of a BST with String keys and Integer values
 * (such as the ones built by FrequencyCounter), and then estimate the memory usage
 * (in bytes) for the BST built when FrequencyCounter uses BST for Tale of Two Cities.
 * <p>
 * 内存使用.在 1.4 章节(见练习 3.1.21)的假设下,比较N 个键值对的 BST 和 BinarySearchST 以及
 * SequentialSearchST 的内存占用.不要计算键值本身占用的内存大小,而是计算他们的引用.然后绘制一个图表描述
 * 一个使用 String key和 Integer 值得 BST 的精确内存占用(就像 FrequencyCounter 构建的一样),然后
 * 估算使用 双城表 在FrequencyCounter 中构建 BST 的内存占用.
 *
 * @author LeonChen
 * @since 4/23/21
 */
class E3_2_27 {

    /**
     * @formatter:off
     * BST
     * 对象头 -> 16 bytes
     * root 引用 -> 8 bytes
     * Node
     *  对象头 ->16 bytes
     *  父类型引用 -> 8 bytes
     *  key 引用 -> 8 bytes
     *  value 引用 -> 8 bytes
     *  left -> 8 bytes
     *  right -> 8 bytes
     *  size -> 4 bytes
     *  内存对齐 -> 4 bytes
     *
     *  N 个 Node 总的内存为 64N bytes
     *
     *  总的为 24 + 64N bytes
     *
     *  SequentialSearchST BinarySearchST 见练习 3.1.21
     * BinarySearchST   (16N to 64N) + 88 bytes
     * SequentialSearchST  48N + 32 bytes
     *
     *
     * String 最少 40bytes , Integer 24bytes.
     * 其中双城记不重复单词为 26436 个.
     *
     * 一个键值对 64 bytes
     * BST 为 24 + 128N bytes ,将单词数代入 N
     * 24 + 64N bytes = 24 + 128*26446 = 3383832 bytes
     *
     *
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
