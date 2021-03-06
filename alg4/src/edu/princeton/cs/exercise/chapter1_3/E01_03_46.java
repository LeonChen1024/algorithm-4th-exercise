package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.46 Forbidden triple for stack generability. Prove that a permutation can be generated by a
 * stack (as in the previous question) if and only if it has no forbidden triple (a, b, c) such that
 * a < b < c with c first, a second, and b third (possibly with other intervening integers between c
 * and a and between a and b).
 *
 * <p>Partial solution: Suppose that there is a forbidden triple (a, b, c). Item c is popped before
 * a and b, but a and b are pushed before c. Thus, when c is pushed, both a and b are on the stack.
 * Therefore, a cannot be popped before b.
 *
 * <p>禁止三元栈的生成性. 证明一个序列可以被一个栈生成(如同前面的问题)当且仅当它没有被禁止的三元情况 (a,b,c) 即 a<b<c 并且<br>
 * c 在第一个,a 在第二个, b 在第三个(c 和 a 以及 a 和 b 之间可能会有其他的中间数字.)
 *
 * <p>部分解决方案: 假设这里有一个禁用的三元(a,b,c). 项 c 是在 a 和 b 之前被弹出,但是 a 和 b 是在 c 之前推入的.因此,当 c<br>
 * 被推入的时候,a 和 b 都在栈中.因此,a 不可能在 b 之前被弹出.
 *
 * @author LeonChen
 * @since 2/6/20
 */
class E01_03_46 {

  public static void main(String[] args) {

    StdOut.println(testGenerTriple("1432"));
    StdOut.println(testGenerTriple("4213"));
    StdOut.println(testGenerTriple("654213"));
  }

  /**
   * 由于不可能出现大小中这种情况,所以,只需要保留一个前面最大的,以及中间最小的进行判断.
   *
   * @param permutation 要验证的公式.
   * @return 是否符合要求.
   */
  private static boolean testGenerTriple(String permutation) {
    Integer max = Integer.MIN_VALUE;
    Integer min = Integer.MAX_VALUE;

    for (int i = 0; i < permutation.length(); i++) {
      Character c = permutation.charAt(i);
      Integer cur = Integer.valueOf(c);
      if (max > cur && cur > min) {
        return false;
      }

      if (cur > max) {
        max = cur;
        // 当 max 更新的时候 , min 要从这个位置之后开始重新寻找
        min = Integer.MAX_VALUE;
      } else if (cur < min) {
        min = cur;
      }
    }
    return true;
  }
}
