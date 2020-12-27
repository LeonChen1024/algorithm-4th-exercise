package edu.princeton.cs.exercise.chapter2_5;

/**
 * 2.5.3 Criticize the following implementation of a class intended to represent account balances.
 * Why is compareTo() a flawed implementation of the Comparable interface? <code>
 * public class Balance implements Comparable<Balance>
 *   {
 * ...
 *     private double amount;
 *     public int compareTo(Balance that)
 *     {
 *       if (this.amount < that.amount - 0.005) return -1;
 *       if (this.amount > that.amount + 0.005) return +1;
 *       return 0;
 *     }
 * ...
 *   }
 * </code> Describe a way to fix this problem.
 *
 * <p>评判下面这个类的实现,它的作用是代表账户余额. 为什么这个 Comparable 接口的实现是有缺陷的?
 *
 * <p>描述一种方式来解决这个问题
 *
 * @author LeonChen
 * @since 12/16/20
 */
class E02_05_03 {

  /** 这样会破坏相等的传递性, 比如 a=0,b=0.005,c=-0.005 , a=b, a=c , 但是 b!=c */
  public static void main(String[] args) {}
}
