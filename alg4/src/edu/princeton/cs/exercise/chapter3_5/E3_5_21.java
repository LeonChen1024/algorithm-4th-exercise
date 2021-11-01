package edu.princeton.cs.exercise.chapter3_5;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3.5.21 Inverted concordance. Write a program InvertedConcordance that takes a
 * concordance on standard input and puts the original string on standard output stream.
 * Note : This computation is associated with a famous story having to do with the Dead
 * Sea Scrolls. The team that discovered the original tablets enforced a secrecy rule that
 * essentially resulted in their making public only a concordance. After a while, other
 * researchers figured out how to invert the concordance, and the full text was eventually
 * made public.
 * <p>
 * 翻转索引.编写一个程序 InvertedConcordance 接收一个标准输入的索引并且将原始字符串给到输出流.注意:这个
 * 计算和 Dead Sea Scrolls 这个有名的故事有关.发现原始表的队伍保守秘密并只提供了索引.过了一会,其他的研究
 * 人员找出如何翻转索引的方法,所有的文字最终被公开
 *
 * @author LeonChen
 * @since 9/11/21
 */
class E3_5_21 {

    /**
     * @formatter:off
     * 按索引存一个字符串数组,再按序取出即可
     * @formatter:on
     */
    public static void main(String[] args) {
        //Test
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
        //
        //Expected output
        //  This is a text to test a concordance. The text has many words. This is a good test.

        InvertedConcordance invertedConcordance = new InvertedConcordance();

        Map<String, List<Integer>> concordance = invertedConcordance.readConcordanceFromInput();
        String text = invertedConcordance.buildTextFromConcordance(concordance);
        StdOut.println(text);
    }

    private static class InvertedConcordance {

        int numberOfWords = 0;

        private Map<String, List<Integer>> readConcordanceFromInput() {
            Map<String, List<Integer>> concordanceMap = new HashMap<>();
            String[] input = new String[]{"This 0, 13", "is 1, 14", "a 2, 6, 15",
                    "text 3, 9", "to 4", "test 5", "concordance. 7", "The 8", "has 10",
                    "many 11", "words. 12", "good 16", "test. 17"};
//            while (StdIn.hasNextLine()) {
//            String concordanceLine = StdIn.readLine();
            for (String concordanceLine : input) {

                String[] concordanceInformation = concordanceLine.split(" ");

                String key = concordanceInformation[0];
                concordanceMap.put(key, new ArrayList<>());

                for (int i = 1; i < concordanceInformation.length; i++) {
                    String noCommaValue = concordanceInformation[i];

                    if (noCommaValue.charAt(noCommaValue.length() - 1) == ',') {
                        noCommaValue = noCommaValue.substring(0, noCommaValue.length() - 1);
                    }

                    int position = Integer.parseInt(noCommaValue);
                    concordanceMap.get(key).add(position);

                    if (position > numberOfWords) {
                        numberOfWords = position;
                    }
                }
            }
//            }

            return concordanceMap;
        }

        private String buildTextFromConcordance(Map<String, List<Integer>> concordance) {
            String[] wordsInText = new String[numberOfWords + 1];

            for (String word : concordance.keySet()) {
                for (int position : concordance.get(word)) {
                    wordsInText[position] = word;
                }
            }

            StringBuilder text = new StringBuilder();
            boolean isFirstWord = true;

            for (String word : wordsInText) {
                if (isFirstWord) {
                    isFirstWord = false;
                } else {
                    text.append(" ");
                }

                text.append(word);
            }

            return text.toString();
        }

    }


}
