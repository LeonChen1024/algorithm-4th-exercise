package edu.princeton.cs.exercise.chapter3_4;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.4.32 Hash attack. Find 2N strings, each of length 2N, that have the same hashCode()
 * value, supposing that the hashCode() implementation for String is the following:
 * public int hashCode()
 * {
 * int hash = 0;
 * for(int i=0;i<length();i++)
 * hash = (hash * 31) + charAt(i);
 * return hash;
 * }
 * Strong hint : Aa and BB have the same value.
 * <p>
 * hash 攻击.找到 2N 字符串,每个长度为 2N, 并且他们拥有相同的 hashCode()值,假设 hashCode() 实现如下
 * public int hashCode()
 * {
 * int hash = 0;
 * for(int i=0;i<length();i++)
 * hash = (hash * 31) + charAt(i);
 * return hash;
 * }
 * 提示: Aa 和 BB 值相同.
 *
 * @author LeonChen
 * @since 8/16/21
 */
class E3_4_32 {

    /**
     * @formatter:off
     * @formatter:on
     */
    public static void main(String[] args) {
        E3_4_32 e = new E3_4_32();
        List<String> hashAttackInput = e.generateStringsInput(3);

        for (String string : hashAttackInput) {
            StdOut.println(string);
        }
    }

    public int hashCode(String string) {
        int hash = 0;

        for (int i = 0; i < string.length(); i++) {
            hash = (hash * 31) + string.charAt(i);
        }

        return hash;
    }

    private List<String> generateStringsInput(int n) {
        String[] values = {"Aa", "BB"};

        List<String> strings = new ArrayList<>();
        generateStrings(n, 0, strings, "", values);

        return strings;
    }

    private void generateStrings(int n, int currentIndex, List<String> strings, String currentString, String[] values) {
        if (currentIndex == n) {
            strings.add(currentString);
            return;
        }

        for (String value : values) {
            String newValue = currentString + value;
            int newIndex = currentIndex + 1;

            generateStrings(n, newIndex, strings, newValue, values);
        }
    }


}
