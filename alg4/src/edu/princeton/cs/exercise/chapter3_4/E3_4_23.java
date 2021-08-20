package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.23 Consider modular hashing for string keys with R = 256 and M = 255. Show that
 * this is a bad choice because any permutation of letters within a string hashes to the
 * same value.
 * <p>
 * 考虑使用  R = 256 和 M = 255 来对字符串键进行 hash.证明这是一个不好的选择因为字符串的不同字符排列
 * 会hash 到相同值
 *
 * @author LeonChen
 * @since 8/6/21
 */
class E3_4_23 {

    /**
     * @formatter:off
     * hash(str{k + 1}) = (hash(str{k}) * 256 + char(k + 1)) % 255
     *  = (hash(str{k}) * (255 + 1) + char(k + 1)) % 255
     *  = (hash(str{k}) * 255 + hash(str{k}) + char(k + 1)) % 255
     *  = (hash(str{k}) + char(k + 1)) % 255
     *  = ((sum of k chars) % 255 + char(k + 1)) % 255
     *  = ((sum of k chars + char(k + 1)) % 255) % 255
     *  = (sum of k + 1 chars) % 255
     *  (N * 255) % 255 = 0 ,并且字符值总小于 255,所以可以直接移出去
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
