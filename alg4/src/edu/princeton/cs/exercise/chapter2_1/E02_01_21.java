package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

/**
 * 2.1.21 Comparable transactions. Using our code for Date (page 247) as a model, expand your
 * implementation of Transaction (Exercise 1.2.13) so that it implements Comparable, such that
 * transactions are kept in order by amount. Solution : <code>
 *   public class Transaction implements Comparable<Transaction>
 *   {
 * ...
 *     private final double amount;
 * ...
 *     public int compareTo(Transaction that)
 *     {
 *       if (this.amount > that.amount) return +1;
 *       if (this.amount < that.amount) return -1;
 *       return 0;
 *     }
 * ...
 *   }
 * </code>
 *
 * <p>可比较的交易.使用Date(247页)作为模型,扩展你的Transaction (练习 1.2.13)使它实现 Comparable,使得交易<br>
 * 能够以金额排序.解决方案: <code>
 *   public class Transaction implements Comparable<Transaction>
 *   {
 * ...
 *     private final double amount;
 * ...
 *     public int compareTo(Transaction that)
 *     {
 *       if (this.amount > that.amount) return +1;
 *       if (this.amount < that.amount) return -1;
 *       return 0;
 *     }
 * ...
 *   }
 * </code>
 *
 * @author LeonChen
 * @since 5/20/20
 */
class E02_01_21 {

  /** */
  public static void main(String[] args) {
    Transaction[] a = new Transaction[4];
    a[0] = new Transaction("a 6/1/1920 100");
    a[1] = new Transaction("b 1/16/2001 1111");
    a[2] = new Transaction("c 2/14/2012 34");
    a[3] = new Transaction("d 5/20/2020 2000");

    StdOut.println(" ==================== unsorted ==========================");
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }

    StdOut.println();
    Insertion.sort(a, new Transaction.HowMuchOrder());
    StdOut.println(" ==================== sorted ==========================");
    for (int i = 0; i < a.length; i++) StdOut.println(a[i]);
  }
}
