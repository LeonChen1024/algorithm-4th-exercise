package edu.princeton.cs.exercise.chapter3_1;

/**
 * 3.1.23 Analysis of binary search. Prove that the maximum number of compares used for a
 * binary search in a table of size N is precisely the number of bits in the binary
 * representation of N, because the operation of shifting 1 bit to the right converts the binary
 * representation of N into the binary representation of ⎣N/2⎦.
 * <p>
 * 分析二分搜索.证明一个大小为 N 的表使用二分搜索的最大比较次数正好是 N 的二进制表达位数,因为向右移动一位 N 的
 * 二进制表达相当于代表着 ⎣N/2⎦
 *
 * @author LeonChen
 * @since 2/15/21
 */
class E3_1_23 {

    /**
     * 前面证得了二分搜索对比最多 lgN + 1 次,而一个数的二进制位数最多也是 lgN + 1 ,没右移一位,相当于除以 2,
     * 正好和二分法每查找一次,剩余数量是原数量除以 2 一致.
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
