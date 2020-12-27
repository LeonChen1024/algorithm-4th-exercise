package edu.princeton.cs.exercise.chapter2_5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2.5.2 Write a program that reads a list of words from standard input and prints all two-word
 * compound words in the list. For example, if after, thought, and afterthought are in the list,
 * then afterthought is a compound word.
 *
 * <p>编写一个程序从标准输入读取一个列表的单词并且打印出列表中所有的两个单词复合的单词.比如,如果列表中出现了 <br>
 * after,thought,还有 afterthought ,那么 afterthought 是一个复合单词.
 *
 * @author LeonChen
 * @since 12/14/20
 */
class E02_05_02 {

  private static class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
      return o1.length() - o2.length();
    }
  }

  /** 可以根据两个组合单词的长度和来查找对应长度的复合单词. */
  public static void main(String[] args) {
    String[] input =
        new String[] {
          "afterthougnt",
          "test",
          "thougnt",
          "common",
          "after",
          "abandon",
          "cool",
          "java",
          "coolguy",
          "C",
          "python",
          "guy"
        };

    Arrays.sort(input, new LengthComparator());

    int maxlen = input[input.length - 1].length();
    for (int i = 0; i < input.length; i++) {
      for (int j = i + 1; j < input.length; j++) {
        int compoundLen = input[i].length() + input[j].length();
        if (compoundLen > maxlen) {
          break;
        }
        int start = binFindStart(input, compoundLen, j, input.length);
        if (start == -1) {
          continue;
        }
        while (start < input.length && input[start].length() == compoundLen) {
          if ((input[i] + input[j]).equals(input[start])
              || (input[j] + input[i]).equals(input[start])) {
            System.out.println(input[start]);
          }
          start++;
        }
      }
    }
  }

  private static int binFindStart(String[] input, int compoundLen, int lo, int hi) {
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      int midLen = input[mid].length();
      if (midLen == compoundLen) {
        if (input[mid - 1].length() != compoundLen) {
          return mid;
        } else {
          hi = mid - 1;
        }
      } else if (midLen > compoundLen) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return -1;
  }
}
