package edu.princeton.cs.exercise.chapter3_1;

/**
 * 3.1.21 Memory usage. Compare the memory usage of BinarySearchST with that of
 * SequentialSearchST for N key-value pairs, under the assumptions described in Section
 * 1.4. Do not count the memory for the keys and values themselves, but do count
 * references to them. For BinarySearchST, assume that array resizing is used, so that the
 * array is between 25 percent and 100 percent full.
 * <p>
 * 内存占用.比较N 个键值对的 BinarySearchST 和 SequentialSearchST 的内存占用,基于 1.4 的假设.不要计算
 * 键值本身的内存,计算他们的引用即可.对于 BinarySearchST ,假设使用了数组扩容,所以数组的大小是在 25%~100%大小
 *
 * @author LeonChen
 * @since 2/14/20
 */
class E3_1_21 {


    /**
     * BinarySearchST
     * <p>
     * 对象头 -> 16 bytes
     * Key[] 引用 (keys) -> 8 bytes
     * Value[] 引用 (values) -> 8 bytes
     * int value (size) -> 4 bytes
     * 内存对齐 -> 4 bytes
     * <p>
     * Key[]
     * 对象头 -> 16 bytes
     * int value (length) -> 4 bytes
     * 内存对齐 -> 4 bytes
     * N 个 Key 引用 ->  8N ~ 32N bytes (25% ~ 100%)
     * <p>
     * Value[]
     * 对象头 -> 16 bytes
     * int value (length) -> 4 bytes
     * 内存对齐 -> 4 bytes
     * N 个Value 引用 ->  8N ~ 32N bytes ( 25% ~ 100% )
     * <p>
     * 内存占用:
     * 16 + 8 + 8 + 4 + 4 + 16 + 4 + 4 + (8N to 32N) + 16 + 4 + 4 + (8N to 32N)
     * = (16N to 64N) + 88 bytes
     * <p>
     * <p>
     * SequentialSearchST
     * 对象头 -> 16 bytes
     * Node 引用 (first) -> 8 bytes
     * <p>
     * Node
     * 对象头 -> 16 bytes
     * 额外的尾部对象引用 -> 8 bytes
     * Key 引用 (key) -> 8 bytes
     * Value 引用 (value) -> 8 bytes
     * Node 引用 (next) -> 8 bytes
     * (N 个 Node 引用 -> 48N bytes)
     * int value (size) -> 4 bytes
     * 内存对齐 -> 4 bytes
     * 内存占用: 16 + 8 + (16 + 8 + 8 + 8 + 8)N + 4 + 4 = 48N + 32 bytes
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
