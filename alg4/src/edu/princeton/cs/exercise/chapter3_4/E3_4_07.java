package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.7 Consider the idea of implementing modular hashing for integer keys with the
 * code (a * k) % M , where a is an arbitrary fixed prime. Does this change mix up the
 * bits sufficiently well that you can use nonprime M?
 * <p>
 * 考虑通过取模代码 (a * k) % M 对整数键进行 hash 的实现,其中 a 是任意的固定质数.这种变化方式是否很好的混合
 * 了位,你可以使用非质数 M
 *
 * @author LeonChen
 * @since 7/27/21
 */
class E3_4_07 {

    /**
     * @formatter:off
     * 1- (a * k) % M = hash
     * 2- a * k = M * q + hash, hash = a * k - M * q, q 是一个自然数
     * 3- 如果 a 也是非质数的话 ,意味着会有一个自然数 t 使得 M = a * t.得到
     * hash = a * k - a * t * q = a * (k - t * q)
     * 这意味着所有 hash 值都可以被 a 整除,会减少 hash 的分布广度.
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
