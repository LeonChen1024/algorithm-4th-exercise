package edu.princeton.cs.exercise.chapter2_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.exercise.chapter1_3.e01_03_17;

/**
 * 2.1.22 Transaction sort test client. Write a class SortTransactions that consists of a static
 * method main() that reads a sequence of transactions from standard input, sorts them, and prints
 * the result on standard output (see Exercise 1.3.17).
 *
 * <p>Solution : <code>
 *   public class SortTransactions
 *   {
 *     public static Transaction[] readTransactions()
 *     { // See Exercise 1.3.17 }
 *       public static void main(String[] args)
 *       {
 *         Transaction[] transactions = readTransactions();
 *         Shell.sort(transactions);
 *         for (Transaction t : transactions)
 *           StdOut.println(t);
 *       }
 *     }
 *   }
 * </code>
 *
 * <p>交易排序测试客户端.编写一个类 SortTransactions 里面包含一个静态 main() 方法从标准输入中读取一个序列的交易<br>
 * 将他们排序,并且打印结果到标准输出(见 练习 1.3.17)
 *
 * @author LeonChen
 * @since 5/21/20
 */
class E02_01_22 {

  /** */
  public static void main(String[] args) {
    String s = "aaaa 4/2/1992 333.2-bbbb 3/1/2022 555.1-cccc 6/3/2011 255.9";
    e01_03_17.Transaction[] transactions = e01_03_17.Transaction.readTransaction(s);
    for (int i = 0; i < transactions.length; i++) {
      System.out.println(transactions[i]);
    }

    StdOut.println();
    Insertion.sort(transactions);
    StdOut.println(" ==================== sorted ==========================");
    for (int i = 0; i < transactions.length; i++) StdOut.println(transactions[i]);
  }
}
