package edu.princeton.cs.exercise.chapter3_1;

import edu.princeton.cs.algs4.FrequencyCounter;

/**
 * 3.1.9 Add code to FrequencyCounter to keep track of the last call to put(). Print the
 * last word inserted and the number of words that were processed in the input stream
 * prior to this insertion. Run your program for tale.txt with length cutoffs 1, 8, and 10.
 * <p>
 * 添加代码到 FrequencyCounter 中来追踪最近一次调用 put().打印出最近一次插入的单词和之前输入流处理的插入次数.
 * 运行你的程序输入 tale.txt 使用长度临界值为 1,8,10
 *
 * @author LeonChen
 * @since 2/5/21
 */
class E3_1_09 {

    /**
     * 在每次 put 之前记录这次 put 的值是什么即可
     * <p>
     * cutoff = 1
     * the 7989
     * lastPut = known
     * distinct = 10674
     * words    = 135643
     * <p>
     * cutoff = 8
     * business 122
     * lastPut = faltering
     * distinct = 5126
     * words    = 14346
     * <p>
     * cutoff = 10
     * monseigneur 101
     * lastPut = disfigurement
     * distinct = 2257
     * words    = 4579
     *
     * @param args
     */
    public static void main(String[] args) {
        FrequencyCounter.main(new String[]{"10"});
    }


}
