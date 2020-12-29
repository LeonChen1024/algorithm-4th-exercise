package edu.princeton.cs.exercise.chapter2_5;

/**
 * 2.5.5 Explain why selection sort is not stable.
 *
 * 解释一下为什么选择排序是不稳定的
 *
 * @author LeonChen
 * @since 12/17/20
 */
class E02_05_05 {

    /**
     * 因为它会将不相邻的元素交换,原本在前面的相同值元素被交换到后面.比如 b_1, b_2,a 交换后编程 a,b_2,b_1.
     * b_1,b_2 相对位置发生变化
     */
    public static void main(String[] args) {

    }

}
