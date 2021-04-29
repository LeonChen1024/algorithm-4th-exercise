package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.FrequencyCounter;

/**
 * 3.1.6 Give the number of calls to put() and get() issued by FrequencyCounter, as a
 * function of the number W of words and the number D of distinct words in the input.
 * <p>
 * 给出 FrequencyCounter 中 put() 和 get() 方法的调用次数,写出得到输入单词数量 W 和其中不同的单词数量 D
 * 的函数
 *
 * @author LeonChen
 * @since 2/2/21
 */
class E3_1_06 {

    /**
     * 参见官方类.
     * <p>
     * 每个单词放进表中都会 put 一次,而且寻找最大值时还有一次调用,所以 W = put()次数 -1
     * 重复的单词放进表中会 get 一次当前值,而且寻找最大值时每个键都会调用两次 get,所以 get()次数 = (W-D)+2D
     * D = get 次数-W
     *
     * @param args
     */
    public static void main(String[] args) {
        FrequencyCounter.main(new String[]{""});
    }


}
