package edu.princeton.cs.exercise.chapter1_2;

import java.util.Arrays;

/**
 * 1.2.13 Using our implementation of Date as a model (page 91),develop an implementation of
 * Transaction.
 *
 * <p>使用我们(p 91)的Date 的实现作为一个模型,开发一个交易的实现.
 *
 * <p>1.2.14 Using our implementation of equals() in Date as a model (page 103), develop
 * implementations of equals() for Transaction.
 *
 * @author LeonChen
 * @since 12/10/19
 */
public class e01_02_13_and_14 {

  public static void main(String[] args) {

    Transaction[] t = new Transaction[3];
    t[0] = new Transaction("aaaa 4/2/1992 333.2");
    t[1] = new Transaction("bbbb 3/1/2022 555.1");
    t[2] = new Transaction("cccc 6/3/2011 255.9");

    System.out.println("Unsorted:");
    for (int i = 0; i < t.length; i++) {
      System.out.println(t[i]);
    }
    System.out.println();

    System.out.println("Sorted:");
    Arrays.sort(t);
    for (int i = 0; i < t.length; i++) {
      System.out.println(t[i]);
    }
    System.out.println();
  }

  public static class Transaction implements Comparable<Transaction> {

    private final String name;
    private final SmartDate date;
    private final double amount;

    public Transaction(String who, SmartDate when, double amount) {
      this.name = who;
      this.date = when;
      this.amount = amount;
    }

    /**
     * 1.2.19 用
     *
     * @param transaction
     */
    public Transaction(String transaction) {
      String[] s = transaction.split("\\s+");
      if (s.length != 3) throw new IllegalArgumentException("Argument illegal " + transaction);
      this.name = s[0];
      this.date = new SmartDate(s[1]);
      this.amount = Double.parseDouble(s[2]);
    }

    public String who() {
      return this.name;
    }

    public SmartDate when() {
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
      Transaction t = (Transaction) that;
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
  }

  public static class SmartDate {

    private final int year;
    private final int month;
    private final int day;
    private int[] daysOfMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] leapDays = {0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    private int[] days = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    private static final String[] weekdays = {
      "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    public SmartDate(int month, int day, int year) {

      if (!isValid(month, day, year)) {
        throw new IllegalArgumentException("not legal date");
      }
      this.year = year;
      this.month = month;
      this.day = day;
    }

    public SmartDate(String date) {
      String[] s = date.split("\\/");
      if (s.length != 3) throw new IllegalArgumentException("Argument illegal " + date);
      int m = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      int y = Integer.parseInt(s[2]);

      if (!isValid(m, d, y))
        throw new IllegalArgumentException("Argument illegal " + m + "/" + d + "/" + y);

      this.month = m;
      this.day = d;
      this.year = y;
    }

    private boolean isValid(int month, int day, int year) {
      if (month < 1 || month > 12) {
        return false;
      }
      if (day < 1 || day > daysOfMonth[month]) {
        return false;
      }
      if (month == 2 && day == 29 && !isLeapYear(year)) {
        return false;
      }

      return true;
    }

    private String dayOfTheWeek() {
      int crossDay = 0;
      if (isLeapYear(year)) {
        crossDay = leapDays[month];
      } else {
        crossDay = days[month];
      }

      crossDay += day;

      for (int i = 2000; i < year; i++) {
        if (isLeapYear(i)) {
          crossDay += 366;
        } else {
          crossDay += 365;
        }
      }

      // 1/1/2000 是周六
      return weekdays[((crossDay - 1) % 7 + 6) % 7];
    }

    public String toString() {
      return month + "/" + day + "/" + year;
    }

    private boolean isLeapYear(int year) {
      if (year % 400 == 0) {
        return true;
      }

      if (year % 100 == 0) {
        return false;
      }

      return year % 4 == 0;
    }
  }
}
