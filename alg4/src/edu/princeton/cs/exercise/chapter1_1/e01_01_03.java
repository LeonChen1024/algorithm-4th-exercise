package edu.princeton.cs.exercise.chapter1_1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

/**
 * 1.1.3 Write a program that takes three integer command-line arguments and prints equal if all
 * three are equal, and not equal otherwise.
 *
 * 编写一个程序接受 3 个整数作为命令行参数并且在他们都相等的时候打印 equal ,否则打印 not equal
 */
class e01_01_03 {


  /**
   */
  public static void main(String[] args) {
    System.out.println("请输入三个整数");
    Scanner scanner = new Scanner(System.in);
    String string1 = scanner.next();
    String string2 = scanner.next();
    String string3 = scanner.next();

    Integer number1 = Integer.valueOf(string1);
    Integer number2 = Integer.valueOf(string2);
    Integer number3 = Integer.valueOf(string3);

    // equal 有传递性,所以满足下列条件时 number1 自然 equals number3
    if (number1.equals(number2) && number2.equals(number3)) {
      System.out.println("equal");
    } else {
      System.out.println("not equal");
    }
  }
}
