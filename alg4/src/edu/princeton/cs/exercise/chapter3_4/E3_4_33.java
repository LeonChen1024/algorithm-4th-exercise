package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.33 Bad hash function. Consider the following hashCode() implementation for
 * String, which was used in early versions of Java:
 * public int hashCode() {
 * int hash = 0;
 * int skip = Math.max(1, length() / 8);
 * for (int i = 0; i < length(); i += skip)
 * hash = (hash * 37) + charAt(i);
 * return hash;
 * }
 * Explain why you think the designers chose this implementation and then why you
 * think it was abandoned in favor of the one in the previous exercise.
 * <p>
 * 差的 hash 函数.考虑如下字符串 hashCode()实现,它是早起 Java 版本使用的
 * public int hashCode() {
 * int hash = 0;
 * int skip = Math.max(1, length() / 8);
 * for (int i = 0; i < length(); i += skip)
 * hash = (hash * 37) + charAt(i);
 * return hash;
 * }
 * 说明你认为它为什么会被选择的原因和为什么和前一个练习相比它会被禁用的原因
 *
 * @author LeonChen
 * @since 8/16/21
 */
class E3_4_33 {


    /**
     * @formatter:off
     * 会被选择的原因我觉得有效率比较高,skip 采取跳跃式的取值,使得计算不需要遍历所有字符,并且每次得到的值都
     * 会乘以一个较大的质数减少两次相互影响的情况.
     * 会被弃用的原因我觉得是因为它跳过了太多的字符.很多只是特定位置一样的字符串都会拥有相同的 hashcode,
     * 导致很多的碰撞
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
