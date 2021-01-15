package edu.princeton.cs.exercise.chapter2_5;


import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.16 Unbiased election. In order to thwart bias against candidates whose names appear toward
 * the end of the alphabet, California sorted the candidates appearing on its 2003 gubernatorial
 * ballot by using the following order of characters:
 * R W Q O J M V A H B S G Z X N T C I E K U P D Y F L
 * Create a data type where this is the natural order and write a client California with a single
 * static method main() that sorts strings according to this ordering. Assume that each string is
 * composed solely of uppercase letters.
 *
 * 无偏见选举.为了避免对名字出现在字母表后面的候选人造成偏见,加利福尼亚在2003年州长选举中使用下列的字母排序:
 * R W Q O J M V A H B S G Z X N T C I E K U P D Y F L
 * 创建一个数据类型使用这个字母排序作为自然顺序并且编写一个客户端 California 带有一个静态方法 main() 根据这个
 * 顺序进行排序.假设每个字符串都是有大写字母组成的
 *
 * @author LeonChen
 * @since 12/24/20
 */
class E02_05_16 {


    public static final Comparator<String> CANDIDATE_ORDER = new CandidateComparator();

    private static class CandidateComparator implements Comparator<String> {
        private static String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

        @Override
        public int compare(String a, String b) {
            int n = Math.min(a.length(), b.length());
            for (int i = 0; i < n; i++) {
                int aindex = order.indexOf(a.charAt(i));
                int bindex = order.indexOf(b.charAt(i));
                if (aindex < bindex) {
                    return -1;
                } else if (aindex > bindex) return +1;
            }
            return a.length() - b.length();
        }
    }


    // 官方解答
    public static void main(String[] args) {
        String[] candidates = StdIn.readAll().toUpperCase().split("\\n+");
        int n = candidates.length;
        Arrays.sort(candidates, CANDIDATE_ORDER);
        for (int i = 0; i < n; i++) {
            StdOut.println(candidates[i]);
        }
    }

}
