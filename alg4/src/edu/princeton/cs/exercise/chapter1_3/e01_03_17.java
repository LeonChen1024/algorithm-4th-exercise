package edu.princeton.cs.exercise.chapter1_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.exercise.chapter1_2.e01_02_13_and_14;

/**
 * 1.3.17 Do Exercise 1.3.16 for Transaction.
 *
 * <p>为Transaction 做练习1.3.16
 *
 * @author LeonChen
 * @since 1/6/20
 */
public class e01_03_17 {

  public static void main(String[] args) {

    String s = "aaaa 4/2/1992 333.2-bbbb 3/1/2022 555.1-cccc 6/3/2011 255.9";
    Transaction[] transactions = Transaction.readTransaction(s);
    for (int i = 0; i < transactions.length; i++) {
      System.out.println(transactions[i]);
    }
  }

  public static class Transaction implements Comparable<Transaction> {

    private final String name;
    private final e01_02_13_and_14.SmartDate date;
    private final double amount;

    public Transaction(String who, e01_02_13_and_14.SmartDate when, double amount) {
      this.name = who;
      this.date = when;
      this.amount = amount;
    }

    public Transaction(String transaction) {
      String[] s = transaction.split("\\s+");
      if (s.length != 3) throw new IllegalArgumentException("Argument illegal " + transaction);
      this.name = s[0];
      this.date = new e01_02_13_and_14.SmartDate(s[1]);
      this.amount = Double.parseDouble(s[2]);
    }

    public String who() {
      return this.name;
    }

    public e01_02_13_and_14.SmartDate when() {
      return this.date;
    }

    public double amount() {
      return this.amount;
    }

    public String toString() {
      return name + " " + date + " " + amount;
    }

    public boolean equals(Object that) {
      if (this == that) return true;
      if (that == null) return false;
      if (this.getClass() != that.getClass()) return false;
      e01_02_13_and_14.Transaction t = (e01_02_13_and_14.Transaction) that;
      if (!this.name.equals(t.who())) return false;
      if (!this.date.equals(t.when())) return false;
      if (this.amount != t.amount()) return false;
      return true;
    }

    public int hashCode() {
      int hash = 1;
      hash = hash * 31 + this.name.hashCode();
      hash = hash * 31 + this.date.hashCode();
      hash = hash * 31 + ((Double) this.amount).hashCode();
      return hash;
    }

    @Override
    public int compareTo(Transaction o) {
      if (this.amount > o.amount()) return 1;
      else if (this.amount < o.amount()) return -1;
      else return 0;
    }

    public static Transaction[] readTransaction(String s) {

      String[] transaction = s.split("-");
      int n = transaction.length;
      Queue<Transaction> q = new Queue<Transaction>();
      for (int i = 0; i < n; i++) {
        q.enqueue(new Transaction(transaction[i]));
      }

      Transaction[] result = new Transaction[n];
      for (int i = 0; i < n; i++) {
        result[i] = q.dequeue();
      }

      return result;
    }
  }
}
