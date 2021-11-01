package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3.5.20 Concordance. Write an ST client Concordance that puts on standard output a
 * concordance of the strings in the standard input stream (see page 498).
 * <p>
 * 索引.编写一个 ST 客户端Concordance 输出标准输入中字符串的索引(见 498)
 *
 * @author LeonChen
 * @since 9/11/21
 */
class E3_5_20 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        //Test
        //  This is a text to test a concordance.
        //  The text has many words. This is a good test.
        //
        //Expected output (not necessarily in this order)
        // This 0, 13
        // is 1, 14
        // a 2, 6, 15
        // text 3, 9
        // to 4
        // test 5
        // concordance. 7
        // The 8
        // has 10
        // many 11
        // words. 12
        // good 16
        // test. 17

        Concordance concordance = new Concordance();
        Map<String, List<Integer>> concordanceMap = concordance.readInputAndBuildConcordance();
        concordance.outputConcordance(concordanceMap);
    }

    private static class Concordance {

        private Map<String, List<Integer>> readInputAndBuildConcordance() {
            int wordIndex = 0;
            Map<String, List<Integer>> concordanceMap = new HashMap<>();

//            while (StdIn.hasNextLine()) {
//                String wordLine = StdIn.readLine();
            String wordLine = "This is a text to test a concordance.";
            String[] words = wordLine.split(" ");

            for (String word : words) {
                if (!concordanceMap.containsKey(word)) {
                    concordanceMap.put(word, new ArrayList<>());
                }
                concordanceMap.get(word).add(wordIndex);

                wordIndex++;
            }
//            }

            return concordanceMap;
        }

        private void outputConcordance(Map<String, List<Integer>> concordance) {
            for (String word : concordance.keySet()) {
                StdOut.print(word);
                boolean isFirstValue = true;

                for (Integer positionInText : concordance.get(word)) {
                    if (isFirstValue) {
                        isFirstValue = false;
                    } else {
                        StdOut.print(",");
                    }

                    StdOut.print(" " + positionInText);
                }

                StdOut.println();
            }
        }
    }

}
