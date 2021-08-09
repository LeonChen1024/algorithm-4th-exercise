package edu.princeton.cs.exercise.chapter3_4;

/**
 * 3.4.5 Is the following implementation of hashCode() legal?
 * public int hashCode()
 * { return 17; }
 * If so, describe the effect of using it. If not, explain why.
 * <p>
 * 下面 hashCode() 的实现符合要求吗?
 * 如果符合,描述使用它的效果.如果不符合,解释为什么
 *
 * @author LeonChen
 * @since 7/25/21
 */
class E3_4_05 {

    /**
     * @formatter:off
     * 符合要求,相同的对象返回的 hashcode 是一样的
     * 但是这个 hash 没有起到优化的作用,所有的对象都会得到相同的 hashcode ,导致最后演变成链表查询
     * @formatter:on
     */
    public static void main(String[] args) {
    }


}
