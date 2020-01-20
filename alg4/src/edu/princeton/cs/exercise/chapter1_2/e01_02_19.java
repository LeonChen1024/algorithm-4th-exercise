package edu.princeton.cs.exercise.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.19 Parsing . Develop the parse constructors for your Date and Transaction implementations of
 * Exercise 1.2.13 that take a single String argument to specify the initialization values, using
 * the formats given in the table below. Partial solution: <code>
 *   public Date(String date) {
 *     String[] fields = date.split("/");
 *     month = Integer.parseInt(fields[0]);
 *     day = Integer.parseInt(fields[1]);
 *     year = Integer.parseInt(fields[2]);
 *   }
 * </code> <code>
 *        type                        format                                      example
 *        Date            integers separated by slashes                           5/22/1939
 *  Transaction   customer,date, and amount, separated by whitespace        Turing 5/22/1939 11.99
 *
 *
 *                            Formatsforparsing
 * </code> 解析.开发一个可以解析你的Date 和练习1.2.13 中的 Transaction 的构造器,接收一个字符串参数作为指定的初始值,使用<br>
 * 以下表给定的格式.部分解决方案如下.
 *
 * @author LeonChen
 * @since 12/13/19
 */
class e01_02_19 {
  public static class Date {

    public Date(String date) {
      String[] fields = date.split("/");
      int month = Integer.parseInt(fields[0]);
      int day = Integer.parseInt(fields[1]);
      int year = Integer.parseInt(fields[2]);
    }
  }

  public static void main(String[] args) {
    String in = "Turing 5/22/1939 11.99";
    String[] s = in.split("\\s+");
    if (s.length > 1) {
      e01_02_13_and_14.Transaction transaction = new e01_02_13_and_14.Transaction(in);
      StdOut.println(transaction);
    } else {
      Date date = new Date(in);
      StdOut.println(date);
    }
  }
}
