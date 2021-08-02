package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.6 Suppose that keys are t-bit integers. For a modular hash function with prime M,
 * prove that each key bit has the property that there exist two keys differing only in
 * that bit that have different hash values.
 * <p>
 * 假设是 t-bit 整数.对于一个对 M 取模 hash 函数,证明每个键存在只有某 bit 不同的时候拥有不同的值
 *
 * @author LeonChen
 * @since 7/26/21
 */
class E3_4_06 {

    /**
     * @formatter:off
     * 5bit 的时候
     * 16: 10000
     * 17: 10001 (differing only in the 1st bit)
     * 18: 10010 (differing only in the 2nd bit)
     * 20: 10100 (differing only in the 3rd bit)
     * 24: 11000 (differing only in the 4th bit)
     * 8:  01000 (differing only in the 5th bit)
     *
     * 如果 M = 7
     *
     * 16 % 7 = 2
     * 17 % 7 = 3
     * 18 % 7 = 4
     * 20 % 7 = 6
     * 24 % 7 = 3
     * 8 % 7 = 1
     *
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
