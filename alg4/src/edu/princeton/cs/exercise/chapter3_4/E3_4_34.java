package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.34 Hash cost. Determine empirically the ratio of the time required for hash()
 * to the time required for compareTo(), for as many commonly-used types of keys for
 * which you can get meaningful results.
 * <p>
 * hash 成本.根据经验判断使用 hash()需要的时间和使用compareTo() 需要的时间的比例,对于那些通常使用的
 * 键类型得出有意义的结果
 *
 * @author LeonChen
 * @since 8/17/21
 */
class E3_4_34 {


    /**
     * @formatter:off
     * compareTo()的成本平均远远大于 hash(),因为compareTo()是线性递增的,而好的 hash 基本是常量级的
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
