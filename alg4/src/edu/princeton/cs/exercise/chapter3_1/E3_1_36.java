package edu.princeton.cs.exercise.chapter3_1;

/**
 * 3.1.36 Performance validation II. Explain why the performance of BinarySearchST
 * and SequentialSearchST for FrequencyCounter is even better than predicted by analysis.
 * <p>
 * 性能验证 II.解释为什么 BinarySearchST 和 SequentialSearchST 在FrequencyCounter 中的性能是比预期
 * 更好一些的
 *
 * @author LeonChen
 * @since 3/4/20
 */
class E3_1_36 {

    /**
     * 根据上一题的输出我们可以看到,输入中的很多单词都是重复的,所以很多时候都可以直接在表中找到,而不需要完整的走完
     * 一次遍历,导致了性能会比预期的要好
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

    }


}
